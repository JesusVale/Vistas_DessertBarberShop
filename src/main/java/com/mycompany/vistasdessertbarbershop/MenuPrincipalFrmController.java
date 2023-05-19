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
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class MenuPrincipalFrmController implements Initializable{

    @FXML
    private Button btnEmpleados;
    
    @FXML
    private Button btnClientes;
    
    @FXML
    private Button btnServicios;
    
    @FXML
    private Button btnCitas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void mostrarMenuEmpleados(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/MenuEmpleados.fxml"));
        try{
            Parent root= loader.load();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage stage2= (Stage) this.btnEmpleados.getScene().getWindow();
            stage2.close();
        }catch(IOException ex){
            Logger.getLogger(MenuPrincipalFrmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarMenuClientes(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/MenuClientes.fxml"));
        try{
            Parent root= loader.load();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage stage2= (Stage) this.btnClientes.getScene().getWindow();
            stage2.close();
        }catch(IOException ex){
            Logger.getLogger(MenuPrincipalFrmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarMenuServicios(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/MenuServicios.fxml"));
        try{
            Parent root= loader.load();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage stage2= (Stage) this.btnServicios.getScene().getWindow();
            stage2.close();
        }catch(IOException ex){
            Logger.getLogger(MenuPrincipalFrmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarMenuCitas(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/primary.fxml"));
        try{
            Parent root= loader.load();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage stage2= (Stage) this.btnCitas.getScene().getWindow();
            stage2.close();
        }catch(IOException ex){
            Logger.getLogger(MenuPrincipalFrmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
