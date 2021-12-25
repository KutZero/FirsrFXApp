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
    void initialize() throws SQLException {
       WelcomeText.setEditable(false);
        WelcomeText.setWrapText(true);
        BDController DBControlForModuleWindow = new BDController();
        Connection connectDB = DBControlForModuleWindow.getConnection();
        Statement statement = connectDB.createStatement();//функции и переменные для работы с БД
        String Name = "придумай как вставить активного пользователя";//Имя активного пользователя
        //Замена текста приветственного окна с учётом имени пользователя
        WelcomeText.setText("Здравствуйте," +Name+"! Вас приветствует тренажер по усвоению компетенции Kompetenzen. Вам предоставляется возможность проверить себя на знание компетенции УК-3: Способен осуществлять социальное взаимодействие и реализовывать свою роль в команде. Начните с прохождения первичного теста, нажав на кнопку “Пройти первичный тест” ниже. После прохождения первичного теста Вы сможете воспользоваться тренажером на знание ролей и понимание социальных взаимодействий членов команды, а также пройти финальный контрольный тест. Удачи! ");

        GoToMainMenuBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("main_window.fxml").Show();//перейти сразу в меню
        });

        GoToPrimaryTestBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("primary_test_window.fxml").Show();//начать прохождение первичного теста
        });

    }
}
