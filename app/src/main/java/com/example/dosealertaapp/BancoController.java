package com.example.dosealertaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private final CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String insereUsuario(String nome, String email, String cpf, String senha) {
        db = banco.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put("nome", nome);
        v.put("email", email);
        v.put("cpf", cpf);
        v.put("senha", senha);

        long resultado = db.insert("usuarios", null, v);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir usuário";
        else
            return "Registro inserido com sucesso";
    }

    public boolean verificaLogin(String email, String senha) {
        db = banco.getReadableDatabase();

        String[] campos = {"id"};
        String where = "email=? AND senha=?";
        String[] args = {email, senha};

        Cursor c = db.query("usuarios", campos, where, args, null, null, null);
        boolean ok = c.moveToFirst();
        c.close();
        db.close();
        return ok;
    }

    public String getNomeUsuario(String email) {
        db = banco.getReadableDatabase();

        String[] campos = {"nome"};
        String where = "email=?";
        String[] args = {email};

        Cursor c = db.query("usuarios", campos, where, args, null, null, null);

        String nome = null;
        if (c.moveToFirst()) {
            nome = c.getString(c.getColumnIndexOrThrow("nome"));
        }
        c.close();
        db.close();
        return nome;
    }

    public String buscarNomePrimeiroUsuario() {
        db = banco.getReadableDatabase();
        String nome = null;
        Cursor cursor = db.rawQuery("SELECT nome FROM usuarios LIMIT 1", null);
        if (cursor.moveToFirst()) {
            nome = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return nome;
    }

    public boolean emailExiste(String email) {
        db = banco.getReadableDatabase();

        String[] campos = {"email"};
        String where = "email=?";
        String[] args = {email};

        Cursor c = db.query("usuarios", campos, where, args, null, null, null);
        boolean existe = c.moveToFirst();
        c.close();
        db.close();
        return existe;
    }

    public boolean cpfExiste(String cpf) {
        db = banco.getReadableDatabase();

        String[] campos = {"cpf"};
        String where = "cpf=?";
        String[] args = {cpf};

        Cursor c = db.query("usuarios", campos, where, args, null, null, null);
        boolean existe = c.moveToFirst();
        c.close();
        db.close();
        return existe;
    }
}

