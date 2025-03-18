package org.example.rotacerta.screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.example.rotacerta.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class NovaViagemController {

    @FXML
    private TextField kmLitroField, kmRotaField, precoCombustivelField, statusField;
    @FXML
    private DatePicker dataViagemPicker;
    @FXML
    private TextField horaViagemField;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField resultadoField;

    public void inserirViagem() {
        try {
            double kmLitro = Double.parseDouble(kmLitroField.getText());
            double kmRota = Double.parseDouble(kmRotaField.getText());
            double precoCombustivel = Double.parseDouble(precoCombustivelField.getText());
            LocalDate dataViagem = dataViagemPicker.getValue();
            LocalTime horaViagem = LocalTime.parse(horaViagemField.getText());
            String status = statusField.getText();

            String sql = "INSERT INTO Viagem (KmLitro, KmRota, PrecoCombustivel, DataViagem, HoraViagem, Status) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setDouble(1, kmLitro);
                pstmt.setDouble(2, kmRota);
                pstmt.setDouble(3, precoCombustivel);
                pstmt.setDate(4, java.sql.Date.valueOf(dataViagem));
                pstmt.setTime(5, java.sql.Time.valueOf(horaViagem));
                pstmt.setString(6, status);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    statusLabel.setText("Viagem inserida com sucesso!");
                } else {
                    statusLabel.setText("Falha na inserção da viagem.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            statusLabel.setText("Erro de banco de dados.");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Erro ao inserir dados.");
        }
    }

    public void calcularviagem(ActionEvent actionEvent) {
        try {
            double kmLitro = Double.parseDouble(kmLitroField.getText());
            double kmRota = Double.parseDouble(kmRotaField.getText());
            double precoCombustivel = Double.parseDouble(precoCombustivelField.getText());

            double custoTotal = (kmRota / kmLitro) * precoCombustivel;
            resultadoField.setText(String.format("Custo Total: %.2f", custoTotal));
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Verifique os valores inseridos.");
            resultadoField.clear();
        }
    }
}
