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
    void initialize() throws SQLException {
        LoginField.setPromptText("Имя пользователя");
        PasswordField.setPromptText("Пароль");
        BDController DBControlForModuleWindow = new BDController();
        Connection connectDB = DBControlForModuleWindow.getConnection();
        Statement statement = connectDB.createStatement();//функции и переменные для работы с БД
        SingInBTM.setOnMouseClicked(mouseEvent -> {//Нажатие на кнопку "Вход"
            if(DBControlForModuleWindow.GetLogin(statement, LoginField.getText(), PasswordField.getText()) != -1) {//проверка логина и пароля

                Main.getNavigation().load("welcome_window.fxml").Show();//Удача: вход в профиль
            }
            else//Неудача: очищение строк "Логин" и "Пароль"
            {
                LoginField.clear();
                PasswordField.clear();
            }
        });

        SingUpBTM.setOnMouseClicked(mouseEvent -> {//Нажатие на кнопку "Регистрация"
            Main.getNavigation().load("sing_up_window.fxml").Show();
        });
    }

}
