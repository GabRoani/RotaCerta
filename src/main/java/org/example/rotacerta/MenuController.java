package org.example.rotacerta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuController {

    @FXML
    private Label RotaCerta;

    @FXML
    protected void onNovaViagemClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("TelaNovaViagem.fxml"));
        Scene novaViagem = new Scene(fxmlLoader.load(), 1720, 980);
        Stage stage0 = new Stage();
        stage0.setTitle("RotaCerta");
        stage0.setScene(novaViagem);

        stage0.show();
    }
    @FXML
    protected void onViagemAnteriorClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("TelaViagemAnterior.fxml"));
        Scene viagemAnterior = new Scene(fxmlLoader.load(), 1720, 980);
        Stage stage1 = new Stage();
        stage1.setTitle("RotaCerta");
        stage1.setScene(viagemAnterior);

        stage1.show();
    }
    @FXML
    protected void onViagemAndamentoClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("TelaViagemAtual.fxml"));
        Scene viagemAndamento = new Scene(fxmlLoader.load(), 1720, 980);
        Stage stage2 = new Stage();
        stage2.setTitle("RotaCerta");
        stage2.setScene(viagemAndamento);

        stage2.show();
    }
    @FXML
    protected void onCadastroClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("TelaCadastro.fxml"));
        Scene sair = new Scene(fxmlLoader.load(), 1720, 980);
        Stage stage3 = new Stage();
        stage3.setTitle("RotaCerta");
        stage3.setScene(sair);

        stage3.show();
    }
}