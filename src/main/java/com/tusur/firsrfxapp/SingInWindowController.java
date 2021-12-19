package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SingInWindowController extends BaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasswordField;

    @FXML
    private Button SingInBTM;

    @FXML
    private Button SingUpBTM;

    @FXML
    void initialize() {
        LoginField.setPromptText("Имя пользователя");
        PasswordField.setPromptText("Пароль");

        SingInBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("welcome_window.fxml").Show();
        });

        SingUpBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("sing_up_window.fxml").Show();
        });
    }

}
