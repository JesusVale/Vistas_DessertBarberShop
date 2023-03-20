package com.mycompany.vistasdessertbarbershop;

import java.io.IOException;
import javafx.fxml.FXML;

public class CitaFrmController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}