package org.example.rotacerta.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import org.example.rotacerta.Model.idModel;
import org.example.rotacerta.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class NovaViagemService {

    @FXML
    private TextField kmLitroField, kmRotaField, precoCombustivelField, resultadoField;
    @FXML
    private Label statusLabel;

    private Integer user;

    @FXML
    private TableView<idModel> tabelaViagens;

    @FXML
    private TableColumn<idModel, Double> colunaKmLitro;

    @FXML
    private TableColumn<idModel, Double> colunaKmTotal;

    @FXML
    private TableColumn<idModel, Double> colunaPrecoCombustivel;

    @FXML
    private TableColumn<idModel, Double> colunaCustoTotal;

    private int userLogged;
    private ObservableList<idModel> listaDeViagens;

    @FXML
    private void initialize() {
        // Configure as colunas da tabela como editáveis
        colunaKmLitro.setCellValueFactory(new PropertyValueFactory<>("kmPorLitro"));
        colunaKmTotal.setCellValueFactory(new PropertyValueFactory<>("kmTotal"));
        colunaPrecoCombustivel.setCellValueFactory(new PropertyValueFactory<>("precoCombustivel"));
        colunaCustoTotal.setCellValueFactory(new PropertyValueFactory<>("custoTotal"));

        // Permitir edição nas colunas
        colunaKmLitro.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colunaKmTotal.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colunaPrecoCombustivel.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colunaCustoTotal.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        tabelaViagens.setEditable(true);

        // Atualizar valores no banco ao confirmar a edição
        colunaKmLitro.setOnEditCommit(event -> atualizarBanco("KmLitro", event.getNewValue(), event.getRowValue()));
        colunaKmTotal.setOnEditCommit(event -> atualizarBanco("KmTotal", event.getNewValue(), event.getRowValue()));
        colunaPrecoCombustivel.setOnEditCommit(event -> atualizarBanco("PrecoCombustivel", event.getNewValue(), event.getRowValue()));
        colunaCustoTotal.setOnEditCommit(event -> atualizarBanco("CustoTotal", event.getNewValue(), event.getRowValue()));

        // Carrega os dados iniciais
        carregarViagens();
    }

    private void atualizarBanco(String coluna, Double novoValor, idModel viagem) {
        String sql = "UPDATE Viagem SET " + coluna + " = ? WHERE idViagem = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, novoValor);
            pstmt.setInt(2, viagem.getIdViagem());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                statusLabel.setText("Registro atualizado com sucesso!");
                // Atualiza o modelo local
                switch (coluna) {
                    case "KmLitro":
                        viagem.setKmPorLitro(novoValor);
                        break;
                    case "KmTotal":
                        viagem.setKmTotal(novoValor);
                        break;
                    case "PrecoCombustivel":
                        viagem.setPrecoCombustivel(novoValor);
                        break;
                    case "CustoTotal":
                        viagem.setCustoTotal(novoValor);
                        break;
                }
                tabelaViagens.refresh();
            } else {
                statusLabel.setText("Erro ao atualizar registro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            statusLabel.setText("Erro de banco de dados.");
        }
    }


    private void carregarViagens() {
        ListaViagemService service = new ListaViagemService();
        List<idModel> viagens = service.getViagens(userLogged);

        listaDeViagens = FXCollections.observableArrayList(viagens);
        tabelaViagens.setItems(listaDeViagens);
    }

    @FXML
    public void calcularviagem(ActionEvent actionEvent) {
        try {
            double kmLitro = Double.parseDouble(kmLitroField.getText());
            double kmRota = Double.parseDouble(kmRotaField.getText());
            double precoCombustivel = Double.parseDouble(precoCombustivelField.getText());

            double custoTotal = (kmRota / kmLitro) * precoCombustivel;
            resultadoField.setText(String.valueOf(custoTotal));
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Verifique os valores inseridos.");
            resultadoField.clear();
        }
    }

    @FXML
    public void inserirViagem() {
        try {
            double kmLitro = Double.parseDouble(kmLitroField.getText());
            double kmTotal = Double.parseDouble(kmRotaField.getText());
            double precoCombustivel = Double.parseDouble(precoCombustivelField.getText());
            double custoTotal = Double.parseDouble(resultadoField.getText());

            String sql = "INSERT INTO Viagem (KmLitro, KmTotal, PrecoCombustivel, CustoTotal, idPessoa) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setDouble(1, kmLitro);
                pstmt.setDouble(2, kmTotal);
                pstmt.setDouble(3, precoCombustivel);
                pstmt.setDouble(4, custoTotal);
                pstmt.setInt(5, user);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    statusLabel.setText("Viagem inserida com sucesso!");

                    // Adiciona a nova viagem na lista de viagens
                    idModel novaViagem = new idModel(kmLitro, kmTotal, precoCombustivel, custoTotal);
                    listaDeViagens.add(novaViagem);
                } else {
                    statusLabel.setText("Falha na inserção da viagem.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            statusLabel.setText("Erro de banco de dados.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: verifique os valores inseridos.");
        }
    }

    public void setUserActive(Integer user) {
        userLogged = user;
        this.user = user;
        carregarViagens();
    }
}
