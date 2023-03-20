package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Cita;
import com.roberto_rw.entidades.Cliente;
import com.roberto_rw.entidades.Empleado;
import com.roberto_rw.entidades.Servicio;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;
import tornadofx.control.DateTimePicker;

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
    private DateTimePicker horaFin;

    @FXML
    private DateTimePicker horaIn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Xd");
        
    }
    
    public void initComponents(Cita cita){
        
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
        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setEmpleado(peluquero);
        cita.setFechaFin(this.horaFin.getDateTimeValue());
        cita.setFechaInicio(this.horaIn.getDateTimeValue());
        cita.setServicio(servicio);
        logicaNegocio.agregarCita(cita);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Información");
        alert.setContentText("Se agregó la cita correctamente");
        alert.setResizable(false);
        alert.show();
    }
    
    
    
}