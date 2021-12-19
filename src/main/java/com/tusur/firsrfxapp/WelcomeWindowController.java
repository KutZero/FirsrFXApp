package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class WelcomeWindowController extends BaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label GoToMainMenuBTM;

    @FXML
    private Button GoToPrimaryTestBTM;

    @FXML
    private TextArea WelcomeText;

    @FXML
    void initialize() {
        WelcomeText.setEditable(false);

        GoToMainMenuBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("main_window.fxml").Show();
        });


    }

}
