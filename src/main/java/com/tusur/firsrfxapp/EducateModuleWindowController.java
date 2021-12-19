package com.tusur.firsrfxapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class EducateModuleWindowController extends BaseController{

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

    @FXML
    private Circle[] Circles;


    //private final int NumOfOptions = 4; // Количество кнопок с вариантами ответов
    private BDController DBControlForModuleWindow; // Экземпляр класса для работы с бд в этом окне
    private static final int OptionsCount = 4; // Кол-во вариантов ответов
    private int TasksCount; // Общее кол-во заданий
    private AllTasksInWindow TasksInWindow; // Класс для работы с заданиями в окне

    // Класс для обработки индикатора выбранного задания
    /*private class CircleIndicator{
        private final Circle[] Circles;
        private int ActiveCircle;

        public boolean setNextActiveCircle(){
            if (ActiveCircle < Circles.length - 1)
            {
                Circles[ActiveCircle].setFill(Paint.valueOf("#c5c5c5"));
                ActiveCircle++;
                Circles[ActiveCircle].setFill(Paint.valueOf("#005aae"));
                return false;
            }
            return true; // Выбран последний вопрос
        }

        public void setActiveCircle(int index){
            Circles[ActiveCircle].setFill(Paint.valueOf("#c5c5c5"));
            ActiveCircle = index;
            Circles[ActiveCircle].setFill(Paint.valueOf("#005aae"));
        }

        public boolean setPrevActiveCircle(){
            if (ActiveCircle > 0)
            {
                Circles[ActiveCircle].setFill(Paint.valueOf("#c5c5c5"));
                ActiveCircle--;
                Circles[ActiveCircle].setFill(Paint.valueOf("#005aae"));
                return false;
            }
            return true; // Выбран первый вопрос
        }

        // Конструктор
        CircleIndicator(int CirclesCount){
            Circles = new Circle[CirclesCount];
            ActiveCircle = 0;
            for (int i = 0; i < CirclesCount; i++)
            {
                Circles[i] = new Circle(CircleOffsetFac * i + CircleRadius,CircleRadius * 2, CircleRadius);
                Circles[i].setFill(Paint.valueOf("#c5c5c5"));
                CircleArea.getChildren().add(Circles[i]);
            }
            Circles[ActiveCircle].setFill(Paint.valueOf("#005aae"));
        }
    }*/

    private void setMouseClickedHandler (TextArea CurrentArea) {
        CurrentArea.setOnMouseClicked(mouseEvent -> {
            TasksInWindow.setChosenOption(CurrentArea);
        });
    }

    @FXML
    void initialize() {

        // Запрос к базе, получение кол-ва вопросов
        DBControlForModuleWindow = new BDController();
        TasksCount = DBControlForModuleWindow.getTasksCount();


        final int CircleCount = TasksCount;
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
                TasksCount, StoryDescriptorLabel, "Координатор история ");
        TasksInWindow.showTask(0);
        PrevTaskBTM.setStyle("-fx-text-fill: #93979C;");

        // Установить обработчик нажатий всех кнопок
        for (int j = 0; j < OptionsArray[0].length; j++)
        {
            //OptionsArray[0][j].setOnMouseClicked(this.BuiltMouseEvent(MainGridPane));
            OptionsArray[0][j].setEditable(false);
            //OptionsArray[0][j].setDisable(true);
            setMouseClickedHandler(OptionsArray[0][j]);
        }

        NextTaskBTM.setOnMouseClicked(mouseEvent -> {
            // меняются вопросы и варианты ответов
            TasksInWindow.showNextTask(PrevTaskBTM, NextTaskBTM);
            //CircleIndArr.setNextActiveCircle();
        });

        PrevTaskBTM.setOnMouseClicked(mouseEvent -> {
            // меняются вопросы и варианты ответов
            TasksInWindow.showPrevTask(PrevTaskBTM, NextTaskBTM);
            //CircleIndArr.setPrevActiveCircle();
        });

        ExitBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("main_window.fxml").Show();
        });

        /*FirstOptionBTM.setOnMouseClicked(mouseEvent -> {
            TasksInWindow.setChosenOption(FirstOptionBTM);
        });

        SecondOptionBTM.setOnMouseClicked(mouseEvent -> {
            TasksInWindow.setChosenOption(SecondOptionBTM);
        });

        ThirdOptionBTM.setOnMouseClicked(mouseEvent -> {
            TasksInWindow.setChosenOption(ThirdOptionBTM);
        });

        FourthOptionBTM.setOnMouseClicked(mouseEvent -> {
            TasksInWindow.setChosenOption(FourthOptionBTM);
        });*/

        /*Label testLabel = new Label("Width " + CircleArea.getWidth() + " Height " + CircleArea.getHeight());
        testLabel.setScaleX(2);
        testLabel.setScaleY(2);
        MainGridPane.add(testLabel,2,1,3,1);*/

        //FirstOptionBTM.setStyle("-fx-background-color: green");

        // final Label testLabel = new Label("Хуйня");
        //testLabel.setId("TestRow");

        //Circle cir = new Circle(20,Color.BLUE);
        //MainGridPane.add(testLabel,1,1);
        //GridPane.setValignment(testLabel, VPos.CENTER);

        //Circle myCircle = new Circle(20,20,40, Color.YELLOW);
        //MainPane.getChildren().add(myCircle);

    }

}
