package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

public class StoryResultWindowController extends BaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label GoToAnsBTM;

    @FXML
    private Label GoToMenuBTM;

    @FXML
    private Label ResultField;

    @FXML
    void initialize() {
        GoToMenuBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("main_window.fxml").Show();
        });
        //
    }

}