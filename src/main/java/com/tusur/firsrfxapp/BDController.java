package com.tusur.firsrfxapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
