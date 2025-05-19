package com.niccolo.memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/niccolo/memory/MemoryGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.show();
    }
*/
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/niccolo/memory/MemoryGame.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Memory Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // <<< stampa l’errore sulla console
        }
    }

    public static void main(String[] args) {
        System.out.println(App.class.getResource("/com/niccolo/memory/MemoryGame.fxml"));
        launch();
    }
}