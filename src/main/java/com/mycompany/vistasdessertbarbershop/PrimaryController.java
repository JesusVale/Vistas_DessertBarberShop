package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Cita;
import com.roberto_rw.entidades.Empleado;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;
/*import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;*/

public class PrimaryController implements Initializable {
    
    
    public ILogicaNegocio logicaNegocio = new LogicaNegocio();
    
    @FXML
    private ComboBox<Object> peluqueroCbx;
    
    @FXML
    private Button buscarBtn;
    
    @FXML
    private Button btnCasa;
    
    @FXML
    private HBox buscadorBox;
    
    @FXML
    private VBox citasBox;

    @FXML
    private Label citasLbl;

    @FXML
    private Label dateLbl;

    @FXML
    private Button leftBtn;

    @FXML
    private Button rightBtn;
    
    @FXML
    private Button nuevaCitaBtn;
    
    @FXML
    private Button btnCancelarBusqueda;
    
    @FXML
    private TextField buscarTxt;
    
    private LocalDateTime fechaActual = LocalDateTime.now();
    
    private Empleado empleado;
    
    private String buscador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.llenarComboBoxEmpleados();
        this.llenarVBox(logicaNegocio.obtenerCitasPorPeriodo(fechaActual.with(LocalTime.MIN), fechaActual.with(LocalTime.MAX)));
        dateLbl.setText(formatter.format(LocalDateTime.now()));
        /*btnCancelarBusqueda.setVisible(false);*/
        buscadorBox.getChildren().remove(btnCancelarBusqueda);
    }
    
    
    @FXML
    private void switchToSecondary() throws IOException {
        
        App.setRoot("secondary");
    }
    
    
    private void llenarComboBoxEmpleados(){
        List<Empleado> empleados = logicaNegocio.obtenerEmpleados();
        this.peluqueroCbx.getItems().addAll(empleados);
        this.peluqueroCbx.setValue("--Selecciona--");
        
    }
    
    
    private void llenarVBox(List<Cita> citas){
        this.citasBox.getChildren().clear();
        for (Cita cita: citas){
            citasBox.getChildren().add(getHBoxCita(cita));
        }
    }
    
    private HBox getHBoxCita(Cita cita){
        HBox hBox = new HBox();
        
        hBox.setStyle("-fx-background-color: white;");
        
        hBox.setSpacing(40);
        
        hBox.setPadding(new Insets(5, 30,5, 30));
        
        hBox.setAlignment(Pos.CENTER_LEFT);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        
        Label labelFechas = new Label();
        labelFechas.setText(formatter.format(cita.getFechaInicio())+ " - "+formatter.format(cita.getFechaFin())+": "+cita.getCliente().getNombre()+" - ");
        
        Label labelPeluquero = new Label(cita.getEmpleado().getNombre());
        
        labelFechas.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        labelPeluquero.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        
        Label emptyLabel = new Label();
        
        emptyLabel.setPrefWidth(Double.MAX_VALUE);
        
        //Botones
        
        HBox botones = new HBox();
        
        botones.setSpacing(12);
        botones.setAlignment(Pos.CENTER_LEFT);
        
        FontAwesomeIconView iconScissors = new FontAwesomeIconView();
        iconScissors.setGlyphName("SCISSORS");
        
        FontAwesomeIconView iconEdit = new FontAwesomeIconView();
        iconEdit.setGlyphName("EDIT");
        
        FontAwesomeIconView iconDelete = new FontAwesomeIconView();
        iconDelete.setGlyphName("TRASH");
        
        Button botonEliminar = new Button();
        Button botonEditar = new Button();
        iconEdit.setSize("1.5em");
        iconDelete.setSize("1.5em");
        iconScissors.setSize("1.5em");
        botonEditar.setGraphic(iconEdit);
        
        botonEliminar.setGraphic(iconDelete);
        labelPeluquero.setGraphic(iconScissors);
        
        botonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                eliminarCita(cita);
            }
        });
        
        botonEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                editarCita(cita);
            }
        });
        
        
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        LocalDate hoy = LocalDate.now();
        LocalDate fechaCita = cita.getFechaInicio().toLocalDate();

        if (fechaCita.isAfter(hoy) || fechaCita.isEqual(hoy)) {
            botones.getChildren().add(botonEditar);
            botones.getChildren().add(botonEliminar);
        }
              
        hBox.getChildren().add(labelFechas);
        hBox.getChildren().add(labelPeluquero);
        hBox.getChildren().add(r);
        hBox.getChildren().add(botones);
        
        
        
        return hBox;
    }
    
    public void eliminarCita(Cita cita){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Está seguro que desea eliminar esta cita?");
        
        ButtonType botonSi = new ButtonType("Sí");
        ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(botonSi, botonNo);

        Optional<ButtonType> resultado = alert.showAndWait();
        
        if(resultado.get() == botonSi){
            this.logicaNegocio.eliminarCita(cita);
            Alert alertaInfo = new Alert(Alert.AlertType.INFORMATION);
            alertaInfo.setTitle("Información");
            alertaInfo.setContentText("Se eliminó la cita correctamente");
            alertaInfo.setResizable(false);
            alertaInfo.show();
            this.llenarVBox(logicaNegocio.obtenerCitasPorPeriodo(fechaActual.with(LocalTime.MIN), fechaActual.with(LocalTime.MAX)));
        }
        
    }
    
    public void editarCita(Cita cita){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/CitaFrm.fxml"));
        try {
            Parent root = loader.load();
            CitaFrmController controlador = loader.getController();
            controlador.initComponents(cita);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            cerrarPantalla();
            
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buscarCitas(){
        this.buscador = this.buscarTxt.getText();
        
        List<Cita> citasEncontradas =logicaNegocio.obtenerCitasPorCliente(this.buscarTxt.getText());
        this.llenarVBox(this.logicaNegocio.obtenerCitasPorEmpleadoClienteFecha(fechaActual, empleado, buscador));
        if(!buscadorBox.getChildren().contains(btnCancelarBusqueda)){
            buscadorBox.getChildren().add(btnCancelarBusqueda);
        }
    }
    
    public void cancelarBusqueda(){
        this.buscarTxt.setText("");
        buscadorBox.getChildren().remove(btnCancelarBusqueda);
        this.peluqueroCbx.setValue("--Selecciona--");
        this.empleado = null;
        this.buscador = null;
        this.llenarVBox(this.logicaNegocio.obtenerCitasPorEmpleadoClienteFecha(fechaActual, empleado, buscador));
        
    }
    
    public void mostrarPantallaEmpleados(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/AdministrarClientesFrm.fxml"));
        try{
            Parent root= loader.load();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage stage2= (Stage) this.btnCasa.getScene().getWindow();
            stage2.close();
        }catch(IOException ex){
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarCitasEmpleado(){
        
        if(this.peluqueroCbx.getValue().equals("--Selecciona--")){
            this.empleado = null;
            this.llenarVBox(this.logicaNegocio.obtenerCitasPorEmpleadoClienteFecha(fechaActual, empleado, buscador));
            return;
        }
        
        if(!buscadorBox.getChildren().contains(btnCancelarBusqueda)){
            buscadorBox.getChildren().add(btnCancelarBusqueda);
        }

        Empleado empleadoSeleccionado = (Empleado) this.peluqueroCbx.getValue();
        
        this.empleado = empleadoSeleccionado;
        this.llenarVBox(this.logicaNegocio.obtenerCitasPorEmpleadoClienteFecha(fechaActual, empleado, buscador));
        
    }
    
    public void mostrarPantallaCita(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/CitaFrm.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            cerrarPantalla();
            
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void cerrarPantalla(){
        Stage stage  = (Stage) this.nuevaCitaBtn.getScene().getWindow();
        stage.close();
    }
    
    public void moverHaciaFechaAnterior(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime fechaAnterior = fechaActual.minusDays(1);

        
        dateLbl.setText(formatter.format(fechaAnterior));

        fechaActual = fechaAnterior; // guardar la fecha anterior en la variable de instancia
        this.llenarVBox(this.logicaNegocio.obtenerCitasPorEmpleadoClienteFecha(fechaActual, empleado, buscador));

    }

    public void moverHaciaFechaSiguiente(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime fechaSiguiente = fechaActual.plusDays(1);

        dateLbl.setText(formatter.format(fechaSiguiente));

        fechaActual = fechaSiguiente; // guardar la fecha anterior en la variable de instancia
        this.llenarVBox(this.logicaNegocio.obtenerCitasPorEmpleadoClienteFecha(fechaActual, empleado, buscador));
    }

}
