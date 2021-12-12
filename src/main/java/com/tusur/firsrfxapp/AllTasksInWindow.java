package com.tusur.firsrfxapp;

import javafx.scene.control.ButtonBase;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class AllTasksInWindow {
    private TaskAndOntions[] TaskClass; // Блоки с данными по каждому заданию

    private final TextArea[] TaskField; // Все поля label с выводом заданий
    private final Button[][] OptionsFields; // по 4 кнопки для каждого задания. первое измерение - кол-во заданий, второе - кол-во кнопок в задании (всегда 4)

    private int[] ChosenAnswers; // Массив выбранных пользователем вариантов ответа (может и не стоит int делать, но без БД - хер его знает)
    private final int TasksCount; // Количество вопросов
    private int ShowedTask; // Показываемое задание
    private static final int OptionsCount = 4; // Кол-во вариантов ответа (всегда 4)
    private final int ShowedTasksCount; // Кол-во одновременно выводимых заданий (равно общему кол-ву заданий)

    AllTasksInWindow(TextArea TaskField[], Button[][] OptionsFields, int ShowedTasksCount){
        this.TaskField = TaskField;
        this.OptionsFields = OptionsFields;
        //this.OptionsCount = OptionsCount; // Кол-во вариантов ответа (всегда 4)
        this.ShowedTasksCount = ShowedTasksCount;

        TasksCount = 12; // типо получил кол-во вопросов
        ChosenAnswers = new int[TasksCount];


        // Настроить все поля label
        for (int i = 0; i < TaskField.length; i++)
        {
            TaskField[i].setStyle("-fx-text-fill: #005aae ;") ;
            TaskField[i].setEditable(false);
            TaskField[i].setPrefColumnCount(120);
            TaskField[i].setPrefRowCount(60);
        }

        // запрос к бд, получение кол-ва вопросов и вопросов

        TaskClass = new TaskAndOntions[TasksCount];

        ShowedTask = 0;

        // Каждому TaskClass надо назначить свои вопросы, варианты ответа и места где они будут выводиться
        // Вариант Егора, где все вопросы в разных местах выводятся
        if (TaskField.length > 1)
        {
            for (int i = 0; i < TasksCount ; i++)
            {
                TaskClass[i] = new TaskAndOntions("Задание " + (i+1) + " сделайте то-то", "делаю 1 (вопрос " + (i+1) + ")",
                        "делаю 2 (вопрос " + (i+1) + ")", "делаю 3 (вопрос " + (i+1) + ")", "делаю 4 (вопрос " + (i+1) + ")", TaskField[i], OptionsFields[i]);
                ChosenAnswers[i] = -1; // Типо не выбран ответ
            }
        }
        // Мой вариант, где все вопросы в одних и тех же местах выводятся
        else
        {
            for (int i = 0; i < TasksCount ; i++)
            {
                TaskClass[i] = new TaskAndOntions("Задание " + (i+1) + " сделайте то-то", "делаю 1 (вопрос " + (i+1) + ")",
                        "делаю 2 (вопрос " + (i+1) + ")", "делаю 3 (вопрос " + (i+1) + ")", "делаю 4 (вопрос " + (i+1) + ")", TaskField[0], OptionsFields[0]);
                ChosenAnswers[i] = -1; // Типо не выбран ответ
            }
        }

        showTask(0);
    }

    // нет обработки нажатий на кнопки с вариантами ответа

    public int getShowedTask() {
        return this.ShowedTask;
    }

    void setChosenOption(Button PushedBTM, int ChosenAns){
        for (int i = 0; i < OptionsCount; i++)
        {
            OptionsFields[0][i].setStyle("-fx-background-color: #005aae");
        }
        PushedBTM.setStyle("-fx-background-color: green");
        TaskClass[ShowedTask].setChosenOptionNum(ChosenAns);

        ChosenAnswers[ShowedTask] = ChosenAns;

    }

    // Получить кол-во заданий в окне
    public int getTasksCount(){
        return this.TasksCount;
    }

    // Вывести предыдущее задание
    public boolean showNextTask()
    {
        if(ShowedTask < TasksCount - 1)
        {
            showTask(++ShowedTask);
            //OptionsFields[0][ShowedTask].setStyle("-fx-background-color: green");
            return false;
        }
        /*for (int i = 0; i < TasksCount ; i++)
        {
            System.out.printf(ChosenAnswers[ShowedTask] + "\n");
        }*/
        return true;
    }

    // Вывести следующее задание
    public boolean showPrevTask()
    {
        if(ShowedTask > 0)
        {
            showTask(--ShowedTask);
            return false;
        }

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

        //System.out.printf(ShowedTask + "\n");

        for (int i = 0; i < OptionsCount; i++)
        {
            OptionsFields[0][i].setStyle("-fx-background-color: #005aae");
        }

        /*if (ChosenAnswers[ShowedTask] != -1)
        {
            OptionsFields[0][ChosenAnswers[ShowedTask]].setStyle("-fx-background-color: green");
        }*/

        for (int i = 0; i < TasksCount; i++)
        {
            System.out.printf(ChosenAnswers[i] + "\t");
        }
        System.out.printf("\n");

        if (ChosenAnswers[ShowedTask] != -1)
        {
            OptionsFields[0][ChosenAnswers[ShowedTask]-1].setStyle("-fx-background-color: green");
        }

        /*TaskField.setText(TaskClass[ShowedTask].getTask());
        for (int i = 0; i < this.OptionsCount; i++)
        {
            OptionsFields[i].setText(TaskClass[ShowedTask].getOption(i));
        }
        */
        TaskClass[ShowedTask].showTask();
        return false;
    }

    // Вывести все задания в разных элементах, для Егора
    public boolean showAllTask(){
        if (ShowedTasksCount > 1)
        {
            for (int i = 0; i < ShowedTasksCount; i++)
            {
                TaskClass[i].showTask();
            }
            return false;
        }
        return  true;
    }

}
