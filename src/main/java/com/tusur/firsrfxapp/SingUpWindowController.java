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
    void initialize() throws SQLException {
        LoginField.setPromptText("Имя пользователя");
        PasswordField.setPromptText("Пароль");
        NameField.setPromptText("Имя");
        LastNameField.setPromptText("Фамилия");
        PatronymicField.setPromptText("Отчество");//Установка фонового текста для полей заполнения данных
        BDController DBControlForModuleWindow = new BDController();
        Connection connectDB = DBControlForModuleWindow.getConnection();
        Statement statement = connectDB.createStatement();//функции и переменные для работы с БД
        GoToSingInBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().GoBack();//возврат в меню
        });

        SingUpBTM.setOnMouseClicked(mouseEvent -> {//Вставка нового пользователя в БД
            DBControlForModuleWindow.AddUser(statement, LoginField.getText(), PasswordField.getText(),NameField.getText(),LastNameField.getText(),PatronymicField.getText());
            Main.getNavigation().load("welcome_window.fxml").Show();//переход в приветственное окно
        });

    }

}
