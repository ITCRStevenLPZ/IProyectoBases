/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author PC_ESQUIVEL_LOPEZ
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button IngresarDonador;
    @FXML
    private TextField Nombres;
    @FXML
    private TextField Apellidos;
    @FXML
    private ChoiceBox<String> Sexo;
    @FXML
    private ComboBox<Integer> Ano;
    @FXML
    private ChoiceBox<String> Mes;
    @FXML
    private ComboBox<Integer> Dia;
    @FXML
    private TextField Cedula;
    @FXML
    private TextField Apellidos2;
    @FXML
    private ChoiceBox<String> TipoSangre;
    @FXML
    private TextField Telefono1;
    @FXML
    private TextField Telefono2;
    @FXML
    private TextField Provincia;
    @FXML
    private TextField Canton;
    @FXML
    private TextField Distrito;
    @FXML
    private TextField Direccion;
    @FXML
    private TextField Cant;
    @FXML
    private TextField CantExis;
    @FXML
    private TableView<Persona> Tabla;
    @FXML
    private TextField CedulaDon;
    @FXML
    private Button IngresarDonacion;
    @FXML
    private Button MostrarTodos;
    @FXML
    private ComboBox<String> Enfermedades;
    @FXML
    private TextField CedulaEnfe;
    @FXML
    private ComboBox<String> Gravedad;
    @FXML
    private TextField AnoPadecimiento;
    @FXML
    private Button IngresarEnfermedad;
    @FXML
    private TextField CedulaMedi;
    @FXML
    private TextField NombreMedi;
    @FXML
    private Button IngresarMedicamento;
    @FXML
    private TextField AnoMed;
    @FXML
    private ComboBox<String> TipoMedi;
    @FXML
    private TextField AnoMedFin;

    ArrayList<Persona> Donadores;
    String insertarUser = "insert into persona values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String insertarMedi = "insert into medicamento_pac values(?,?,?,?,?)";
    String insertarEnfermedad = "insert into enfermedad_pac values(?,?,?,?)";
    String insertarDonacion = "insert into donacion values(?,?)";
    String verEnfer_Pac = "select * from enfermedad_pac";
    String verUser = "select * from persona";
    @FXML
    private Button PomedioSangre;

    /**
     *
     * @param event
     */
    @FXML
    public void AnadirNuevo(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/DonacionCR", "postgres", "roniexy50");
                PreparedStatement ingreso = conn.prepareStatement(insertarUser)) {

            ingreso.setInt(1, parseInt(Cedula.getText()));//cedula
            ingreso.setInt(2, ObtenerDato(Dia));//dia
            ingreso.setString(3, Mes.getValue());//mes
            ingreso.setInt(4, ObtenerDato(Ano));//ano
            ingreso.setString(5, Nombres.getText());//nombres
            ingreso.setString(6, Apellidos.getText());//apellidos
            ingreso.setString(7, Apellidos2.getText());//apellidos2
            ingreso.setString(8, Sexo.getValue());//sexo
            ingreso.setString(9, Direccion.getText());//direccion
            ingreso.setString(10, Distrito.getText());//distrito
            ingreso.setString(11, Canton.getText());//canton
            ingreso.setString(12, Provincia.getText());//provincia
            ingreso.setString(13, TipoSangre.getValue());//tipo de sangre
            ingreso.setInt(14, parseInt(Cant.getText()));//cantidad de sangre en ml
            ingreso.setInt(15, parseInt(Telefono1.getText()));//telefono obligatorio
            ingreso.setInt(16, parseInt(Telefono2.getText()));//telefono opcional
            ingreso.executeUpdate();

            if (conn != null) {
                System.out.println("Donador nuevo ingresado!");
            } else {
                System.out.println("Fallo a la hora de ingresar nuevo donador!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int ObtenerMedi(ComboBox<String> medi) {
        if (null == medi.getValue()) {
            return 5;
        } else {
            switch (medi.getValue()) {
                case "Antiinflamatorios":
                    return 1;
                case "Analgésicos":
                    return 2;
                case "Antiinfecciosos":
                    return 3;
                case "Antialérgicos":
                    return 4;
                default:
                    return 5;
            }
        }
    }

    @FXML
    public void AnadirMedi(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/DonacionCR", "postgres", "roniexy50");
                PreparedStatement ingreso = conn.prepareStatement(insertarMedi)) {

            ingreso.setInt(1, parseInt(CedulaMedi.getText()));//cedula medicamento
            ingreso.setInt(2, ObtenerMedi(TipoMedi));//tipo
            ingreso.setInt(3, parseInt(AnoMed.getText()));//ano inicio
            ingreso.setInt(4, parseInt(AnoMedFin.getText()));//ano fin
            ingreso.setString(5, NombreMedi.getText());//nombre medicamento
            ingreso.executeUpdate();

            if (conn != null) {
                System.out.println("Medicamento nuevo ingresado!");
            } else {
                System.out.println("Fallo a la hora de ingresar nuevo medicamento!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void AnadirDonacion(ActionEvent event) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/DonacionCR", "postgres", "roniexy50");
                PreparedStatement ingreso = conn.prepareStatement(insertarDonacion)) {
            ingreso.setInt(1, parseInt(CedulaDon.getText()));//cedula donador
            ingreso.setInt(2, parseInt(CantExis.getText()));//cantidad nueva
            ingreso.executeUpdate();

            if (conn != null) {
                System.out.println("Donacion nueva ingresada!");
            } else {
                System.out.println("Fallo a la hora de ingresar nueva donacion!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void AnadirEnfermedad(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/DonacionCR", "postgres", "roniexy50");
                PreparedStatement ingreso = conn.prepareStatement(insertarEnfermedad)) {

            ingreso.setInt(1, parseInt(CedulaEnfe.getText()));//cedula enfermedad
            ingreso.setInt(2, ObtenerEnfermedad(Enfermedades));//enfermedad
            ingreso.setInt(3, parseInt(AnoPadecimiento.getText()));//ano inicio
            ingreso.setString(4, Gravedad.getValue());//seriedad/gravedad de enfermedad
            ingreso.executeUpdate();

            if (conn != null) {
                System.out.println("Enfermedad nueva ingresada!");
            } else {
                System.out.println("Fallo a la hora de ingresar nueva enfermedad!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int ObtenerEnfermedad(ComboBox<String> a) {
        if (null == a.getValue()) {
            return 6;
        } else {
            switch (a.getValue()) {
                case "Diabetes":
                    return 1;
                case "Hipertensión":
                    return 2;
                case "Trigliceridos":
                    return 3;
                case "Cancer":
                    return 4;
                case "Anemia":
                    return 5;
                default:
                    return 6;
            }
        }
    }

    private int ObtenerDato(ComboBox<Integer> a) {
        return a.getValue();
    }

    private void MostrarTodos() {
        Donadores = null;
        Donadores = new ArrayList<>();
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/DonacionCR", "postgres", "roniexy50");
            stmt = conn.createStatement();
            ResultSet mostrar = stmt.executeQuery(verUser);
            while (mostrar.next()) {
                int cedula = mostrar.getInt("cedula");
                String name = mostrar.getString("nombre");
                String apellidos = mostrar.getString("apellido");
                String apellidos2 = mostrar.getString("apelido2");
                int dia = mostrar.getInt("dia");
                String mes = mostrar.getString("mes");
                int ano = mostrar.getInt("ano");
                String sexo = mostrar.getString("sexo");
                String direccion = mostrar.getString("direccion");
                String distrito = mostrar.getString("distrito");
                String canton = mostrar.getString("canton");
                String provincia = mostrar.getString("provincia");
                String tipo_sangre = mostrar.getString("tipo_sangre");
                int total_sangre = mostrar.getInt("total_sangre");
                int telefono1 = mostrar.getInt("telefono1");
                int telefono2 = mostrar.getInt("telefono2");
                Persona nueva = new Persona(cedula, name, apellidos, apellidos2, sexo, provincia, canton, distrito, direccion, dia, mes, ano, tipo_sangre, total_sangre, telefono1, telefono2);
                InsertarEnfermedades(nueva);
                Donadores.add(nueva);

            }
            mostrar.close();
            stmt.close();

            if (conn != null) {
                System.out.println("Se muestran todos los donadores con exito!");
            } else {
                System.out.println("Fallo a la hora de ingresar nuevo donador!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void InsertarEnfermedades(Persona a) {
        a.enfermedades = null;
        a.enfermedades = new ArrayList<>();
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/DonacionCR", "postgres", "roniexy50");
            stmt = conn.createStatement();
            ResultSet mostrar = stmt.executeQuery(verEnfer_Pac);
            while (mostrar.next()) {
                int cedula = mostrar.getInt("id");
                int codigo = mostrar.getInt("codigo");
                int ano = mostrar.getInt("fec_diagnostico");
                String gravedad = mostrar.getString("seriedad");
                if (cedula == a.getCedula()) {
                    if (codigo == 1) {
                        a.diabetes = true;
                        Enfermedad nueva = new Enfermedad(nombreEnfermedad(codigo), gravedad, ano);
                        a.enfermedades.add(nueva);
                    } else {
                        Enfermedad nueva = new Enfermedad(nombreEnfermedad(codigo), gravedad, ano);
                        a.enfermedades.add(nueva);
                    }

                }
            }
            mostrar.close();
            stmt.close();

            if (conn == null) {
                System.out.println("No fue posiblee encontrar enfermedades");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String nombreEnfermedad(int a) {
        switch (a) {
            case 1:
                return "Diabetes";
            case 2:
                return "Hipertensión";
            case 3:
                return "Trigliceridos";
            case 4:
                return "Cancer";
            case 5:
                return "Anemia";
            default:
                return "ETS";
        }
    }

    @FXML
    public void AgregarOrdenAlfa(ActionEvent event) {
        Tabla.getColumns().clear();
        MostrarTodos();
        ObservableList<Persona> Don = FXCollections.observableArrayList();
        for (int i = 0; i < Donadores.size(); i++) {
            Don.add(Donadores.get(i));
        }
        Tabla.setItems(Don);
        TableColumn<Persona, String> colApe1 = new TableColumn<>("Apellido");
        colApe1.setCellValueFactory(new PropertyValueFactory<Persona, String>("Apellido1"));
        colApe1.setMinWidth(Tabla.getMaxWidth() / 4);

        TableColumn<Persona, String> colApe2 = new TableColumn<>("2ndo Apellido");
        colApe2.setCellValueFactory(new PropertyValueFactory<Persona, String>("Apellido2"));
        colApe2.setMinWidth(Tabla.getMaxWidth() / 4);

        TableColumn<Persona, String> Name = new TableColumn<>("Nombre");
        Name.setCellValueFactory(new PropertyValueFactory<Persona, String>("Nombres"));
        Name.setMinWidth(Tabla.getMaxWidth() / 4);

        TableColumn<Persona, String> Ced = new TableColumn<>("Cedula");
        Ced.setCellValueFactory(new PropertyValueFactory<Persona, String>("Cedula"));
        Ced.setMinWidth(Tabla.getMaxWidth() / 4);

        Tabla.getColumns().addAll(colApe1, colApe2, Name, Ced);
    }

    @FXML
    public void AgregarPromSangre(ActionEvent event) {
        Tabla.getColumns().clear();
        MostrarTodos();
        ObservableList<Persona> Don = FXCollections.observableArrayList();
        for (int i = 0; i < Donadores.size(); i++) {
            Don.add(Donadores.get(i));
        }
        Tabla.setItems(Don);
        TableColumn<Persona, String> colApe1 = new TableColumn<>("Apellido");
        colApe1.setCellValueFactory(new PropertyValueFactory<Persona, String>("Apellido1"));
        colApe1.setMinWidth(Tabla.getMaxWidth() / 6);

        TableColumn<Persona, String> colApe2 = new TableColumn<>("2ndo Apellido");
        colApe2.setCellValueFactory(new PropertyValueFactory<Persona, String>("Apellido2"));
        colApe2.setMinWidth(Tabla.getMaxWidth() / 6);

        TableColumn<Persona, String> Name = new TableColumn<>("Nombre");
        Name.setCellValueFactory(new PropertyValueFactory<Persona, String>("Nombres"));
        Name.setMinWidth(Tabla.getMaxWidth() / 6);

        TableColumn<Persona, String> Ced = new TableColumn<>("Cedula");
        Ced.setCellValueFactory(new PropertyValueFactory<Persona, String>("Cedula"));
        Ced.setMinWidth(Tabla.getMaxWidth() / 6);

        TableColumn<Persona, String> total = new TableColumn<>("Sangre Donada");
        total.setCellValueFactory(new PropertyValueFactory<Persona, String>("Cant_Sangre"));
        total.setMinWidth(Tabla.getMaxWidth() / 6);

        TableColumn<Persona, String> tipo = new TableColumn<>("Tipo de Sangre");
        tipo.setCellValueFactory(new PropertyValueFactory<Persona, String>("Tipo_Sangre"));
        tipo.setMinWidth(Tabla.getMaxWidth() / 6);

        Tabla.getColumns().addAll(colApe1, colApe2, Name, Ced, total, tipo);
    }

    public void AgregarDiabetes(ActionEvent event) {
        Tabla.getColumns().clear();
        MostrarTodos();
        ObservableList<Persona> Don = FXCollections.observableArrayList();
        for (int i = 0; i < Donadores.size(); i++) {
            if (Donadores.get(i).diabetes==true){
                Don.add(Donadores.get(i));
            }          
        }
        Tabla.setItems(Don);
        TableColumn<Persona, String> colApe1 = new TableColumn<>("Apellido");
        colApe1.setCellValueFactory(new PropertyValueFactory<Persona, String>("Apellido1"));
        colApe1.setMinWidth(Tabla.getMaxWidth() / 4);

        TableColumn<Persona, String> colApe2 = new TableColumn<>("2ndo Apellido");
        colApe2.setCellValueFactory(new PropertyValueFactory<Persona, String>("Apellido2"));
        colApe2.setMinWidth(Tabla.getMaxWidth() / 4);

        TableColumn<Persona, String> Name = new TableColumn<>("Nombre");
        Name.setCellValueFactory(new PropertyValueFactory<Persona, String>("Nombres"));
        Name.setMinWidth(Tabla.getMaxWidth() / 4);

        TableColumn<Persona, String> Ced = new TableColumn<>("Cedula");
        Ced.setCellValueFactory(new PropertyValueFactory<Persona, String>("Cedula"));
        Ced.setMinWidth(Tabla.getMaxWidth() / 4);
        
        TableColumn colClubInfo = new TableColumn("Club Information");
        

        Tabla.getColumns().addAll(colApe1, colApe2, Name, Ced);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Sexo.setItems(observableArrayList(
                "Hombre", "Mujer"));
        Sexo.setValue("Hombre");
        Enfermedades.setItems(observableArrayList(
                "Diabetes", "Hipertensión", "Trigliceridos", "Cancer", "Anemia", "ETS"));
        Enfermedades.setValue("Diabetes");
        Gravedad.setItems(observableArrayList(
                "bajo", "medio", "alto"));
        Gravedad.setValue("bajo");
        TipoMedi.setItems(observableArrayList(
                " Antiinflamatorios", "Analgésicos", "Antiinfecciosos", "Antialérgicos", "Antiácidos"));
        TipoMedi.setValue("Analgésicos");
        TipoSangre.setItems(observableArrayList(
                "O+", "O-", "A+", "A-", "AB+", "AB-", "B+", "B-"));
        TipoSangre.setValue("AB+");
        Ano.setItems(Anos());
        Ano.setValue(2001);
        Dia.setItems(Dias());
        Dia.setValue(1);
        Mes.setItems(observableArrayList(
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
        Mes.setValue("Enero");
    }

    public ObservableList<Integer> Anos() {
        ObservableList<Integer> respuesta = FXCollections.observableArrayList();;
        int anos = 2001;//ano maximo
        for (int a = 0; a < 50; a++) {
            respuesta.add(anos - a);
        }
        return respuesta;
    }

    public ObservableList<Integer> Dias() {
        ObservableList<Integer> respuesta = FXCollections.observableArrayList();;
        for (int a = 1; a < 32; a++) {
            respuesta.add(a);
        }
        return respuesta;
    }

}
