package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SingInWindowController {

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



    }

}
