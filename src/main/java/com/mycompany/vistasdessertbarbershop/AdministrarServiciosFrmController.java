/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Servicio;
import com.roberto_rw.enums.Categoria;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;

/**
 *
 * @author Admin
 */
public class AdministrarServiciosFrmController implements Initializable {

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
    private ComboBox<Object> cboCategoriaServicio;

    @FXML
    private TextArea txtDescripcionServicio;

    @FXML
    private TextField txtPrecioServicio;

    @FXML
    private CheckBox chboxEliminar;

    private Servicio servicio;

    private String modo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        llenarComboBoxCategorias();
    }

    public void initComponents(Servicio servicio, String modo) {
        this.servicio = servicio;
        if (servicio != null) {
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

    private void llenarComboBoxCategorias() {
        Categoria[] categorias = Categoria.values();
        this.cboCategoriaServicio.getItems().addAll(categorias);
        this.cboCategoriaServicio.setValue("--Selecciona--");
    }

    private void modoAgregar() {
        this.lblTitulo.setText("Agregar Servicio");
        this.chboxEliminar.setVisible(false);
        this.btnConfirmar.setText("Agregar");
    }
    
    private void modoEditar() {
        this.lblTitulo.setText("Editar Servicio");
        this.chboxEliminar.setVisible(false);
        this.btnConfirmar.setText("Editar");
        this.cboCategoriaServicio.setValue(this.servicio.getCategoria());
        this.txtDescripcionServicio.setText(this.servicio.getDescripcion());
        this.txtPrecioServicio.setText(this.servicio.getPrecio()+"");
    }
    
    private void modoEliminar() {
        this.lblTitulo.setText("Eliminar Servicio");
        this.btnConfirmar.setText("Eliminar");
        this.cboCategoriaServicio.setValue(this.servicio.getCategoria());
        this.cboCategoriaServicio.setEditable(false);
        this.txtDescripcionServicio.setText(this.servicio.getDescripcion());
        this.txtDescripcionServicio.setEditable(false);
        this.txtPrecioServicio.setText(this.servicio.getPrecio()+"");
        this.txtPrecioServicio.setEditable(false);
    }
    
    public void accionVolver(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/MenuServiciosFrm.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Menú Servicios");
            stage.setResizable(false);
            stage.getIcons().add(new Image("./images/icono.png"));
            stage.show();

            Stage stage2 = (Stage) this.btnVolver.getScene().getWindow();
            stage2.close();
        } catch (IOException ex) {
            Logger.getLogger(AdministrarServiciosFrmController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdministrarServiciosFrmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void confirmarAccion(ActionEvent evt) {
        if (modo.equalsIgnoreCase("agregar")) {
            boolean validacion = validarCampos();
            if (validacion) {
                Servicio servicio = obtenerServicioFormulario();
                this.logicaNegocio.agregarServicio(servicio);
                mostrarMensajeInformativo("Se ha agregado correctamente el servicio");
            }
        }
        if (modo.equalsIgnoreCase("editar")) {
            boolean validacion = validarCampos();
            if (validacion) {
                actualizarServicio();
                this.logicaNegocio.actualizarServicio(servicio);
                mostrarMensajeInformativo("Se ha actualizado correctamente el servicio");
            }
        }
        if (modo.equalsIgnoreCase("eliminar")) {
            boolean confirmacion = this.chboxEliminar.isSelected();
            if (confirmacion) {
                this.logicaNegocio.eliminarServicio(servicio);
                mostrarMensajeInformativo("Se ha eliminado el servicio correctamente");
            } else {
                mostrarMensajeInformativo("Debes confirmar que estas seguro que deseas llevar a cabo la eliminación");
            }
        }
    }
    
    private boolean validarCampos(){
        if(camposVacios()){
            mostrarMensajeError("Hay algún campo vacío");
            return false;
        }
        
        String precio= this.txtPrecioServicio.getText();
        if(!verificarPrecio(precio)){
            mostrarMensajeError("El precio contiene algún caracter inválido");
            return false;
        }
        
        return true;
    }
    
    private boolean camposVacios(){
        String desc= this.txtDescripcionServicio.getText();
        String precio= this.txtPrecioServicio.getText();
        if(this.cboCategoriaServicio.getValue().equals("--Selecciona--")||desc.equals("")||precio.equals("")){
            return true;
        }else{
            return false;
        }
    }
    
    private Servicio obtenerServicioFormulario(){
        Categoria categoria= (Categoria)this.cboCategoriaServicio.getValue();
        String desc= this.txtDescripcionServicio.getText();
        double precio= Double.parseDouble(this.txtPrecioServicio.getText());
                
        Servicio servicio= new Servicio();
        servicio.setCategoria(categoria);
        servicio.setDescripcion(desc);
        servicio.setPrecio(precio);
        
        return servicio;
    }
    
    private void actualizarServicio(){
        Categoria categoria= (Categoria)this.cboCategoriaServicio.getValue();
        String desc= this.txtDescripcionServicio.getText();
        double precio= Double.parseDouble(this.txtPrecioServicio.getText());
        
        servicio.setCategoria(categoria);
        servicio.setDescripcion(desc);
        servicio.setPrecio(precio);
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
    
    private boolean verificarPrecio(String precio) {
        String patron = "^[0-9.]+$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(precio);
        return matcher.matches();
    }
}
