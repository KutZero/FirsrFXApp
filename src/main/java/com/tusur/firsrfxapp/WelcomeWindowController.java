package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BTMStartFirstTest;

    @FXML
    void initialize() {
        assert BTMStartFirstTest != null : "fx:id=\"BTMStartFirstTest\" was not injected: check your FXML file 'welcome-window.fxml'.";

    }

}