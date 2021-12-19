package com.tusur.firsrfxapp;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class CircleIndicator{
    /*private final int number;
    private Circle Object;

    public int getNumber(){
        return number;
    }

    public CircleIndicator(double x, double y, double radius, int num){
        Object = new Circle(x,y, radius);
        this.number = num;
    }

    public void setFill(Paint paint) {
        Object.setFill(paint);
    }

    public Circle getObject() {
        return Object;
    }*/

    private final Circle[] Circles;
    private int ActiveCircle;
    private final int CircleRadius = 5; // Радиус кружков индикатора
    private final int CircleOffsetFac = 15; // Отступ между кружками индикатора
    private final AllTasksInWindow TasksInWindow;

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

    private void setMouseClickedHandler (Circle CurrentCircle) {
        CurrentCircle.setOnMouseClicked(mouseEvent -> {

            Circles[ActiveCircle].setFill(Paint.valueOf("#c5c5c5"));
            CurrentCircle.setFill(Paint.valueOf("#005aae"));
            ActiveCircle = Integer.parseInt(CurrentCircle.getId());
            TasksInWindow.showTask(ActiveCircle);
        });
    }

    // Конструктор
    CircleIndicator(int CirclesCount, Pane CircleArea, AllTasksInWindow TasksInWindow){
        this.TasksInWindow = TasksInWindow;
        Circles = new Circle[CirclesCount];
        ActiveCircle = 0;
        CircleArea.setPrefWidth(CirclesCount * CircleOffsetFac - CircleRadius);
        for (int i = 0; i < CirclesCount; i++)
        {
            Circles[i] = new Circle(CircleOffsetFac * i + CircleRadius,CircleRadius * 2, CircleRadius);
            Circles[i].setFill(Paint.valueOf("#c5c5c5"));
            Circles[i].setId(Integer.toString(i));
            setMouseClickedHandler (Circles[i]);
            CircleArea.getChildren().add(Circles[i]);
        }
        Circles[ActiveCircle].setFill(Paint.valueOf("#005aae"));
    }
}
