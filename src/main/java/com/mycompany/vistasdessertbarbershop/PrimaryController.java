package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Cita;
import com.roberto_rw.entidades.Empleado;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.llenarComboBoxEmpleados();
        this.llenarVBox(logicaNegocio.obtenerCitas());
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
            System.out.println(cita.getCategoria());
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
        labelFechas.setText(formatter.format(cita.getFechaInicio())+ " - "+formatter.format(cita.getFechaFin())+": "+cita.getCliente().getNombre());
        
        labelFechas.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        
        Label emptyLabel = new Label();
        
        emptyLabel.setPrefWidth(Double.MAX_VALUE);
        
        //Botones
        
        HBox botones = new HBox();
        
        botones.setSpacing(12);
        botones.setAlignment(Pos.CENTER_LEFT);
        
        FontAwesomeIconView iconEdit = new FontAwesomeIconView();
        iconEdit.setGlyphName("EDIT");
        
        FontAwesomeIconView iconDelete = new FontAwesomeIconView();
        iconDelete.setGlyphName("TRASH");
        
        Button botonEliminar = new Button();
        Button botonEditar = new Button();
        iconEdit.setSize("1.5em");
        iconDelete.setSize("1.5em");
        botonEditar.setGraphic(iconEdit);
        
        botonEliminar.setGraphic(iconDelete);
        
        botonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                eliminarCita(cita.getId());
            }
        });
        
        botonEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                editarCita(cita.getId());
            }
        });
        
        
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        
        botones.getChildren().add(botonEditar);
        botones.getChildren().add(botonEliminar);       
        hBox.getChildren().add(labelFechas);
        hBox.getChildren().add(r);
        hBox.getChildren().add(botones);
        
        
        
        return hBox;
    }
    
    public void eliminarCita(Long id){
        
    }
    
    public void editarCita(Long id){
        
    }
    
    public void buscarCitas(){
        this.buscarTxt.getText();
        
        List<Cita> citasEncontradas =logicaNegocio.obtenerCitasPorCliente(this.buscarTxt.getText());
        this.llenarVBox(citasEncontradas);
        buscadorBox.getChildren().add(btnCancelarBusqueda);
    }
    
    public void cancelarBusqueda(){
        this.buscarTxt.setText("");
        buscadorBox.getChildren().remove(btnCancelarBusqueda);
        this.peluqueroCbx.setValue("--Selecciona--");
        this.llenarVBox(logicaNegocio.obtenerCitas());
        
    }
    
    public void mostrarCitasEmpleado(){
        
        if(this.peluqueroCbx.getValue().equals("--Selecciona--")){
            return;
        }
        
        buscadorBox.getChildren().add(btnCancelarBusqueda);
        
        Empleado empleadoSeleccionado = (Empleado) this.peluqueroCbx.getValue();
        
        this.llenarVBox(this.logicaNegocio.obtenerCitasPorEmpleado(empleadoSeleccionado));
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

}
