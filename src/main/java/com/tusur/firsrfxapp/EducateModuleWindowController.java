package com.tusur.firsrfxapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;

public class EducateModuleWindowController {

    @FXML
    private Pane CircleArea;

    @FXML
    private Label ExitBTM;

    @FXML
    private GridPane MainGridPane;

    @FXML
    private Label NextTaskBTM;

    @FXML
    private Label PrevTaskBTM;

    @FXML
    private TextArea TaskArea;

    @FXML
    private Button FirstOptionBTM;

    @FXML
    private Button SecondOptionBTM;

    @FXML
    private Button ThirdOptionBTM;

    @FXML
    private Button FourthOptionBTM;

    @FXML
    private Circle[] Circles;

    private final int CircleRadius = 5; // Радиус кружков индикатора
    private final int CircleOffsetFac = 15; // Отступ между кружками индикатора
    //private final int NumOfOptions = 4; // Количество кнопок с вариантами ответов
    private BDController DBControlForModuleWindow; // Экземпляр класса для работы с бд в этом окне
    private static final int OptionsCount = 4; // Кол-во вариантов ответов
    private int TasksCount; // Общее кол-во заданий
    private AllTasksInWindow TasksInWindow; // Класс для работы с заданиями в окне

    // Класс для обработки индикатора выбранного задания
    private class CircleIndicator{
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
    }

    // Класс содержащий поля вопроса и 4 вариантов для 1 задания
    /*private class TaskAndOntions{
        private final String Task;
        private final String[] Options;
        private int ChosenOptionNum;

        TaskAndOntions(String Task, String op1, String op2, String op3, String op4){
            this.Task = Task;
            this.Options = new String[4];
            this.Options[0] = op1;
            this.Options[1] = op1;
            this.Options[2] = op1;
            this.Options[3] = op1;
            ChosenOptionNum = -1; // Не выбран ответ
        }

        public void setChosenOptionNum(int ChosenOptionNum){
            this.ChosenOptionNum = ChosenOptionNum;
        }

        public void setActive()
        {
            FirstOptionBTM.setText(this.Options[0]);
            SecondOptionBTM.setText(this.Options[1]);
            ThirdOptionBTM.setText(this.Options[2]);
            FourthOptionBTM.setText(this.Options[3]);

        }
    }*/

    // Установить обработчик нажатий выбранной кнопоки
    private void setMouseClickedHandler (Button CurrentButton) {
        CurrentButton.setOnMouseClicked(mouseEvent -> {
            TasksInWindow.setChosenOption(CurrentButton);
        });

    }

    @FXML
    void initialize() {

        // Запрос к базе, получение кол-ва вопросов
        DBControlForModuleWindow = new BDController();
        TasksCount = DBControlForModuleWindow.getTasksCount();

        // Запрос к базе, получение кол-ва вопросов

        // рисование нужного кол-ва элементов в которых будут отображаться задания
        Button[][] OptionsArray = new Button[][] {{FirstOptionBTM, SecondOptionBTM, ThirdOptionBTM, FourthOptionBTM}};
        TextArea[] FakeTextArea = new TextArea[]{TaskArea};
        // рисование нужного кол-ва элементов в которых будут отображаться задания

        TasksInWindow = new AllTasksInWindow(FakeTextArea, OptionsArray, 1, TasksCount);

        final int CircleCount = TasksCount;

        CircleArea.setPrefWidth(CircleCount * CircleOffsetFac - CircleRadius);
        CircleIndicator CircleIndArr = new CircleIndicator(CircleCount);

        TasksInWindow.showTask(0);

        /*for (int i = 0; i < Circles.length; i++)
        {
            //Circles[i] = new Circle(CircleOffsetFac * i + CircleRadius,CircleRadius * 2, CircleRadius);
            //Circles[i].setFill(Paint.valueOf("#c5c5c5"));

            //MainGridPane.add(Circles[i],1,5,3,1);
            //GridPane.setValignment(Circles[i], VPos.CENTER);
            //GridPane.setHalignment(Circles[i], HPos.CENTER);
            //GridPane.setMargin(Circles[i], new Insets(5,5,5,5));

            //CircleArea.getChildren().add(Circles[i]);

            //CircleArea.getChildren().add(CircleIndArr.getCircleArray()[i]);

        }*/

        //Circles[0].setFill(Paint.valueOf("#005aae"));

        // Установить обработчик нажатий всех кнопок
        for (int j = 0; j < OptionsArray[0].length; j++)
        {
            //OptionsArray[0][j].setOnMouseClicked(this.BuiltMouseEvent(MainGridPane));
            setMouseClickedHandler(OptionsArray[0][j]);
        }

        NextTaskBTM.setOnMouseClicked(mouseEvent -> {
            // меняются вопросы и варианты ответов
            TasksInWindow.showNextTask();
            CircleIndArr.setNextActiveCircle();
        });

        PrevTaskBTM.setOnMouseClicked(mouseEvent -> {
            // меняются вопросы и варианты ответов
            TasksInWindow.showPrevTask();
            CircleIndArr.setPrevActiveCircle();
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
