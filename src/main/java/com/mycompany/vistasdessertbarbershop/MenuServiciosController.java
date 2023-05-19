/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistasdessertbarbershop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class MenuServiciosController implements Initializable{

    @FXML
    private Button btnCasa;
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private Button btnBuscarServicio;
    
    @FXML
    private Button btnNuevoServicio;
    
    @FXML
    private HBox buscadorBox;
    
    @FXML
    private TextField txtBuscarServicio;
    
    @FXML
    private VBox boxServicios;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void accionVolver(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/MenuPrincipalFrm.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
            stage.setResizable(false);
            stage.getIcons().add(new Image("./images/icono.png"));
            stage.show();

            Stage stage2 = (Stage) this.btnVolver.getScene().getWindow();
            stage2.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void volverPrincipio(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/MenuPrincipalFrm.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
            stage.setResizable(false);
            stage.getIcons().add(new Image("./images/icono.png"));
            stage.show();

            Stage stage2 = (Stage) this.btnCasa.getScene().getWindow();
            stage2.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarAgregarServicio(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/AdministrarServiciosFrm.fxml"));
        try {
            Parent root = loader.load();
            AdministrarServiciosFrmController controlador= loader.getController();
            controlador.initComponents(null, "agregar");
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar Servicio");
            stage.setResizable(false);
            stage.getIcons().add(new Image("./images/icono.png"));
            stage.show();

            Stage stage2 = (Stage) this.btnNuevoServicio.getScene().getWindow();
            stage2.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
