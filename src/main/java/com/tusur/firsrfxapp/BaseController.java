package com.tusur.firsrfxapp;

import javafx.scene.Node;

public class BaseController implements Controller {

    private Node view;

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void setView (Node view){
        this.view = view;
    }

    @Override
    public void Show() {
        PreShowing();
        Main.getNavigation().Show(this);
        PostShowing();
    }

    public void PreShowing()
    {
        System.out.printf("pre\n");
    }

    public void PostShowing()
    {
        System.out.printf("post\n");
    }
}
