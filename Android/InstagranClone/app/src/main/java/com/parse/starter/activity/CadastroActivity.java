package com.parse.starter.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;
import com.parse.starter.util.ParseErrors;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.mapComponents();
        this.mContext = this;
    }

    public void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_logar: {
                this.cadastrarUsuario();
            }
            case R.id.txt_faca_login:{
                this.goToLoginActivity();
            }
             default:
                break;
        }
    }

    private void cadastrarUsuario() {
        ParseUser usuario = new ParseUser();
        usuario.setUsername(this.mViewHolder.nomeUsuario.getText().toString());
        usuario.setEmail(this.mViewHolder.emailUsuario.getText().toString());
        usuario.setPassword(this.mViewHolder.senhaUsuario.getText().toString());
        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(mContext, "Sucesso ao cadastrar usuario.", Toast.LENGTH_LONG).show();
                    goToLoginActivity();
                } else {
                    ParseErrors parseErrors = new ParseErrors();
                    Toast.makeText(mContext, parseErrors.getErrors(e.getCode()), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void mapComponents() {
        this.mViewHolder.nomeUsuario = findViewById(R.id.text_usuario);
        this.mViewHolder.emailUsuario = findViewById(R.id.text_senha);
        this.mViewHolder.senhaUsuario = findViewById(R.id.text_senha);
        this.mViewHolder.btnCadastrar = findViewById(R.id.button_logar);
        this.mViewHolder.btnCadastrar.setOnClickListener(this);
    }


    private static class ViewHolder {
        public EditText nomeUsuario;
        public EditText emailUsuario;
        public EditText senhaUsuario;
        public Button btnCadastrar;
    }
}
