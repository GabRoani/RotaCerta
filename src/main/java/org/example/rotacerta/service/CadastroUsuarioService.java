package org.example.rotacerta.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.rotacerta.MenuInicialApplication;
import org.example.rotacerta.config.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroUsuarioService {

    @FXML
    private TextField UsernameCadastroField;

    @FXML
    private PasswordField PasswordCadastroField;

    @FXML
    private TextField EmailCadastroField;

    @FXML
    private TextField CPFCadastroField;

    @FXML
    private Label statusLabel;

    // Lista estática para armazenar IDs de viagem
    private static List<Integer> viagemIds = new ArrayList<>();

    // Método para carregar IDs de viagem apenas uma vez
    public static void carregarViagemIds() {
        // Só consulta o banco se a lista estiver vazia
        if (viagemIds.isEmpty()) {
            String sql = "SELECT idViagem FROM viagem";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                // Armazena cada idViagem na lista
                while (rs.next()) {
                    viagemIds.add(rs.getInt("idViagem"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obter a lista de IDs de viagem
    public static List<Integer> getViagemIds() {
        // Garante que os IDs estejam carregados antes de retornar a lista
        carregarViagemIds();
        return viagemIds;
    }

    public void CadastroUsuarioOnClick() {
        try {
            String nome = UsernameCadastroField.getText();
            String senha = PasswordCadastroField.getText();
            String email = EmailCadastroField.getText();
            String cpf = CPFCadastroField.getText();

            if (nome.isEmpty() || senha.isEmpty() || email.isEmpty()) {
                statusLabel.setText("Por favor, preencha todos os campos.");
                return;
            }

            String sql = "INSERT INTO pessoa (Nome, Senha, Email, cpf) VALUES (?, ?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, nome);
                pstmt.setString(2, senha);
                pstmt.setString(3, email);
                pstmt.setString(4, cpf);
                pstmt.executeUpdate();

                statusLabel.setText("Usuário cadastrado com sucesso!");
            }
        } catch (Exception e) {
            statusLabel.setText("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void VoltarMenuOnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuInicialApplication.class.getResource("MenuInicial.fxml"));
        Scene novaViagem = new Scene(fxmlLoader.load(), 1720, 980);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(novaViagem);
        stage.setTitle("RotaCerta");
        stage.show();
    }
}
