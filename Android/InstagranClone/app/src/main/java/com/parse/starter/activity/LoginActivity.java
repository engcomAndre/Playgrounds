package com.parse.starter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.ConditionVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.util.ParseErrors;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.mContext = this;
        this.mapComponents();
        this.verificarUsuarioLogado();
    }

    public void goToCadastroActivity() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    private void verificarUsuarioLogado() {
        if (ParseUser.getCurrentUser() != null) {
            this.goToMainActivity();
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void mapComponents() {
        this.mViewHolder.nomeUsuario = findViewById(R.id.text_usuario);
        this.mViewHolder.senhaUsuario = findViewById(R.id.text_senha);
        this.mViewHolder.btnLogar = findViewById(R.id.button_logar);
        this.mViewHolder.btnLogar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_logar: {
                this.logarUsuario();

                break;
            }
            case R.id.text_crie_uma_conta: {
                this.goToCadastroActivity();
                break;

            }
            default: {
                break;
            }
        }

    }

    private void logarUsuario() {
        ParseUser.logInInBackground(
                this.mViewHolder.nomeUsuario.getText().toString(),
                this.mViewHolder.senhaUsuario.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            Toast.makeText(mContext, "Login realizado sucesso!", Toast.LENGTH_LONG).show();

                            goToMainActivity();
                        } else {
                            ParseErrors parseErrors = new ParseErrors();
                            Toast.makeText(mContext, parseErrors.getErrors(e.getCode()), Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }


    private static class ViewHolder {
        public EditText nomeUsuario;
        public EditText senhaUsuario;
        public Button btnLogar;

    }
}
