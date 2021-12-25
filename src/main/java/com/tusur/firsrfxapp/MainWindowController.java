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
        GoToPrimaryTestBTM.setOnMouseClicked(mouseEvent -> {//вывод сцены с первичнымм тестом по нажатию кнопки
            Main.getNavigation().load("primary_test_window.fxml").Show();
        });

        CoordinatorBTM.setOnMouseClicked(mouseEvent -> {//вывод сцены с историей по нажатию кнопки
            Main.getNavigation().load("story_window.fxml").Show();
        });

    }
}
