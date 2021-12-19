package com.tusur.firsrfxapp;

import javafx.scene.Node;

public interface Controller {
    Node getView();
    void setView(Node view);
    void Show();
}
