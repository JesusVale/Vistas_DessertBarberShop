package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Cita;
import com.roberto_rw.entidades.Cliente;
import com.roberto_rw.entidades.Empleado;
import com.roberto_rw.entidades.Servicio;
import com.roberto_rw.entidades.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;
//import tornadofx.control.DateTimePicker;

public class CitaFrmController implements Initializable{

    public ILogicaNegocio logicaNegocio = new LogicaNegocio();
    
    @FXML
    private Button btnAgendar;

    @FXML
    private Button btnAgregarCliente;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private Button btnCasa;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<Object> cboClientes;

    @FXML
    private ComboBox<Object> cboPeluquero;

    @FXML
    private ComboBox<Object> cboServicio;
    
    @FXML
    private DatePicker dtpFechaCita;

    @FXML
    private TextField txtHoraFin;

    @FXML
    private TextField txtHoraInicio;
    
    @FXML
    private Label nuevaCitasLbl;
    
    private Cita cita;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxClientes();
        llenarComboBoxEmpleado();
        llenarComboBoxServicio();
    }
    
    public void initComponents(Cita cita){
        this.cita = cita;
        if(cita != null){
            modoEditar();
        }
    }
    
    private void modoEditar(){
        nuevaCitasLbl.setText("Editar Cita");
        this.dtpFechaCita.setValue(cita.getFechaInicio().toLocalDate());
        this.txtHoraInicio.setText(cita.getFechaInicio().format(DateTimeFormatter.ofPattern("HH:mm")));
        this.txtHoraFin.setText(cita.getFechaFin().format(DateTimeFormatter.ofPattern("HH:mm")));
        this.cboPeluquero.setValue(cita.getEmpleado());
        this.cboClientes.setValue(cita.getCliente());
        this.cboServicio.setValue(cita.getServicio());
        this.btnAgendar.setText("Editar Cita");
    }
    
    private void llenarComboBoxEmpleado(){
        List<Empleado> empleados = logicaNegocio.obtenerEmpleados();
        this.cboPeluquero.getItems().addAll(empleados);
        this.cboPeluquero.setValue("--Selecciona--");
    }
    
    private void llenarComboBoxClientes(){
        List<Cliente> clientes = logicaNegocio.obtenerClientes();
        this.cboClientes.getItems().addAll(clientes);
        this.cboClientes.setValue("--Selecciona--");
    }
    
    private void llenarComboBoxServicio(){
        List<Servicio> servicios = logicaNegocio.obtenerServicios();
        this.cboServicio.getItems().addAll(servicios);
        this.cboServicio.setValue("--Selecciona--");
    }
    
    private boolean validarCamposVacios(){
 
        String msjError = "";
        
        msjError = this.validarComboBoxs(msjError);
        msjError = this.validarFechas(msjError);
           
        if(msjError != ""){
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setContentText(msjError);
            alertError.showAndWait();
        }

        return msjError.equals("");
        
    }
    
    public String validarComboBoxs(String msjError){
        if(this.cboPeluquero.getValue().equals("--Selecciona--")){
            msjError += "No se ha seleccionado un peluquero\n";
        }
        if(this.cboClientes.getValue().equals("--Selecciona--")){
            msjError += "No se ha seleccionado ningun cliente\n";
        }
        if(this.cboServicio.getValue().equals("--Selecciona--")){
            msjError += "No se ha seleccionado ningun servicio\n";
        }
        return msjError;
    }
    
    public String validarFechas(String msjError){
        LocalTime fechaFin = null;
        LocalTime fechaInicio = null;
        
        if(this.dtpFechaCita.getValue() == null){
            msjError += "No se ha seleccionado ninguna fecha \n";
        }
        
        if(this.txtHoraInicio.getText().equals("")){
            msjError += "No se ha seleccionado una hora de inicio \n";
            return msjError;
        }else{
            String hora = this.txtHoraInicio.getText(); 
            String patron = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"; 
            Pattern pattern = Pattern.compile(patron); 
            Matcher matcher = pattern.matcher(hora); 
            if(!matcher.matches()){
                msjError += "Formato de hora de inicio inválido \n";
                return msjError;
            }else{
                fechaInicio = LocalTime.parse(this.txtHoraInicio.getText());
            }
        }
        
        if(this.txtHoraFin.getText().equals("")){
            msjError += "No se ha seleccionado una hora de fin \n";
            return msjError;
        }else{
            String hora = this.txtHoraFin.getText(); 
            String patron = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"; 
            Pattern pattern = Pattern.compile(patron); 
            Matcher matcher = pattern.matcher(hora); 
            if(!matcher.matches()){
                msjError += "Formato de hora de fin inválido \n";
                return msjError;
            }else{
                fechaFin = LocalTime.parse(this.txtHoraFin.getText());
            }
        }
        
        if(fechaInicio != null && fechaFin != null){
            if(fechaInicio.isAfter(fechaFin)){
                msjError += "La fecha de inicio no puede estar después de la de fin \n";
            }
        }
        
        if(this.getFecha(this.txtHoraInicio.getText()).isBefore(LocalDateTime.now()) || this.getFecha(this.txtHoraFin.getText()).isBefore(LocalDateTime.now())){
            msjError += "No se puede agendar una cita que ya pasó \n";
        }
        
        return msjError;
    }
    
    public LocalDateTime getFecha(String hora){
        String horasMinutos[]= hora.split(":");
        return dtpFechaCita.getValue().atTime(Integer.parseInt(horasMinutos[0]), Integer.parseInt(horasMinutos[1]));
    }
    
    
    public void guardarCita(){   
        
        if(!validarCamposVacios()){
            return;
        }
        
        Empleado peluquero = (Empleado) this.cboPeluquero.getValue();
        Cliente cliente = (Cliente) this.cboClientes.getValue();
        Servicio servicio = (Servicio) this.cboServicio.getValue();
        Cita nuevaCita = null;
        if(cita != null){
            nuevaCita = this.logicaNegocio.obtenerCita(cita.getId());
        } else{
            nuevaCita = new Cita();
        }
        
        nuevaCita.setCliente(cliente);
        nuevaCita.setEmpleado(peluquero);
        
        LocalDateTime fechaDesde = getFecha(this.txtHoraInicio.getText());
        LocalDateTime fechaHasta = getFecha(this.txtHoraFin.getText());

        //Se le suma un segundo para que no se empalme con la hora de
        // terminacion de la cita anterior
        fechaDesde = fechaDesde.plus(Duration.ofSeconds(1));
        
        nuevaCita.setFechaInicio(fechaDesde);
        nuevaCita.setFechaFin(fechaHasta);

        nuevaCita.setServicio(servicio);
        Usuario u= new Usuario();
        u.setId(1L);
        nuevaCita.setUsuario(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setResizable(false);
        if(cita != null){
            nuevaCita.setId(cita.getId());
            try {
                this.logicaNegocio.actualizarCita(nuevaCita);
            } catch (Exception ex) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                return;
            }
            alert.setContentText("Se realizaron cambios correctamente");
            alert.showAndWait();
            
            showAdministrarCitas();
        } else{
            try {
                logicaNegocio.agregarCita(nuevaCita);
            } catch (Exception ex) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                return;
            }
            alert.setContentText("Se agregó la cita correctamente");
            this.vaciarForm();
            alert.showAndWait();
        }
    }
    
    public void cerrarPantalla(){
        Stage stage  = (Stage) this.btnAgendar.getScene().getWindow();
        stage.close();
    }
    
    public void showAdministrarCitas(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/primary.fxml"));
        this.cerrarPantalla();
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void vaciarForm(){
        this.dtpFechaCita.setValue(LocalDate.now());
        this.cboClientes.setValue("--Selecciona--");
        this.cboPeluquero.setValue("--Selecciona--");
        this.cboServicio.setValue("--Selecciona--");
        this.txtHoraFin.setText("");
        this.txtHoraInicio.setText("");
    }
    
}