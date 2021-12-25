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
    void initialize() throws SQLException {
        BDController DBControlForModuleWindow = new BDController();
        Connection connectDB = DBControlForModuleWindow.getConnection();
        Statement statement = connectDB.createStatement();
        String DataBaseName = DBControlForModuleWindow.getDataBaseName();//функции и переменные для работы с БД
        int Right = DBControlForModuleWindow.GetRightStory1(statement, DBControlForModuleWindow, DataBaseName);//получение правильных ответов пользователя
        ResultField.setText("Правильных ответов: "+Right+"/"+DBControlForModuleWindow.getTasksCount(statement));//текст с результатами
        DBControlForModuleWindow.UpdateStory1Status(statement, DataBaseName, Right);//обновление информации о прохождении истории
        GoToMenuBTM.setOnMouseClicked(mouseEvent -> {//выход в главное меню
            Main.getNavigation().load("main_window.fxml").Show();
        });
        //
    }
}
