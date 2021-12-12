package com.tusur.firsrfxapp;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

// Класс, содержащий задание, варианты ответа и выбранный вариант ответа для одного задания
public class TaskAndOntions {
    private final String Task; // Текст задания
    private final String[] Options; // Текст каждого варианта ответа
    private int ChosenOptionNum; // Номер выбранного варианта ответа
    private static final int OptionsCount = 4; // Кол-во вариантов ответа

    // Для этого конкретного задания
    private TextArea TaskField; // Место, где выводится текст задания
    private Button[] OptionsFields; // Массив элементов с вариантами ответа

    TaskAndOntions(String Task, String op1, String op2, String op3, String op4, TextArea TaskField, Button[] OptionsFields) {
        this.Task = Task;
        this.Options = new String[OptionsCount];
        this.Options[0] = op1;
        this.Options[1] = op2;
        this.Options[2] = op3;
        this.Options[3] = op4;
        ChosenOptionNum = -1; // Не выбран ответ

        this.TaskField = TaskField;
        this.OptionsFields = OptionsFields;
    }

    public void setChosenOptionNum(int ChosenOptionNum){
        this.ChosenOptionNum = ChosenOptionNum;
    }

    public int getChosenOptionNum(){
        return this.ChosenOptionNum;
    }

    public String getTask()
    {
        return this.Task;
    }

    public String getOption(int index)
    {
        return this.Options[index];
    }

    public void showTask()
    {
        TaskField.setText(Task);
        for (int i = 0; i < 4; i++)
        {
            OptionsFields[i].setText(Options[i]);
        }
    }
}
