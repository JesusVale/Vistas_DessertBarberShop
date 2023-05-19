/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistasdessertbarbershop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    
}
