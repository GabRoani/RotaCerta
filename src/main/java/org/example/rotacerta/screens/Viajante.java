package org.example.rotacerta.screens;

public class Viajante {
    int idviajante;
    String nomeviajante;
    int senhaviajante;

    public Viajante(int idviajante, String nomeviajante, int senhaviajante) {
        this.idviajante = idviajante;
        this.nomeviajante = nomeviajante;
        this.senhaviajante = senhaviajante;
    }

    public int getIdviajante() {
        return idviajante;
    }

    public void setIdviajante(int idviajante) {
        this.idviajante = idviajante;
    }

    public String getNomeviajante() {
        return nomeviajante;
    }

    public void setNomeviajante(String nomeviajante) {
        this.nomeviajante = nomeviajante;
    }

    public int getSenhaviajante() {
        return senhaviajante;
    }

    public void setSenhaviajante(int senhaviajante) {
        this.senhaviajante = senhaviajante;
    }
}
