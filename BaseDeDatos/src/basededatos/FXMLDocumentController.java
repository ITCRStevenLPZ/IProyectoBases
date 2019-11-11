/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author PC_ESQUIVEL_LOPEZ
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button IngresarDonador;
    @FXML
    private TextField Nombres;
    @FXML
    private TextField Apellidos;
    @FXML
    private ChoiceBox<?> Sexo;
    @FXML
    private TextField Ano;
    @FXML
    private TextField Mes;
    @FXML
    private TextField Dia;
    @FXML
    private TextField Cedula;
    @FXML
    private TextField Apellidos2;
    @FXML
    private ChoiceBox<?> TipoSangre;
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
    private TextField Telefono11;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
