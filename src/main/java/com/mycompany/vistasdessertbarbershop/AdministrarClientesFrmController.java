/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;

/**
 *
 * @author Admin
 */
public class AdministrarClientesFrmController implements Initializable {
    
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
    private TextField txtNombreCliente;
    
    @FXML
    private TextField txtCorreoCliente;
    
    @FXML
    private TextField txtTelefonoCliente;
    
    @FXML
    private CheckBox chboxEliminar;
    
    private Cliente cliente;
    
    private String modo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void initComponents(Cliente cliente, String modo) {
        this.cliente = cliente;
        if (cliente != null) {
            if (modo.equalsIgnoreCase("editar")) {
                this.modo = modo;
                modoEditar();
            }
            if (modo.equalsIgnoreCase("eliminar")) {
                modoEliminar();
                this.modo = modo;
            }
        } else {
            modoAgregar();
            this.modo = "agregar";
        }
    }
    
    private void modoAgregar(){
        this.lblTitulo.setText("Agregar Cliente");
        this.chboxEliminar.setVisible(false);
        this.btnConfirmar.setText("Agregar");
    }
    
    private void modoEditar(){
        this.lblTitulo.setText("Editar Cliente");
        this.chboxEliminar.setVisible(false);
        this.btnConfirmar.setText("Editar");
        this.txtNombreCliente.setText(this.cliente.getNombre());
        this.txtCorreoCliente.setText(this.cliente.getCorreo());
        this.txtTelefonoCliente.setText(this.cliente.getTelefono());
    }
    
    private void modoEliminar(){
        this.lblTitulo.setText("Eliminar Cliente");
        this.btnConfirmar.setText("Eliminar");
        this.txtNombreCliente.setText(this.cliente.getNombre());
        this.txtNombreCliente.setEditable(false);
        this.txtCorreoCliente.setText(this.cliente.getCorreo());
        this.txtCorreoCliente.setEditable(false);
        this.txtTelefonoCliente.setText(this.cliente.getTelefono());
        this.txtTelefonoCliente.setEditable(false);
    }
    
    private void confirmarAccion() {
        if (modo.equalsIgnoreCase("agregar")) {
            boolean validacion = validarCampos();
            if (validacion) {
                Cliente cliente = obtenerClienteFormulario();
                this.logicaNegocio.agregarCliente(cliente);
                mostrarMensajeInformativo("Se ha agregado correctamente el cliente");
            }
        }
        if (modo.equalsIgnoreCase("editar")) {
            boolean validacion = validarCampos();
            if (validacion) {
                actualizarCliente();
                this.logicaNegocio.actualizarCliente(this.cliente);
                mostrarMensajeInformativo("Se ha actualizado correctamente el cliente");
            }
        }
        if (modo.equalsIgnoreCase("eliminar")) {
            boolean confirmacion = this.chboxEliminar.isSelected();
            if (confirmacion) {
                this.logicaNegocio.eliminarCliente(this.cliente);
                mostrarMensajeInformativo("Se ha eliminado el cliente correctamente");
            } else {
                mostrarMensajeInformativo("Debes confirmar que estas seguro que deseas llevar a cabo la eliminación");
            }
        }
    }
    
    private boolean validarCampos(){
        if(camposVacios()){
            mostrarMensajeError("Algún campo se encuentra vacío");
            return false;
        }
        
        String nombre= this.txtNombreCliente.getText();
        if(!verificarCadenasTexto(nombre)){
            mostrarMensajeError("El nombre contiene algún caracter inválido");
            return false;
        }
        
        String correo= this.txtCorreoCliente.getText();
        if(!verificarCorreo(correo)){
            mostrarMensajeError("El correo electrónico no se encuentra en el formato adecuado");
            return false;
        }
        
        String telefono= this.txtTelefonoCliente.getText();
        if(!verificarTelefono(telefono)){
            mostrarMensajeError("El teléfono contiene algún caracter inválido");
            return false;
        }
        
        return true;
    }
    
    private Cliente obtenerClienteFormulario(){
        Cliente cliente= new Cliente();
        cliente.setNombre(this.txtNombreCliente.getText());
        cliente.setCorreo(this.txtCorreoCliente.getText());
        cliente.setTelefono(this.txtTelefonoCliente.getText());
        return cliente;
    }
    
    private void actualizarCliente(){
        this.cliente.setNombre(this.txtNombreCliente.getText());
        this.cliente.setCorreo(this.txtCorreoCliente.getText());
        this.cliente.setTelefono(this.txtTelefonoCliente.getText());
    }
    
    private boolean camposVacios(){
        String nombre= this.txtNombreCliente.getText();
        String correo= this.txtCorreoCliente.getText();
        String telefono= this.txtTelefonoCliente.getText();
        
        if(nombre.equals("")||correo.equals("")||telefono.equals("")){
            return true;
        }
        return false;
    }
    
    private void mostrarMensajeError(String error) {
        Alert alertaInfo = new Alert(Alert.AlertType.WARNING);
        alertaInfo.setTitle("Advertencia");
        alertaInfo.setContentText(error);
        alertaInfo.setResizable(false);
        alertaInfo.show();
    }

    private void mostrarMensajeInformativo(String mensaje) {
        Alert alertaInfo = new Alert(Alert.AlertType.INFORMATION);
        alertaInfo.setTitle("Información");
        alertaInfo.setContentText(mensaje);
        alertaInfo.setResizable(false);
        alertaInfo.show();
    }

    private boolean verificarCadenasTexto(String cadena) {
        String patron = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    private boolean verificarTelefono(String telefono) {
        String patron = "^\\d{10}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
    }
    
    private boolean verificarCorreo(String correo){
        String patron= "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
}
