package com.tusur.firsrfxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/tusur/firsrfxapp/fxmlFiles/educate_module_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 560);
        stage.setTitle("Educate Module");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}