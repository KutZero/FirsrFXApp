package com.tusur.firsrfxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    private static Navigation navigation;

    public static Navigation getNavigation()
    {
        return navigation;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //sing_up_window.fxml
        //sing_in_window.fxml
        //welcome_window.fxml
        //main_window.fxml
        //educate_module_window.fxml

        navigation = new Navigation(stage);

        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/tusur/firsrfxapp/fxmlFiles/sing_in_window.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 1140, 688);

        stage.setTitle("Kompetenzen");
        //stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        navigation.load("sing_in_window.fxml").Show();

    }

    public static void main(String[] args) {
        launch();
    }
}