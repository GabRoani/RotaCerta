package org.example.rotacerta.service;

import org.example.rotacerta.Model.idModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaViagemService {

    private static final String URL = "jdbc:mysql://localhost:3306/RotaCerta";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";

    public List<idModel> getViagens(int userId) {
        List<idModel> listId = new ArrayList<>();
        String query = "SELECT * FROM viagem WHERE idPessoa = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId); // Define o ID do usuário na consulta

            try (ResultSet rs = stmt.executeQuery()) {
                // Itera pelos resultados da consulta
                while (rs.next()) {
                    idModel viagem = new idModel(
                            rs.getDouble("kmLitro"),
                            rs.getDouble("kmTotal"),
                            rs.getDouble("precoCombustivel"),
                            rs.getDouble("custoTotal"));

                    listId.add(viagem);
                }

                // Exibe as informações no console
                for (idModel viagem : listId) {
                    System.out.println("ID Viagem: " + viagem.getId());
                    System.out.println("Km/Litro: " + viagem.getKmPorLitro());
                    System.out.println("Km Total: " + viagem.getKmTotal());
                    System.out.println("Preço Combustível: " + viagem.getPrecoCombustivel());
                    System.out.println("Custo Total: " + viagem.getCustoTotal());
                    System.out.println("---------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listId;
    }
}
