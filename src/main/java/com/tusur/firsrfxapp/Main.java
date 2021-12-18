package com.tusur.firsrfxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //sing_up_window.fxml
        //sing_in_window.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/tusur/firsrfxapp/fxmlFiles/sing_in_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1140, 688);

        stage.setTitle("Kompetenzen");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}