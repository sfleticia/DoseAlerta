package com.example.dosealertaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco_exemplo.db";
    private static final int VERSAO = 6;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUsuarios = "CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " +
                "cpf TEXT NOT NULL UNIQUE, " +
                "senha TEXT NOT NULL)";
        db.execSQL(sqlUsuarios);

        String sqlContatos = "CREATE TABLE contatos (" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "email TEXT)";
        db.execSQL(sqlContatos);
        String sqlAgendamento = "CREATE TABLE agendamento (" +
                "idAgendamento INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "medicamento TEXT, " +
                "data TEXT, " +
                "hora TEXT, " +
                "email TEXT, " +
                "intervalo TEXT, " +
                "dias INTEGER)";
        db.execSQL(sqlAgendamento);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS contatos");
        db.execSQL("DROP TABLE IF EXISTS agendamento");
        onCreate(db);
    }
}
