/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Cliente;
import com.roberto_rw.entidades.Empleado;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;

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
    
    public ILogicaNegocio logicaNegocio = new LogicaNegocio();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.llenarVBox(this.logicaNegocio.obtenerEmpleados());
    }
    
    public void accionVolver(){
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

            Stage stage2 = (Stage) this.btnVolver.getScene().getWindow();
            stage2.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MenuEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void llenarVBox(List<Empleado> empleados){
        this.boxEmpleados.getChildren().clear();
        for (Empleado empleado: empleados){
            boxEmpleados.getChildren().add(getHBoxEmpleado(empleado));
        }
    }
    
    
    private HBox getHBoxEmpleado(Empleado empleado){
        HBox hBox = new HBox();
        
        hBox.setStyle("-fx-background-color: white;");
        
        hBox.setSpacing(40);
        
        hBox.setPadding(new Insets(5, 30,5, 30));
        
        hBox.setAlignment(Pos.CENTER_LEFT);
        
        
        Label labelNom = new Label();
        labelNom.setText(empleado.getNombre()+" "+empleado.getApellido()+" - ");
        
        Label labelPuesto = new Label(empleado.getPuesto());
        
        labelNom.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        labelPuesto.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/AgregarEmpleadoFrm.fxml"));
                try {
                    Parent root = loader.load();
                    AgregarEmpleadoFrmController controlador= loader.getController();
                    controlador.initComponents(empleado, "eliminar");

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Eliminar Empleado");
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("./images/icono.png"));
                    stage.show();

                    Stage stage2 = (Stage) btnNuevoEmpleado.getScene().getWindow();
                    stage2.close();
                } catch (IOException ex) {
                    Logger.getLogger(AgregarEmpleadoFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        botonEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/AgregarEmpleadoFrm.fxml"));
                try {
                    Parent root = loader.load();
                    AgregarEmpleadoFrmController controlador= loader.getController();
                    controlador.initComponents(empleado, "editar");

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Editar Empleado");
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("./images/icono.png"));
                    stage.show();

                    Stage stage2 = (Stage) btnNuevoEmpleado.getScene().getWindow();
                    stage2.close();
                } catch (IOException ex) {
                    Logger.getLogger(AgregarEmpleadoFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);

        botones.getChildren().add(botonEditar);
        botones.getChildren().add(botonEliminar);


        hBox.getChildren().add(labelNom);
        hBox.getChildren().add(labelPuesto);
        hBox.getChildren().add(r);
        hBox.getChildren().add(botones);
        return hBox;
    }
    
    public void mostrarAgregarEmpleado(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/AgregarEmpleadoFrm.fxml"));
        try {
            Parent root = loader.load();
            AgregarEmpleadoFrmController controlador= loader.getController();
            controlador.initComponents(null, "agregar");
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar Empleado");
            stage.setResizable(false);
            stage.getIcons().add(new Image("./images/icono.png"));
            stage.show();

            Stage stage2 = (Stage) this.btnNuevoEmpleado.getScene().getWindow();
            stage2.close();
        } catch (IOException ex) {
            Logger.getLogger(AgregarEmpleadoFrmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
