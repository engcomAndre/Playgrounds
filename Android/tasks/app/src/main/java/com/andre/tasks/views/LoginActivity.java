package com.andre.tasks.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andre.tasks.R;
import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.infra.OperationListener;
import com.andre.tasks.infra.SecurityPreferences;
import com.andre.tasks.manager.PersonManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private PersonManager mPersonManager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mPersonManager = new PersonManager(this);
        this.mContext = this;

        // Inicializa elementos
        this.mViewHolder.editEmail = (EditText) this.findViewById(R.id.edit_email);
        this.mViewHolder.editPassword = (EditText) this.findViewById(R.id.edit_password);
        this.mViewHolder.buttonLogin = (Button) this.findViewById(R.id.button_login);
        this.mViewHolder.textRegister = (TextView) this.findViewById(R.id.text_register);

        // Inicializa eventos
        this.mViewHolder.buttonLogin.setOnClickListener(this);
        this.mViewHolder.textRegister.setOnClickListener(this);

        this.verifyLoggedUser();

    }

    private void verifyLoggedUser() {
        SecurityPreferences preferences = new SecurityPreferences(this.mContext);
        String token =  preferences.getStoredString(TaskConstants.HEADER.TOKEN_KEY);
        String personKey=  preferences.getStoredString(TaskConstants.HEADER.PERSON_KEY);

        if(!"".equals(token) && !"".equals(personKey)){
            startActivity(new Intent(this.mContext,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_login) {
            this.handleLogin();
        } else if (id == R.id.text_register) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    private void handleLogin() {
        String email = this.mViewHolder.editEmail.getText().toString();
        String password = this.mViewHolder.editPassword.getText().toString();

        this.mPersonManager.login(email,password,loginListener());

    }

    private OperationListener loginListener(){
        return new OperationListener<Boolean>(){
            @Override
            public void onSucess(Boolean result){
                startActivity(new Intent(mContext,MainActivity.class));
                finish();
            }
            @Override
            public void onError(int errorCode,String errorMessage){
                Toast.makeText(mContext,errorMessage,Toast.LENGTH_LONG).show();
             }
        };

    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        private EditText editEmail;
        private EditText editPassword;
        private Button buttonLogin;
        private TextView textRegister;
    }
}
