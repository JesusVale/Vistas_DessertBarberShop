package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Cita;
import com.roberto_rw.entidades.Cliente;
import com.roberto_rw.entidades.Empleado;
import com.roberto_rw.entidades.Servicio;
import com.roberto_rw.entidades.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
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

//    @FXML
//    private DateTimePicker horaFin;
//
//    @FXML
//    private DateTimePicker horaIn;

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
    
    public void guardarCita(){
        if(this.cboPeluquero.getValue().equals("--Selecciona--")){
            //Alerta de que no se ha seleccionado un Peluquero
        }
        if(this.cboClientes.getValue().equals("--Selecciona--")){
            //Alerta de que no se ha seleccionado un clientes
        }
        if(this.cboServicio.getValue().equals("--Selecciona--")){
            //Alerta de que no se ha seleccionado un servicio
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
        
        String horaInicio= txtHoraInicio.getText();
        String horasMinutosInicio[]= horaInicio.split(":");
        String horaFin= txtHoraFin.getText();
        String horasMinutosFin[]= horaFin.split(":");
        LocalDateTime fechaDesde= dtpFechaCita.getValue().atTime(Integer.parseInt(horasMinutosInicio[0]), Integer.parseInt(horasMinutosInicio[1]));
        LocalDateTime fechaHasta= dtpFechaCita.getValue().atTime(Integer.parseInt(horasMinutosFin[0]), Integer.parseInt(horasMinutosFin[1]));
        
        nuevaCita.setFechaInicio(fechaDesde);
        nuevaCita.setFechaFin(fechaHasta);
        
//        cita.setFechaFin(this.horaFin.getDateTimeValue());
//        cita.setFechaInicio(this.horaIn.getDateTimeValue());
        nuevaCita.setServicio(servicio);
        Usuario u= new Usuario();
        u.setId(1L);
        nuevaCita.setUsuario(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setResizable(false);
        if(cita != null){
            nuevaCita.setId(cita.getId());
            System.out.println(nuevaCita.getId());
            System.out.println(nuevaCita.getEmpleado());
            this.logicaNegocio.actualizarCita(nuevaCita);
            alert.setContentText("Se realizaron cambios correctamente");
            alert.showAndWait();
            
            showAdministrarCitas();
        } else{
            logicaNegocio.agregarCita(nuevaCita);
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