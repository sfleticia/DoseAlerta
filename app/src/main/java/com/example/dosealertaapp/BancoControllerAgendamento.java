package com.example.dosealertaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoControllerAgendamento {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoControllerAgendamento(Context context) {
        banco = new CriaBanco(context);
    }

    public String insereAgendamento(String data, String hora, String email, String medicamento, String intervalo, int dias) {
        db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("data", data);
        valores.put("hora", hora);
        valores.put("email", email);
        valores.put("medicamento", medicamento);
        valores.put("intervalo", intervalo);
        valores.put("dias", dias);

        long resultado = db.insert("agendamento", null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir agendamento";
        } else {
            return "Agendamento inserido com sucesso";
        }
    }


    public Cursor ConsultaDadosAgendamento(String data, String hora) {
        db = banco.getReadableDatabase();

        String[] colunas = {"idAgendamento"};
        String where = "data = ? AND hora = ?";
        String[] args = {data, hora};

        Cursor cursor = db.query("agendamento", colunas, where, args, null, null, null);


        return cursor;
    }


    public Cursor getTodosAgendamentos() {
        db = banco.getReadableDatabase();
        String[] colunas = {"idAgendamento", "medicamento", "data", "hora", "email", "intervalo", "dias"};
        return db.query("agendamento", colunas, null, null, null, null, "data ASC, hora ASC");
    }

    public String deletarAgendamento(int idAgendamento) {
        db = banco.getWritableDatabase();
        int linhas = db.delete("agendamento", "idAgendamento = ?", new String[]{String.valueOf(idAgendamento)});
        db.close();

        return (linhas > 0) ? "Agendamento excluído com sucesso" : "Erro ao excluir agendamento";
    }


    public Cursor getAllAgendamentos() {
        db = banco.getReadableDatabase();
        return db.query("agendamento", null, null, null, null, null, "data ASC, hora ASC");
    }

    public String atualizarAgendamento(int idAgendamento, String medicamento, String data, String hora, String intervalo, int dias) {
        db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("medicamento", medicamento);
        valores.put("data", data);
        valores.put("hora", hora);
        valores.put("intervalo", intervalo);
        valores.put("dias", dias);

        int rows = db.update("agendamento", valores, "idAgendamento = ?", new String[]{String.valueOf(idAgendamento)});
        db.close();

        if (rows > 0) {
            return "Agendamento atualizado com sucesso";
        } else {
            return "Erro ao atualizar agendamento";
        }
    }



}
