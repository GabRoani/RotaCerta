package org.example.rotacerta.screens;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.rotacerta.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroUserController {

    @FXML    private TextField viajanteField;
    @FXML
    private TextField senhaField;
    @FXML
    private Button cadastrarButton;

    @FXML
    protected void onCadastrarButtonClick() {
        String viajante = viajanteField.getText();
        String senha = senhaField.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO viajante (nomeviajante, senhaviajante) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, viajante);
            stmt.setString(2, senha);
            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
