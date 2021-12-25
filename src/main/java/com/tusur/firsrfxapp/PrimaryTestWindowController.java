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
        BDController DBControlForModuleWindow = new BDController();
        Connection connectDB = DBControlForModuleWindow.getConnection();
        Statement statement = connectDB.createStatement();
        String DataBaseName = DBControlForModuleWindow.getDataBaseName();//функции и переменные для работы с БД
        TasksCount = DBControlForModuleWindow.GetPrimaryQuestionCount(statement);
        // Тут надо заполнить массив вопросами и ответами
        String[][] TempForTasks = new String[TasksCount][5]; //получение количества вопросов из БД
        for(int i = 0; i<TasksCount; i++)
        {
            try//запрос текста вопроса
            {
                String Question_text = "SELECT Vopros FROM " +DataBaseName+".question where NUM = "+(i+1);
                ResultSet Question_output = statement.executeQuery(Question_text);
                while (Question_output.next())
                {
                    String text = Question_output.getString("Vopros");
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
                try//запрос текста 4-х ответо на соответствующий вопрос
                {
                    String Question_text = "SELECT Otvettxt FROM " +DataBaseName+".otvetsperq where num_otv = "+(j+1)+" AND NUM_VOPR = "+(i+1);
                    ResultSet Answer_output = statement.executeQuery(Question_text);
                    while (Answer_output.next())
                    {
                        String text = Answer_output.getString("otvettxt");
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

        NextTaskBTM.setOnMouseClicked(mouseEvent -> {//следующий вопрос
            // меняются вопросы и варианты ответов
            TasksInWindow.showNextTask(PrevTaskBTM, NextTaskBTM, "primary_test_result_window.fxml", "pertest");//в данной функции имеется возможность выбрать таблицу, в которую будет вписаные данные. Именно название таблицы. Название БД уже будет вставлено
        });

        PrevTaskBTM.setOnMouseClicked(mouseEvent -> {//предыдущий вопрос
            // меняются вопросы и варианты ответов
            TasksInWindow.showPrevTask(PrevTaskBTM, NextTaskBTM, "primary_test_result_window.fxml", "pertest");
        });

        ExitBTM.setOnMouseClicked(mouseEvent -> {
            Main.getNavigation().load("main_window.fxml").Show();//кнопка выхода в меню
        });
    }

}
