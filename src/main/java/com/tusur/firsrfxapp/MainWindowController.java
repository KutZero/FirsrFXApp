package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MainWindowController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CoordinatorBTM;

    @FXML
    private Button GoToFinalTestBTM;

    @FXML
    private Button GoToPrimaryTestBTM;

    @FXML
    void initialize() {
        GoToPrimaryTestBTM.setOnMouseClicked(mouseEvent -> {
            // к первичному тесту
        });

        CoordinatorBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("educate_module_window.fxml").Show();
        });

    }
}