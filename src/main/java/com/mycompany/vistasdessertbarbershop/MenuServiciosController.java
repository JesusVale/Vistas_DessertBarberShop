/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistasdessertbarbershop;

import com.roberto_rw.entidades.Servicio;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
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
public class MenuServiciosController implements Initializable{

    public ILogicaNegocio logicaNegocio = new LogicaNegocio();
    
    @FXML
    private Button btnCasa;
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private Button btnBuscarServicio;
    
    @FXML
    private Button btnNuevoServicio;
    
    @FXML
    private HBox buscadorBox;
    
    @FXML
    private TextField txtBuscarServicio;
    
    @FXML
    private VBox boxServicios;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.llenarVBox(this.logicaNegocio.obtenerServicios());
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
            Logger.getLogger(MenuServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void llenarVBox(List<Servicio> servicios){
        this.boxServicios.getChildren().clear();
        for (Servicio servicio: servicios){
            boxServicios.getChildren().add(getHBoxServicio(servicio));
        }
    }
    
    private HBox getHBoxServicio(Servicio servicio){
        HBox hBox = new HBox();
        
        hBox.setStyle("-fx-background-color: white;");
        
        hBox.setSpacing(40);
        
        hBox.setPadding(new Insets(5, 30,5, 30));
        
        hBox.setAlignment(Pos.CENTER_LEFT);
        
        
        Label labelDesc = new Label();
        labelDesc.setText(servicio.getDescripcion()+" - ");
        
        Label labelCat = new Label(servicio.getCategoria().toString());
        
        labelDesc.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        labelCat.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/AdministrarServiciosFrm.fxml"));
                try {
                    Parent root = loader.load();
                    AdministrarServiciosFrmController controlador= loader.getController();
                    controlador.initComponents(servicio, "eliminar");

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Eliminar Servicio");
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("./images/icono.png"));
                    stage.show();

                    Stage stage2 = (Stage) btnNuevoServicio.getScene().getWindow();
                    stage2.close();
                } catch (IOException ex) {
                    Logger.getLogger(MenuServiciosController.class.getName()).log(Level.SEVERE, null, ex);
                } 
                
            }
        });
        
        botonEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/AdministrarServiciosFrm.fxml"));
                try {
                    Parent root = loader.load();
                    AdministrarServiciosFrmController controlador= loader.getController();
                    controlador.initComponents(servicio, "editar");

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Editar Servicio");
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("./images/icono.png"));
                    stage.show();

                    Stage stage2 = (Stage) btnNuevoServicio.getScene().getWindow();
                    stage2.close();
                } catch (IOException ex) {
                    Logger.getLogger(MenuServiciosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);

        botones.getChildren().add(botonEditar);
        botones.getChildren().add(botonEliminar);


        hBox.getChildren().add(labelDesc);
        hBox.getChildren().add(labelCat);
        hBox.getChildren().add(r);
        hBox.getChildren().add(botones);
        
        
        
        return hBox;
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
            Logger.getLogger(MenuServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarAgregarServicio(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/vistasdessertbarbershop/AdministrarServiciosFrm.fxml"));
        try {
            Parent root = loader.load();
            AdministrarServiciosFrmController controlador= loader.getController();
            controlador.initComponents(null, "agregar");
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar Servicio");
            stage.setResizable(false);
            stage.getIcons().add(new Image("./images/icono.png"));
            stage.show();

            Stage stage2 = (Stage) this.btnNuevoServicio.getScene().getWindow();
            stage2.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
