package com.example.dosealertaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha;
    private BancoController bc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bc = new BancoController(this);

        txtEmail = findViewById(R.id.txtemail);
        txtSenha = findViewById(R.id.txtsenha);

        Button btnLogin = findViewById(R.id.loginButton);
        TextView linkCad = findViewById(R.id.registraLink);

        btnLogin.setOnClickListener(v -> fazerLogin());
        linkCad.setOnClickListener(v -> startActivity(new Intent(this, CadastroActivity.class)));
    }

    private void fazerLogin() {
        String email = txtEmail.getText().toString().trim();
        String senha = txtSenha.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {
            Toast.makeText(this, "Informe seu email e a sua senha", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bc.verificaLogin(email, senha)) {
            SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email_usuario_logado", email);
            editor.apply();

            Toast.makeText(this, "Login concluído", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Informações inválidas", Toast.LENGTH_SHORT).show();
        }

    }
}


