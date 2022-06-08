package com.example.taqueria;

import com.example.taqueria.Base.AlimentoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Button btnBorrar;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1550, 550);
        stage.setResizable(false);
        stage.setTitle("Taqueria");
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }
}