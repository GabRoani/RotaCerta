package org.example.rotacerta.Model;

public class idModel {

    private int id;                  // ID da viagem
    private double kmPorLitro;       // Quilômetros por litro
    private double kmTotal;          // Quilometragem total
    private double precoCombustivel; // Preço do combustível
    private double custoTotal;       // Custo total da viagem

    // Construtor corrigido
    public idModel(double kmPorLitro, double kmTotal, double precoCombustivel, double custoTotal) {
        this.id = id;
        this.kmPorLitro = kmPorLitro;
        this.kmTotal = kmTotal;
        this.precoCombustivel = precoCombustivel;
        this.custoTotal = custoTotal;
    }

    // Métodos getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getKmPorLitro() {
        return kmPorLitro;
    }

    public void setKmPorLitro(double kmPorLitro) {
        this.kmPorLitro = kmPorLitro;
    }

    public double getKmTotal() {
        return kmTotal;
    }

    public void setKmTotal(double kmTotal) {
        this.kmTotal = kmTotal;
    }

    public double getPrecoCombustivel() {
        return precoCombustivel;
    }

    public void setPrecoCombustivel(double precoCombustivel) {
        this.precoCombustivel = precoCombustivel;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public int getIdViagem() {
        return id;
    }
}
