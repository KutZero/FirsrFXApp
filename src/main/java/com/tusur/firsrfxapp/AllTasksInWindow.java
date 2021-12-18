package com.tusur.firsrfxapp;

import javafx.scene.control.*;

public class AllTasksInWindow {
    private TaskAndOntions[] TaskClass; // Блоки с данными по каждому заданию

    private final Label[] TaskField; // Все поля label с выводом заданий
    private final Button[][] OptionsFields; // по 4 кнопки для каждого задания. первое измерение - кол-во заданий, второе - кол-во кнопок в задании (всегда 4)

    private int[] ChosenAnswers; // Массив выбранных пользователем вариантов ответа (может и не стоит int делать, но без БД - хер его знает)
    private final int TasksCount; // Количество вопросов
    private int ShowedTask; // Показываемое задание
    private static final int OptionsCount = 4; // Кол-во вариантов ответа (всегда 4)
    private final int ShowedTasksCount; // Кол-во одновременно выводимых заданий (равно общему кол-ву заданий)

    // как то надо еще передать массив вопросов полученных через класс BDController в выбранном окне
    AllTasksInWindow(Label TaskField[], Button[][] OptionsFields, int ShowedTasksCount, int TasksCount){
        this.TaskField = TaskField;
        this.OptionsFields = OptionsFields;
        this.ShowedTasksCount = ShowedTasksCount;
        this.TasksCount = TasksCount; // типо получил кол-во вопросов
        ChosenAnswers = new int[TasksCount];

        // Настроить все поля label
        for (int i = 0; i < TaskField.length; i++)
        {
            TaskField[i].setStyle("-fx-text-fill: #005aae ;") ;
        }

        TaskClass = new TaskAndOntions[TasksCount];

        ShowedTask = 0; // Отображаемое сейчас задание (только если одновременно выводится только 1 задание)

        // Каждому TaskClass надо назначить свои вопросы, варианты ответа и места где они будут выводиться
        // Вариант Егора, где все вопросы в разных местах выводятся (для каждого вопроса свои поля и все вопросы выводятся одновременно)
        if (this.ShowedTasksCount > 1)
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
            //showTask(0);
        }

        //

    }

    /*public int getShowedTask() {
        return this.ShowedTask;
    }*/

    // Главное во втором измерении массива OptionsFields расположить кнопки в правильном порядке (смотри нумерацию в окне EducateModule)
    void setChosenOption(Button PushedBTM){

        for (int i = 0; i < OptionsFields.length; i++)
        {
            for (int j = 0; j < OptionsFields[i].length; j++)
            {
                OptionsFields[i][j].setStyle("-fx-background-color: #005aae");
                if (PushedBTM == OptionsFields[i][j])
                {
                    // задание номер i + 1
                    // выбранный ответ j + 1
                    OptionsFields[i][j].setStyle("-fx-background-color: green");
                    if (ShowedTasksCount == 1)
                    {
                        // Все задания имеют одинаковые поля вывода
                        TaskClass[ShowedTask].setChosenOptionNum(j+1); //ChosenAns
                        ChosenAnswers[ShowedTask] = j+1;//ChosenAns;
                    }
                    else
                    {
                        // Все задания имеют разные поля вывода
                        TaskClass[i].setChosenOptionNum(j+1);
                        ChosenAnswers[i] = j+1;
                    }
                }
            }
        }

        /*void setChosenOption(Button PushedBTM, int ChosenAns){

            for (int i = 0; i < OptionsFields.length; i++)
            {
                for (int j = 0; j < OptionsFields[i].length; j++)
                {
                    OptionsFields[i][j].setStyle("-fx-background-color: #005aae");
                    if (PushedBTM == OptionsFields[i][j])
                    {
                        // задание номер i + 1
                        // выбранный ответ j + 1
                        OptionsFields[i][j].setStyle("-fx-background-color: green");
                        if (ShowedTasksCount == 1)
                        {
                            // Все задания имеют одинаковые поля вывода
                            TaskClass[ShowedTask].setChosenOptionNum(j+1); //ChosenAns
                            ChosenAnswers[ShowedTask] = j+1;//ChosenAns;
                        }
                        else
                        {
                            // Все задания имеют разные поля вывода
                            TaskClass[i].setChosenOptionNum(ChosenAns);
                            ChosenAnswers[i] = ChosenAns;
                        }
                    }
                }
            }*/

        /*for (int j = 0; j < OptionsCount; j++)
        {
            OptionsFields[0][j].setStyle("-fx-background-color: #005aae");
        }

        PushedBTM.setStyle("-fx-background-color: green");*/


        //askClass[ShowedTask].setChosenOptionNum(ChosenAns);

        //ChosenAnswers[ShowedTask] = ChosenAns;

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
            return false;
        }

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

        // Вывод выбранных ответов для каждого задания
        /*for (int i = 0; i < TasksCount; i++)
        {
            System.out.printf(ChosenAnswers[i] + "\t");
        }
        System.out.printf("\n");*/

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
