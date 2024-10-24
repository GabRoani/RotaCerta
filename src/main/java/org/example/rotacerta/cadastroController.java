package org.example.rotacerta;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.control.TextField;


public class cadastroController {
    @FXML
    private TextField kmPorLitroField;

    @FXML
    private TextField kmTotalField;

    @FXML
    private TextField precoCombustivelField;

    @FXML
    private TreeTableView<Viagem> tabelaViagem;

    @FXML
    private TreeTableColumn<Viagem, Integer> colunaId;

    @FXML
    private TreeTableColumn<Viagem, Integer> idColumn;

    @FXML
    private TreeTableColumn<Viagem, Double> valorEstimadoColumn;

    // Classe Viagem usada para armazenar os dados de cada viagem
    public static class Viagem {
        private final Integer id;
        private final Double valorEstimado;

        public Viagem(Integer id, Double valorEstimado) {
            this.id = id;
            this.valorEstimado = valorEstimado;
        }

        public Integer getId() {
            return id;
        }

        public Double getValorEstimado() {
            return valorEstimado;
        }
    }

    @FXML
    public void initialize() {
        // Configurando as colunas
        idColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        valorEstimadoColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("valorEstimado"));

        // Criando dados de exemplo para a tabela
        TreeItem<Viagem> root = new TreeItem<>(new Viagem(0, 0.0));
        tabelaViagem.setRoot(root);
        tabelaViagem.setShowRoot(false);  // Para não exibir a raiz

    }

    @FXML
    private void calcularViagem(ActionEvent event) {
        try {
            double kmPorLitro = Double.parseDouble(kmPorLitroField.getText());
            double kmTotal = Double.parseDouble(kmTotalField.getText());
            double precoCombustivel = Double.parseDouble(precoCombustivelField.getText());

            // Cálculo do custo estimado da viagem
            double litrosNecessarios = kmTotal / kmPorLitro;
            double valorEstimado = litrosNecessarios * precoCombustivel;

            int contadorViagens = 0;

            contadorViagens++;


            System.out.println("Valor estimado da viagem: R$ " + valorEstimado);

            Viagem novaViagem = new Viagem(contadorViagens, valorEstimado);
            TreeItem<Viagem> item = new TreeItem<>(novaViagem);
            tabelaViagem.getRoot().getChildren().add(item);

        } catch (NumberFormatException e) {
            System.out.println("Erro ao calcular a viagem. Verifique os valores inseridos.");
        }
    }

    @FXML
    public void iniciarViagem() {
        // Lógica para iniciar uma viagem (implemente conforme necessário)
    }
}
