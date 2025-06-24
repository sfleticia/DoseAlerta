package com.example.dosealertaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome, edtEmail, edtCpf, edtSenha, edtConfirma;
    private BancoController bc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bc = new BancoController(this);

        edtNome = findViewById(R.id.editTextNome);
        edtEmail = findViewById(R.id.editTextEmail);
        edtCpf = findViewById(R.id.editTextCPF);
        edtSenha = findViewById(R.id.editTextSenha);
        edtConfirma = findViewById(R.id.editTextConfirmarSenha);

        Button btn = findViewById(R.id.btnCadastrar);
        btn.setOnClickListener(v -> cadastrar());
    }

    private void cadastrar(){
        String n = edtNome.getText().toString().trim();
        String e = edtEmail.getText().toString().trim();
        String c = edtCpf.getText().toString().trim();
        String s = edtSenha.getText().toString().trim();
        String sc = edtConfirma.getText().toString().trim();

        if (TextUtils.isEmpty(n) || TextUtils.isEmpty(e) || TextUtils.isEmpty(c) || TextUtils.isEmpty(s)) {
            Toast.makeText(this, "Preencha todos os CAMPOS", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!s.equals(sc)){
            Toast.makeText(this, "As senhas não são IGUAIS", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bc.emailExiste(e)) {
            Toast.makeText(this, "Email já cadastrado", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bc.cpfExiste(c)) {
            Toast.makeText(this, "CPF já cadastrado", Toast.LENGTH_SHORT).show();
            return;
        }

        String msg = bc.insereUsuario(n, e, c, s);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if (msg.contains("sucesso")) finish();
    }
}
