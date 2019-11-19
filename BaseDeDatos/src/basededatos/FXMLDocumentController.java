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
    String verUser = "select * from persona";

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
                Persona nueva=new Persona(cedula,name,apellidos,apellidos2,sexo,provincia,canton,distrito,direccion,dia,mes,ano,tipo_sangre,total_sangre,telefono1,telefono2);
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

    @FXML
    public void AgregarOrdenAlfa(ActionEvent event) {
        Tabla.getColumns().clear();
        MostrarTodos();
        ObservableList<Persona> Don = FXCollections.observableArrayList();
        for (int i = 0 ; i<Donadores.size();i++) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Sexo.setItems(observableArrayList(
                "Hombre", "Mujer"));
        Sexo.setValue("Hombre");
        Enfermedades.setItems(observableArrayList(
                "Diabetes", "Hipertensión", "Cirrosis", "Cancer", "Anemia"));
        Enfermedades.setValue("Diabetes");
        Gravedad.setItems(observableArrayList(
                "Leve", "Media", "Grave"));
        Gravedad.setValue("Leve");
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
