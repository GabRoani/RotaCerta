package org.example.rotacerta.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.rotacerta.MenuApplication;

import java.io.IOException;

public class ViagemAtualApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("TelaViagemAtual.fxml"));
        Scene viagemAndamento = new Scene(fxmlLoader.load(), 1720, 980);
        Stage stage2 = new Stage();
        stage2.setTitle("RotaCerta");
        stage2.setScene(viagemAndamento);

        stage2.show();
    }
    public static void main(String[] args) {
        launch();
    }
}


