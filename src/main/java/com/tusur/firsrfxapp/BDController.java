package com.tusur.firsrfxapp;

// класс для работы с базой данных
public class BDController {
    private int TaskCount; // Кол-во вопросов в окне (типо из ббд получено)

    BDController(){
        TaskCount = 8;
    }

    public int getTasksCount(){
        return this.TaskCount;
    }
}
