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
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import javafx.scene.control.TextField;

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
    private TextField CedulaExis;
    @FXML
    private TextField Cant;
    @FXML
    private TextField CantExis;

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
            ingreso.setInt(1, parseInt(Cedula.getText()));//cedulla
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

    private void AnadiraExistente(ActionEvent event) {
        System.out.println("You clicked me!");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Sexo.setItems(observableArrayList(
                "Hombre", "Mujer"));
        TipoSangre.setItems(observableArrayList(
                "O+", "O-", "A+", "A-", "AB+", "AB-", "B+", "B-"));
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
