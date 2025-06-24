package com.example.dosealertaapp;

public class Historico {
    private String nome;
    private String dose;
    private String dataHora;

    public Historico(String nome, String dose, String dataHora) {
        this.nome = nome;
        this.dose = dose;
        this.dataHora = dataHora;
    }

    public String getNome() {
        return nome;
    }

    public String getDose() {
        return dose;
    }

    public String getDataHora() {
        return dataHora;
    }
}
