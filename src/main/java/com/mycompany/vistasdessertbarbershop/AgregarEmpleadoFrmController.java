/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Empleado;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;

/**
 *
 * @author Admin
 */
public class AgregarEmpleadoFrmController implements Initializable {

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
    private TextField txtNombreEmpleado;

    @FXML
    private TextField txtApellidoEmpleado;

    @FXML
    private TextField txtTelefonoEmpleado;

    @FXML
    private TextField txtPuestoEmpleado;

    @FXML
    private TextField txtSalarioEmpleado;

    @FXML
    private TextField txtHoraEntradaEmpleado;

    @FXML
    private TextField txtHoraSalidaEmpleado;

    @FXML
    private CheckBox chboxEliminar;

    private Empleado empleado;

    private String modo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void initComponents(Empleado empleado, String modo) {
        this.empleado = empleado;
        if (empleado != null) {
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

    private void modoAgregar() {
        this.lblTitulo.setText("Agregar Empleado");
        this.chboxEliminar.setVisible(false);
        this.btnConfirmar.setText("Agregar");
    }

    private void modoEditar() {
        this.lblTitulo.setText("Editar Empleado");
        this.chboxEliminar.setVisible(false);
        this.btnConfirmar.setText("Editar");
        this.txtNombreEmpleado.setText(this.empleado.getNombre());
        this.txtApellidoEmpleado.setText(this.empleado.getApellido());
        this.txtTelefonoEmpleado.setText(this.empleado.getTelefono());
        this.txtPuestoEmpleado.setText(this.empleado.getPuesto());
        this.txtSalarioEmpleado.setText(this.empleado.getSalario() + "");
        this.txtHoraEntradaEmpleado.setText((this.empleado.getHoraEntrada().getHour() + 1) + ":" + this.empleado.getHoraEntrada().getMinute());
        this.txtHoraSalidaEmpleado.setText((this.empleado.getHoraSalida().getHour() + 1) + ":" + this.empleado.getHoraSalida().getMinute());
    }

    private void modoEliminar() {
        this.lblTitulo.setText("Eliminar Empleado");
        this.btnConfirmar.setText("Eliminar");
        this.txtNombreEmpleado.setText(this.empleado.getNombre());
        this.txtNombreEmpleado.setEditable(false);
        this.txtApellidoEmpleado.setText(this.empleado.getApellido());
        this.txtApellidoEmpleado.setEditable(false);
        this.txtTelefonoEmpleado.setText(this.empleado.getTelefono());
        this.txtTelefonoEmpleado.setEditable(false);
        this.txtPuestoEmpleado.setText(this.empleado.getPuesto());
        this.txtPuestoEmpleado.setEditable(false);
        this.txtSalarioEmpleado.setText(this.empleado.getSalario() + "");
        this.txtSalarioEmpleado.setEditable(false);
        this.txtHoraEntradaEmpleado.setText((this.empleado.getHoraEntrada().getHour() + 1) + ":" + this.empleado.getHoraEntrada().getMinute());
        this.txtHoraEntradaEmpleado.setEditable(false);
        this.txtHoraSalidaEmpleado.setText((this.empleado.getHoraSalida().getHour() + 1) + ":" + this.empleado.getHoraSalida().getMinute());
        this.txtHoraSalidaEmpleado.setEditable(false);
    }
    
    public void accionVolver(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/MenuEmpleados.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Menú Empleados");
            stage.setResizable(false);
            stage.getIcons().add(new Image("./images/icono.png"));
            stage.show();

            Stage stage2 = (Stage) this.btnVolver.getScene().getWindow();
            stage2.close();
        } catch (IOException ex) {
            Logger.getLogger(AgregarEmpleadoFrmController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AgregarEmpleadoFrmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmarAccion(ActionEvent event) {
        if (modo.equalsIgnoreCase("agregar")) {
            boolean validacion = validarCampos();
            if (validacion) {
                Empleado empleado = obtenerEmpleadoFormulario();
                this.logicaNegocio.agregarEmpleado(empleado);
                mostrarMensajeInformativo("Se ha agregado correctamente al empleado");
            }
        }
        if (modo.equalsIgnoreCase("editar")) {
            boolean validacion = validarCampos();
            if (validacion) {
                actualizarEmpleado();
                this.logicaNegocio.actualizarEmpleado(this.empleado);
                mostrarMensajeInformativo("Se ha actualizado correctamente al empleado");
            }
        }
        if (modo.equalsIgnoreCase("eliminar")) {
            boolean confirmacion = this.chboxEliminar.isSelected();
            if (confirmacion) {
                this.logicaNegocio.eliminarEmpleado(empleado);
                mostrarMensajeInformativo("Se ha eliminado el empleado correctamente");
            } else {
                mostrarMensajeInformativo("Debes confirmar que estas seguro que deseas llevar a cabo la eliminación");
            }
        }
    }

    private Empleado obtenerEmpleadoFormulario() {
        String nombre = this.txtNombreEmpleado.getText();
        String apellido = this.txtApellidoEmpleado.getText();
        String telefono = this.txtTelefonoEmpleado.getText();
        String puesto = this.txtPuestoEmpleado.getText();
        double salario = Double.parseDouble(this.txtSalarioEmpleado.getText());
        LocalTime horaEntrada = LocalTime.parse(this.txtHoraEntradaEmpleado.getText());
        LocalTime horaSalida = LocalTime.parse(this.txtHoraSalidaEmpleado.getText());

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setTelefono(telefono);
        empleado.setPuesto(puesto);
        empleado.setSalario(salario);
        empleado.setHoraEntrada(horaEntrada);
        empleado.setHoraSalida(horaSalida);

        return empleado;
    }

    private void actualizarEmpleado() {
        this.empleado.setNombre(this.txtNombreEmpleado.getText());
        this.empleado.setApellido(this.txtApellidoEmpleado.getText());
        this.empleado.setTelefono(this.txtTelefonoEmpleado.getText());
        this.empleado.setPuesto(this.txtPuestoEmpleado.getText());
        this.empleado.setSalario(Double.parseDouble(this.txtSalarioEmpleado.getText()));
        this.empleado.setHoraEntrada(LocalTime.parse(this.txtHoraEntradaEmpleado.getText()));
        this.empleado.setHoraSalida(LocalTime.parse(this.txtHoraSalidaEmpleado.getText()));
    }

    private boolean validarCampos() {
        if(camposVacios()){
            mostrarMensajeError("Algún campo se encuentra vacío");
            return false;
        }
        
        String nombre = this.txtNombreEmpleado.getText();
        if (!verificarCadenasTexto(nombre)) {
            mostrarMensajeError("El nombre contiene caracteres no válidos");
            return false;
        }

        String apellido = this.txtApellidoEmpleado.getText();
        if (!verificarCadenasTexto(apellido)) {
            mostrarMensajeError("El apellido contiene caracteres no válidos");
            return false;
        }

        String telefono = this.txtTelefonoEmpleado.getText();
        if (!verificarTelefono(telefono)) {
            mostrarMensajeError("El telefono contiene caracteres inválidos o esta incompleto");
            return false;
        }

        String puesto = this.txtPuestoEmpleado.getText();
        if (!verificarCadenasTexto(puesto)) {
            mostrarMensajeError("El puesto contiene caracteres inválidos");
            return false;
        }

        String salario = this.txtSalarioEmpleado.getText();
        if (!verificarSalario(salario)) {
            mostrarMensajeError("El salario contiene caracteres inválidos");
            return false;
        }

        String horaEntrada = this.txtHoraEntradaEmpleado.getText();
        if (!verificarHoras(horaEntrada)) {
            mostrarMensajeError("La hora de entrada no se encuentra en el formato solicitado o contiene caracteres inválidos");
            return false;
        }

        String horaSalida = this.txtHoraSalidaEmpleado.getText();
        if (!verificarHoras(horaSalida)) {
            mostrarMensajeError("La hora de salida no se encuentra en el formato solicitado o contiene caracteres inválidos");
            return false;
        }

        return true;
    }
    
    private boolean camposVacios(){
        String nombre = this.txtNombreEmpleado.getText();
        String apellido = this.txtApellidoEmpleado.getText();
        String telefono = this.txtTelefonoEmpleado.getText();
        String puesto = this.txtPuestoEmpleado.getText();
        String salario = this.txtSalarioEmpleado.getText();
        String horaEntrada = this.txtHoraEntradaEmpleado.getText();
        String horaSalida = this.txtHoraSalidaEmpleado.getText();
        
        if(nombre.equals("")||apellido.equals("")||telefono.equals("")||puesto.equals("")||salario.equals("")||horaEntrada.equals(nombre)||horaSalida.equals("")){
            return true;
        }else{
            return false;
        }
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

    private boolean verificarSalario(String salario) {
        String patron = "^[0-9.]+$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(salario);
        return matcher.matches();
    }

    private boolean verificarHoras(String hora) {
        String patron = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(hora);
        return matcher.matches();
    }
}
