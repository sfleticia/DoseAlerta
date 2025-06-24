package com.example.dosealertaapp;

public class ModeloDados {

    private int idAgendamento;
    private String nomeMedicamento;
    private String hora;
    private String data;
    private String intervalo;
    private int dias;
    public ModeloDados() {}

    public ModeloDados(int idAgendamento, String nomeMedicamento, String hora) {
        this.idAgendamento = idAgendamento;
        this.nomeMedicamento = nomeMedicamento;
        this.hora = hora;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    // getters e setters para data, intervalo e dias
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getIntervalo() { return intervalo; }
    public void setIntervalo(String intervalo) { this.intervalo = intervalo; }

    public int getDias() { return dias; }
    public void setDias(int dias) { this.dias = dias; }

}
