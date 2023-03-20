package com.mycompany.vistasdessertbarbershop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

public class CitaFrmController {

    @FXML
    private Button btnCasa;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}