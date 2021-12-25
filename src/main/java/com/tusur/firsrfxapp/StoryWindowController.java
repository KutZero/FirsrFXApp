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

/*import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;*/

public class StoryWindowController extends BaseController{

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
    private String DataBaseName;
    private AllTasksInWindow TasksInWindow; // Класс для работы с заданиями в окне


    private void setMouseClickedHandler (TextArea CurrentArea) {
        CurrentArea.setOnMouseClicked(mouseEvent -> {
            TasksInWindow.setChosenOption(CurrentArea);
        });
    }

    @FXML
    void initialize() throws SQLException {

       //Данный контроллер аналогично по смыслу PrimaryTestWindowController


        BDController DBControlForModuleWindow = new BDController();
        Connection connectDB = DBControlForModuleWindow.getConnection();
        Statement statement = connectDB.createStatement();
        TasksCount = DBControlForModuleWindow.getTasksCount(statement);
        String[][] TempForTasks = new String[TasksCount][5];
        DataBaseName = DBControlForModuleWindow.getDataBaseName();
        // Тут надо заполнить массив вопросами и ответами
         for(int i = 0; i<TasksCount; i++)
        {
            try
            {
                String Question_text = "SELECT Story FROM " +DataBaseName+".stories where NUM = "+(i+1);
                ResultSet Question_output = statement.executeQuery(Question_text);
                while (Question_output.next())
                {
                    String text = Question_output.getString("Story");
                    TempForTasks[i][0] = text;
                    System.out.println("1Подстановка вопроса успешна");
                }
            }
            catch (Exception exp)
            {
                System.out.println("Подстановка вопроса не выполнена, проверьте запрос");
            }
            for (int j = 0; j<4; j++)
            {
                try
                {
                    String Question_text = "SELECT otvet FROM " +DataBaseName+".otvetsstories where numOtv = "+(j+1)+" AND NumStory = "+(i+1);
                    ResultSet Answer_output = statement.executeQuery(Question_text);
                    while (Answer_output.next())
                    {
                        String text = Answer_output.getString("otvet");
                        TempForTasks[i][j+1] = text;
                        System.out.println("Подстановка ответа успешна");
                    }
                }
                catch (Exception exp)
                {
                    System.out.println("Подстановка ответа не выполнена, проверьте запрос");
                }
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////

        // Запрос к базе, получение кол-ва вопросов

        // рисование нужного кол-ва элементов в которых будут отображаться задания

        TextArea[][] OptionsArray = new TextArea[][] {{FirstOptionArea, SecondOptionArea, ThirdOptionArea, FourthOptionArea}};
        TextArea[] FakeTextArea = new TextArea[]{TaskArea};
        FakeTextArea[0].setEditable(false);


        TasksInWindow = new AllTasksInWindow(FakeTextArea, OptionsArray, CircleArea,
                TasksCount, StoryDescriptorLabel, "Координатор история ",  TempForTasks);

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
            TasksInWindow.showNextTask(PrevTaskBTM, NextTaskBTM, "story_result_window.fxml",
                    "stories");
        });

        PrevTaskBTM.setOnMouseClicked(mouseEvent -> {
            // меняются вопросы и варианты ответов
            TasksInWindow.showPrevTask(PrevTaskBTM, NextTaskBTM, "primary_test_result_window.fxml",
                    "stories");
        });

        ExitBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("main_window.fxml").Show();
        });

    }

}
