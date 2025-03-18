package org.example.rotacerta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuInicialApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuInicialApplication.class.getResource("MenuInicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1720, 980);
        stage.setTitle("RotaCerta");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}