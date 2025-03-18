package org.example.rotacerta.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.rotacerta.MenuInicialApplication;
import org.example.rotacerta.Model.idModel;
import org.example.rotacerta.config.DatabaseConnection;
import org.example.rotacerta.service.ListaViagemService;
import org.example.rotacerta.service.NovaViagemService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Menuinicial {

    @FXML
    private Label RotaCerta;

    @FXML
    private TextField cpfField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button simularViagemButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Button listarViagem;

    @FXML
    public ListView<idModel> viagemListView;

    @FXML
    protected void autenticarUsuario(ActionEvent event) {
        String cpf = cpfField.getText();
        String password = passwordField.getText();

        if (cpf.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Preencha todos os campos.");
            return;
        }

        int userId = 0;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT id FROM pessoa WHERE cpf = ? AND senha = ?")) {

            stmt.setString(1, cpf);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id");
                    statusLabel.setText("Login bem-sucedido!");

                    FXMLLoader loader = new FXMLLoader(MenuInicialApplication.class.getResource("TelaNovaViagem.fxml"));
                    Parent root = loader.load();

                    NovaViagemService novaViagemService = loader.getController();
                    novaViagemService.setUserActive(userId);

                    Scene scene = new Scene(root);
                    Stage stage = (Stage) simularViagemButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();

                } else {
                    statusLabel.setText("CPF ou senha incorretos.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            statusLabel.setText("Erro ao autenticar usuário.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void CadastroUsuarioOnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuInicialApplication.class.getResource("TelaCadastroUsuario.fxml"));
        Scene cadastroUsuario = new Scene(fxmlLoader.load(), 1720, 980);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(cadastroUsuario);
        stage.setTitle("RotaCerta");
        stage.show();
    }

    @FXML
    protected void SimularViagemOnClick(ActionEvent event) throws IOException {
        if (!simularViagemButton.isDisable()) {
            FXMLLoader fxmlLoader = new FXMLLoader(MenuInicialApplication.class.getResource("TelaNovaViagem.fxml"));
            Scene novaViagem = new Scene(fxmlLoader.load(), 1720, 980);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(novaViagem);
            stage.setTitle("RotaCerta");
            stage.show();
        }
    }
}
