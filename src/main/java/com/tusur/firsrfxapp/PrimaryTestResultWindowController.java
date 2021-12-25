package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

public class PrimaryTestResultWindowController extends BaseController{

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
   void initialize() throws SQLException {
        BDController DBControlForModuleWindow = new BDController();
        Connection connectDB = DBControlForModuleWindow.getConnection();
        Statement statement = connectDB.createStatement();
        String DataBaseName = DBControlForModuleWindow.getDataBaseName();//функции для работы с БД
        int Right = DBControlForModuleWindow.GetRightAnswers(statement, DBControlForModuleWindow, DataBaseName);//Получение правильных ответов пользователя
        ResultField.setText("Правильных ответов: "+Right+"/"+DBControlForModuleWindow.GetPrimaryQuestionCount(statement));//Текст поля с результами
        DBControlForModuleWindow.UpdatePrimaryStatus(statement, DataBaseName, Right);//изменение у пользователя результата прохождения теста
        GoToMenuBTM.setOnMouseClicked(mouseEvent -> { //выход в главное меню
            Main.getNavigation().load("main_window.fxml").Show();
        });
    }

}
