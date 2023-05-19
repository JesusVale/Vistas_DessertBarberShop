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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Admin
 */
public class MenuEmpleadosController implements Initializable{

    @FXML
    private Button btnCasa;
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private Button btnBuscarEmpleado;
    
    @FXML
    private Button btnNuevoEmpleado;
    
    @FXML
    private HBox buscadorBox;
    
    @FXML
    private Label citasLbl;
    
    @FXML
    private TextField txtBuscarEmpleado;
    
    @FXML
    private VBox boxEmpleados;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
