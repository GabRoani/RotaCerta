package org.example.rotacerta.model;

public class Viagem {

        private double kilometragemEstimada;
        private double kmLitro;
        private double precoCombustivel;

    public Viagem(double kilometragemEstimada, double kmLitro, double precoCombustivel) {
        this.kilometragemEstimada = kilometragemEstimada;
        this.kmLitro = kmLitro;
        this.precoCombustivel = precoCombustivel;
    }

    public double getKilometragemEstimada() {
        return kilometragemEstimada;
    }

    public void setKilometragemEstimada(double kilometragemEstimada) {
        this.kilometragemEstimada = kilometragemEstimada;
    }

    public double getKmLitro() {
        return kmLitro;
    }

    public void setKmLitro(double kmLitro) {
        this.kmLitro = kmLitro;
    }

    public double getPrecoCombustivel() {
        return precoCombustivel;
    }

    public void setPrecoCombustivel(double precoCombustivel) {
        this.precoCombustivel = precoCombustivel;
    }
}
