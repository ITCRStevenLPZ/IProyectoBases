/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDeDatos extends Application {

    private final String user = "postgres";
    private final String password = "Roniexy50";

    public Connection realizaConexion() {
        Connection conn = null;
        String urlDatabase = "jdbc:postgresql://localhost:5432/DonacionCR";
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase, user, password);
            System.out.println("La conexión se realizo sin problemas! =) ");
            return conn;
        } catch (Exception e) {
            System.out.println("Ocurrio un error : " + e.getMessage());
            return null;
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        BaseDeDatos proyecto = new BaseDeDatos();
        proyecto.realizaConexion();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
