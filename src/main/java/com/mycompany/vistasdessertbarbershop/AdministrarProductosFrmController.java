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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;

/**
 *
 * @author Admin
 */
public class AdministrarProductosFrmController implements Initializable{
    public ILogicaNegocio logicaNegocio = new LogicaNegocio();
    
    @FXML
    private Button btnConfirmar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnCasa;
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private Label lblTitulo;
    
    @FXML
    private TextField txtNombreProducto;
    
    @FXML
    private TextArea txtDescripcionProducto;
    
    @FXML
    private TextField txtPrecioProducto;
    
    @FXML
    private TextField txtStockProducto;
    
    @FXML
    private CheckBox chboxEliminar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
