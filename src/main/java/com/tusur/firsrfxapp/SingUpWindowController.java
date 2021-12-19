package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SingUpWindowController extends BaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label GoToSingInBTM;

    @FXML
    private TextField LastNameField;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField PatronymicField;

    @FXML
    private Button SingUpBTM;

    @FXML
    void initialize() {
        LoginField.setPromptText("Имя пользователя");
        PasswordField.setPromptText("Пароль");
        NameField.setPromptText("Имя");
        LastNameField.setPromptText("Фамилия");
        PatronymicField.setPromptText("Отчество");

        GoToSingInBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().GoBack();
        });

        SingUpBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("welcome_window.fxml").Show();
        });

    }

}
