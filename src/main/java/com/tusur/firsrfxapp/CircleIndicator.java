package com.tusur.firsrfxapp;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class CircleIndicator{//класс для создания и взаимодействия с кружками в нижней части приложения

    private final Circle[] Circles; //массив кружков
    private int ActiveCircle; //номер активного кружка
    private final int CircleRadius = 5; // Радиус кружков индикатора
    private final int CircleOffsetFac = 15; // Отступ между кружками индикатора
    private final AllTasksInWindow TasksInWindow; //количество заданий

    public boolean setNextActiveCircle(){ //переключение на следующий кружок
        if (ActiveCircle < Circles.length - 1)
        {
            Circles[ActiveCircle].setFill(Paint.valueOf("#c5c5c5")); //смена цвета на серый
            ActiveCircle++;//переход на следующий
            Circles[ActiveCircle].setFill(Paint.valueOf("#005aae")); //синий цвет у активного кружка
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

    private void setMouseClickedHandler (Circle CurrentCircle) { //обработка нажатия на активный кружок
        CurrentCircle.setOnMouseClicked(mouseEvent -> {

            Circles[ActiveCircle].setFill(Paint.valueOf("#c5c5c5"));
            CurrentCircle.setFill(Paint.valueOf("#005aae"));
            ActiveCircle = Integer.parseInt(CurrentCircle.getId());
            TasksInWindow.showTask(ActiveCircle);
        });
    }

    // Конструктор
    CircleIndicator(int CirclesCount, Pane CircleArea, AllTasksInWindow TasksInWindow){ //конструктор класса, создающий кружки, в зависимости от количества вопросв
        this.TasksInWindow = TasksInWindow;
        Circles = new Circle[CirclesCount];
        ActiveCircle = 0;
        CircleArea.setPrefWidth(CirclesCount * CircleOffsetFac - CircleRadius);
        for (int i = 0; i < CirclesCount; i++)//создание
        {
            Circles[i] = new Circle(CircleOffsetFac * i + CircleRadius,CircleRadius * 2, CircleRadius); //создание, использууя параметры размеров
            Circles[i].setFill(Paint.valueOf("#c5c5c5"));
            Circles[i].setId(Integer.toString(i));
            setMouseClickedHandler (Circles[i]);
            CircleArea.getChildren().add(Circles[i]);//добавление кружка в область
        }
        Circles[ActiveCircle].setFill(Paint.valueOf("#005aae"));
    }
}

