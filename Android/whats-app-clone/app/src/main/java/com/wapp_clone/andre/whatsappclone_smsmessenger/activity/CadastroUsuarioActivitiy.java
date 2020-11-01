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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.wapp_clone.andre.whatsappclone_smsmessenger.R;

import config.ConfiguracaoFirebase;
import helper.Base64Custom;
import helper.Preferencias;
import model.Usuario;

public class CadastroUsuarioActivitiy extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button btnCadastrar;

    private Usuario usuario;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_activitiy);

        nome = findViewById(R.id.edit_cadastro_nome);
        email = findViewById(R.id.edit_cadastro_email);
        senha = findViewById(R.id.edit_cadastro_senha);
        btnCadastrar = findViewById(R.id.btn_cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                cadastrarUsuario(usuario);

            }
        });

    }

    private void cadastrarUsuario(final Usuario usuario) {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(CadastroUsuarioActivitiy.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail().toString());
                            usuario.setId(identificadorUsuario);
                            usuario.salvar();

                            Preferencias preferencias = new Preferencias(CadastroUsuarioActivitiy.this);
                            preferencias.salvaDados(identificadorUsuario);
                            
                            abrirUsuarioLogado();

                            Toast.makeText(
                                    CadastroUsuarioActivitiy.this,
                                    "Sucesso ao cadastrar usuário.",
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            String erroExcecao = "";

                            try {
                                throw task.getException();

                            }catch (FirebaseAuthWeakPasswordException e){
                                erroExcecao = "digite uma senha mais forte,contendo mais caracteres com letras e números.";

                            } catch (FirebaseAuthInvalidUserException e){
                                erroExcecao = "o email informado inválido,informe um email válido.";
                            }catch (FirebaseAuthUserCollisionException e){
                                erroExcecao = "o email informado já cadastrado.";
                            }
                            catch (Exception e){
                                erroExcecao = "ao efetuar cadastro.";
                                e.printStackTrace();
                            }
                            Toast.makeText(
                                    CadastroUsuarioActivitiy.this,
                                    "Erro ,"+ erroExcecao,
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }

    private void abrirUsuarioLogado() {
        Intent intent = new Intent(CadastroUsuarioActivitiy.this,LoginActitvity.class);
        startActivity(intent);
    }
}
