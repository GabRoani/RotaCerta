package org.example.rotacerta.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.rotacerta.Model.idModel;

public class ListaViagemController {

    @FXML
    private TableView<idModel> tabelaViagens;
    @FXML
    private TableColumn<idModel, String> colunaCpf;
    @FXML
    private TableColumn<idModel, Double> colunaKmLitro;
    @FXML
    private TableColumn<idModel, Double> colunaKmTotal;
    @FXML
    private TableColumn<idModel, Double> colunaPreco;
    @FXML
    private TableColumn<idModel, Double> colunaCustoTotal;
    @FXML
    private Button buttonVoltar;

    private ObservableList<idModel> listaDeViagens = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaKmLitro.setCellValueFactory(new PropertyValueFactory<>("kmPorLitro"));
        colunaKmTotal.setCellValueFactory(new PropertyValueFactory<>("kmTotal"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("precoCombustivel"));
        colunaCustoTotal.setCellValueFactory(new PropertyValueFactory<>("custoTotal"));

        tabelaViagens.setItems(listaDeViagens);

        tabelaViagens.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            buttonVoltar.setDisable(newSelection == null);
        });
    }



    @FXML
    private void voltar(MouseEvent event) {
        System.out.println("Botão Voltar clicado!");
    }

    public void Voltar(ActionEvent actionEvent) {
    }
}
