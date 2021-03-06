package com.tusur.firsrfxapp;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class AllTasksInWindow {
    private TaskAndOntions[] TaskClass; // Блоки с данными по каждому заданию

    private final TextArea[] TaskField; // Все поля label с выводом заданий
    private final TextArea[][] OptionsFields; // по 4 кнопки для каждого задания. первое измерение - кол-во заданий, второе - кол-во кнопок в задании (всегда 4)

    private int[] ChosenAnswers; // Массив выбранных пользователем вариантов ответа (может и не стоит int делать, но без БД - хер его знает)
    private final int TasksCount; // Количество вопросов
    private int ShowedTask; // Показываемое задание
    private static final int OptionsCount = 4; // Кол-во вариантов ответа (всегда 4)
    private final Label TaskDescriptor; // Место для отображения номера задания/ истории
    private String TaskDescriptorText; // Номер задания/истории
    private String DataBaseName;
    private final CircleIndicator CircleIndArr; // Индикаторы для заданий
   // private final int ShowedTasksCount; // Кол-во одновременно выводимых заданий (равно общему кол-ву заданий)

    
      
    BDController DBControlForModuleWindow = new BDController(); //Создание переменной класса базы данных для дальнейшей работы с ней
    Connection connectDB = DBControlForModuleWindow.getConnection(); //подключение к базе данных
    Statement statement = connectDB.createStatement(); //создание переменной для запросов
    String DataBaseName = DBControlForModuleWindow.getDataBaseName(); //получение названия базы данных для запросов
    
    // как то надо еще передать массив вопросов полученных через класс BDController в выбранном окне
    AllTasksInWindow(TextArea TaskField[], TextArea[][] OptionsFields, Pane CircleArea,
                     int TasksCount, Label TaskDescriptor, String TaskDescriptorText, String[][] TempForTasks) throws SQLException {
        this.TaskField = TaskField;
        this.OptionsFields = OptionsFields;
        this.TaskDescriptor = TaskDescriptor;
        //this.ShowedTasksCount = ShowedTasksCount;
        this.TasksCount = TasksCount; // типо получил кол-во вопросов
        ChosenAnswers = new int[TasksCount];

        CircleIndArr = new CircleIndicator(TasksCount, CircleArea, this); //создание круговых элементов в нижней части, в зависимости от количества вопросов

        this.TaskDescriptorText = TaskDescriptorText;
        this.TaskDescriptor.setText(TaskDescriptorText);

        TaskClass = new TaskAndOntions[TasksCount];

        ShowedTask = 0; // Отображаемое сейчас задание (только если одновременно выводится только 1 задание)

        // Каждому TaskClass надо назначить свои вопросы, варианты ответа и места где они будут выводиться

        //Заполнение поля вопроса и ответов текстом из базы данных
        for (int i = 0; i < TasksCount; i++)
        {

            // Норм заполнение
            TaskClass[i] = new TaskAndOntions(
                    TempForTasks[i][0],
                    TempForTasks[i][1],
                    TempForTasks[i][2],
                    TempForTasks[i][3],
                    TempForTasks[i][4],
                    TaskField[0], OptionsFields[0]);

            ChosenAnswers[i] = -1; // Типо не выбран ответ
        }
    }


    // Главное во втором измерении массива OptionsFields расположить кнопки в правильном порядке (смотри нумерацию в окне EducateModule)
    void setChosenOption(TextArea PushedArea){

        for (int i = 0; i < OptionsFields.length; i++)
        {
            for (int j = 0; j < OptionsFields[i].length; j++)
            {
                OptionsFields[i][j].setStyle("-fx-border-color: #C8C9CB;\n" +
                        "-fx-border-radius 3px;\n" +
                        "-fx-border-width: 0.5px;"); //задание визуальных параметров вариантам ответов
                if (PushedArea == OptionsFields[i][j])
                {

                    OptionsFields[i][j].setStyle("-fx-border-color: #005AAE;\n" +
                            "-fx-border-radius 3px;\n" +
                            "-fx-border-width: 2px;"); //изменение внешнего вида выбранного ответа
                    // Все задания имеют одинаковые поля вывода
                    TaskClass[ShowedTask].setChosenOptionNum(j+1); //ChosenAns
                    ChosenAnswers[ShowedTask] = j+1;//ChosenAns;

                }
            }
        }
    }

    // Вывести предыдущее задание
    public boolean showNextTask(Label PrevTaskBTM, Label NextTaskBTM, String NextWindow, String Query)
    {
        if (ShowedTask == TasksCount - 2) //когда доходим до последнего задания
        {
            NextTaskBTM.setText("Завершить тест"); //смена текста
            NextTaskBTM.setOnMouseClicked(mouseEvent -> { //н

                // Одномерный массив выбранных ответов (индекс - номер вопроса -1, значение - номер варианта ответа)
                //заполнение в базе данных вариантов ответа, которые выбрал пользователь
               for (int i = 0; i < ChosenAnswers.length; i++)
                {
                    try {
                        String PasteAsk = "INSERT INTO " + DataBaseName + ".stata_"+Query+"(Num, Num_student, Otvet, Num_popytki) VALUES (" + (i + 1) + "," + 1 + "," + ChosenAnswers[i] + "," + 1 + ")";
                        statement.executeUpdate(PasteAsk);
                        System.out.println("Вставка ответа успешна");
                    } catch (Exception exp) {
                        System.out.println("Вставка ответа не удалась, проверьте запрос");
                    }
                    System.out.printf(ChosenAnswers[i] + "\t");
                }

                Main.getNavigation().load(NextWindow).Show();//переход на следующее окно
            });
        }

        if(ShowedTask < TasksCount - 1)
        {
            if(ShowedTask == 0)
            {
                PrevTaskBTM.setStyle("-fx-text-fill: #005AAE;");
            }
            showTask(++ShowedTask);
            CircleIndArr.setNextActiveCircle();
            return false;
        }
        return true;
    }

    // Вывести следующее задание
    public boolean showPrevTask(Label PrevTaskBTM, Label NextTaskBTM, String NextWindow, String Query)
    {
        if(ShowedTask > 0)
        {
            if (ShowedTask == TasksCount - 1)
            {
                NextTaskBTM.setText("Следующий вопрос");
                NextTaskBTM.setOnMouseClicked(mouseEvent -> {
                    this.showNextTask(PrevTaskBTM, NextTaskBTM, NextWindow, Query);
                });
            }
            showTask(--ShowedTask);
            CircleIndArr.setPrevActiveCircle();
            return false;
        }
        PrevTaskBTM.setStyle("-fx-text-fill: #93979C;");
        return true;
    }

    // Вывести одно задание
    public boolean showTask(int TaskNum){
        if ((TaskNum < TasksCount) && (TaskNum >= 0))
        {
            ShowedTask = TaskNum;
        }
        else
        {
            return true;
        }

        TaskDescriptor.setText(TaskDescriptorText + (ShowedTask + 1));

        OptionsFields[0][0].requestFocus();

        for (int i = 0; i < OptionsCount; i++)
        {
            OptionsFields[0][i].setStyle(
                    "-fx-border-color: #C8C9CB;\n" +
                    "-fx-border-radius 3px;\n" +
                    "-fx-border-width: 0.5px;");
        }

        if (ChosenAnswers[ShowedTask] != -1)
        {
            OptionsFields[0][ChosenAnswers[ShowedTask]-1].requestFocus();
            OptionsFields[0][ChosenAnswers[ShowedTask]-1].setStyle(
                    "-fx-border-color: #005AAE;\n" +
                            "-fx-border-radius 3px;\n" +
                            "-fx-border-width: 2px;");
        }

        TaskClass[ShowedTask].showTask();
        return false;
    }

}
