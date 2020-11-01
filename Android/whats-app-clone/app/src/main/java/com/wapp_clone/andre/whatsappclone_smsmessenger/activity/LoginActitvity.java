package com.wapp_clone.andre.whatsappclone_smsmessenger.activity;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wapp_clone.andre.whatsappclone_smsmessenger.R;

import config.ConfiguracaoFirebase;
import helper.Base64Custom;
import helper.Preferencias;
import model.Usuario;

public class LoginActitvity extends AppCompatActivity {
    private EditText email;
    private EditText senha;
    private Button btLogar;

    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actitvity);

        verificarUsuarioLogado();

        email = findViewById(R.id.edit_login_email);
        senha = findViewById(R.id.edit_login_senha);
        btLogar = findViewById(R.id.btn_logar);

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();

                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                validarLogin();

            }
        });

    }

    private void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Preferencias preferencias = new Preferencias(LoginActitvity.this);
                    String identificadorUsuarioLogado = Base64Custom.codificarBase64(usuario.getEmail());
                    preferencias.salvaDados(identificadorUsuarioLogado);
                    Toast.makeText(LoginActitvity.this, "Sucesso ao fazer login", Toast.LENGTH_LONG).show();
                    abrirTelaPrincipal();
                } else {
                    Toast.makeText(LoginActitvity.this, "Erro ao fazer login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(LoginActitvity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void abrirCadastroUsuario(View view) {
        Intent intent = new Intent(LoginActitvity.this, CadastroUsuarioActivitiy.class);
        startActivity(intent);
    }
}
