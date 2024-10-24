package org.example.rotacerta.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.rotacerta.MenuApplication;

import java.io.IOException;

public class CadastroUserApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("TelaCadastro.fxml"));
            Scene cadastro = new Scene(fxmlLoader.load(), 1720, 980);
            Stage stage3 = new Stage();
            stage3.setTitle("RotaCerta");
            stage3.setScene(cadastro);

            stage3.show();
        }

    public static void main(String[] args) {
        launch();
    }
}