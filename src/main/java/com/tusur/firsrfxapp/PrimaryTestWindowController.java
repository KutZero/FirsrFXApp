package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class PrimaryTestWindowController extends BaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane CircleArea;

    @FXML
    private Label ExitBTM;

    @FXML
    private TextArea FirstOptionArea;

    @FXML
    private TextArea FourthOptionArea;

    @FXML
    private Label NextTaskBTM;

    @FXML
    private Label PrevTaskBTM;

    @FXML
    private TextArea SecondOptionArea;

    @FXML
    private Label StoryDescriptorLabel;

    @FXML
    private TextArea TaskArea;

    @FXML
    private TextArea ThirdOptionArea;

    private BDController DBControlForModuleWindow; // Экземпляр класса для работы с бд в этом окне
    private static final int OptionsCount = 4; // Кол-во вариантов ответов
    private int TasksCount; // Общее кол-во заданий
    private AllTasksInWindow TasksInWindow; // Класс для работы с заданиями в окне


    private void setMouseClickedHandler (TextArea CurrentArea) {
        CurrentArea.setOnMouseClicked(mouseEvent -> {
            TasksInWindow.setChosenOption(CurrentArea);
        });
    }

    @FXML
    void initialize() {
        ///////////////////////////////////////////////////////////////////////////////////////////////
        // Тут надо получить кол-во вопросов
        DBControlForModuleWindow = new BDController();
        TasksCount = DBControlForModuleWindow.getTasksCount();
        // Тут надо заполнить массив вопросами и ответами
        String[][] TempForTasks = new String[TasksCount][4];

        /////////////////////////////////////////////////////////////////////////////////////////////////

        //CircleIndicator CircleIndArr = new CircleIndicator(CircleCount, CircleArea);
        // Запрос к базе, получение кол-ва вопросов

        // рисование нужного кол-ва элементов в которых будут отображаться задания
        TextArea[][] OptionsArray = new TextArea[][] {{FirstOptionArea, SecondOptionArea, ThirdOptionArea, FourthOptionArea}};
        TextArea[] FakeTextArea = new TextArea[]{TaskArea};
        FakeTextArea[0].setEditable(false);

        //FakeTextArea[0].setDisable(true);
        //FakeTextArea[0].setFocusTraversable(false);
        //Label[] FakeTextArea = new Label[]{TaskArea};
        // рисование нужного кол-ва элементов в которых будут отображаться задания

        TasksInWindow = new AllTasksInWindow(FakeTextArea, OptionsArray, CircleArea,
                TasksCount, StoryDescriptorLabel, "Вопрос # ",  TempForTasks);

        TasksInWindow.showTask(0);
        PrevTaskBTM.setStyle("-fx-text-fill: #93979C;");

        // Установить обработчик нажатий всех кнопок
        for (int j = 0; j < OptionsArray[0].length; j++)
        {
            OptionsArray[0][j].setEditable(false);
            setMouseClickedHandler(OptionsArray[0][j]);
        }

        NextTaskBTM.setOnMouseClicked(mouseEvent -> {
            // меняются вопросы и варианты ответов
            TasksInWindow.showNextTask(PrevTaskBTM, NextTaskBTM, "primary_test_result_window.fxml", "любая нужная тебе строка для записи в нужную таблицу");
        });

        PrevTaskBTM.setOnMouseClicked(mouseEvent -> {
            // меняются вопросы и варианты ответов
            TasksInWindow.showPrevTask(PrevTaskBTM, NextTaskBTM, "primary_test_result_window.fxml", "любая нужная тебе строка для записи в нужную таблицу");
        });

        ExitBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("main_window.fxml").Show();
        });

    }

}
