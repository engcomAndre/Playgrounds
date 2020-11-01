package com.andre.tasks.views;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.andre.tasks.R;
import com.andre.tasks.infra.OperationListener;
import com.andre.tasks.manager.PersonManager;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private PersonManager mPersonManager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.mPersonManager = new PersonManager(this);
        this.mContext = this;

        // Inicializa elementos
        this.mViewHolder.editName = (EditText) this.findViewById(R.id.edit_name);
        this.mViewHolder.editEmail = (EditText) this.findViewById(R.id.edit_email);
        this.mViewHolder.editPassword = (EditText) this.findViewById(R.id.edit_password);
        this.mViewHolder.buttonSave = (Button) this.findViewById(R.id.button_save);
        this.mViewHolder.imageBack = (ImageView) this.findViewById(R.id.image_toolbar_back);

        // Inicializa eventos
        this.mViewHolder.buttonSave.setOnClickListener(this);
        this.mViewHolder.imageBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_save: {
                this.handleSave();
            }
            break;
            case R.id.image_toolbar_back: {
                super.onBackPressed();
            }
            break;
            default:{
                break;
            }
        }


    }

    private void handleSave() {
        String name = this.mViewHolder.editName.getText().toString();
        String email = this.mViewHolder.editEmail.getText().toString();
        String password = this.mViewHolder.editPassword.getText().toString();

        this.mPersonManager.create(name, email, password, registerLstener());
    }

    private OperationListener registerLstener() {
        return new OperationListener<Boolean>() {
            @Override
            public void onSucess(Boolean result) {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();

            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
            }
        };

    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        private ImageView imageBack;
        private EditText editName;
        private EditText editEmail;
        private EditText editPassword;
        private Button buttonSave;
    }
}
