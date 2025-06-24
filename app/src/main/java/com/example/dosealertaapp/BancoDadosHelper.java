package com.example.dosealertaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDadosHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dosealerta.db";
    private static final int DATABASE_VERSION = 1;

    public BancoDadosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE agendamentos (" +
                "idAgendamento INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nomeMedicamento TEXT NOT NULL," +
                "hora TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS agendamentos");
        onCreate(db);
    }
}
