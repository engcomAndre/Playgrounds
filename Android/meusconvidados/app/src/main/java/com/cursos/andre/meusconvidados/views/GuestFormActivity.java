package com.cursos.andre.meusconvidados.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cursos.andre.meusconvidados.R;
import com.cursos.andre.meusconvidados.business.GuestBusiness;
import com.cursos.andre.meusconvidados.constants.GuestConstants;
import com.cursos.andre.meusconvidados.entities.GuestEntity;

public class GuestFormActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;

    private int mGuestID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_form);


        this.mViewHolder.mEditName = findViewById(R.id.edit_name);
        this.mViewHolder.mEditDocument = findViewById(R.id.edit_document);

        this.mViewHolder.mRadioPresent = findViewById(R.id.radio_present);
        this.mViewHolder.mRadioAbsent = findViewById(R.id.radio_absent);
        this.mViewHolder.mRadioNotConfirmed = findViewById(R.id.radio_not_confirmed);
        this.mViewHolder.mButtonSave = findViewById(R.id.button_save);

        mGuestBusiness = new GuestBusiness(this);

        this.setListeners();

        this.loadDataFromActivity();
    }

    private void loadDataFromActivity() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.mGuestID = bundle.getInt(GuestConstants.BundleConstants.GUEST_ID);

            GuestEntity guestEntity = mGuestBusiness.load(this.mGuestID);

            this.mViewHolder.mEditName.setText(guestEntity.getName());
            this.mViewHolder.mEditDocument.setText(guestEntity.getDocument());

            switch (guestEntity.getConfirmed()) {
                case GuestConstants.CONFIRMATION.PRESENT: {
                    this.mViewHolder.mRadioPresent.setChecked(true);
                    break;
                }
                case GuestConstants.CONFIRMATION.ABSENT :{
                    this.mViewHolder.mRadioAbsent.setChecked(true);
                    break;
                }
                case GuestConstants.CONFIRMATION.NOT_CONFIRMED :{
                    this.mViewHolder.mRadioNotConfirmed.setChecked(true);
                    break;
                }
            }
        }
    }

    private void setListeners() {
        this.mViewHolder.mEditName.setOnClickListener(this);
        this.mViewHolder.mRadioAbsent.setOnClickListener(this);
        this.mViewHolder.mRadioPresent.setOnClickListener(this);
        this.mViewHolder.mRadioNotConfirmed.setOnClickListener(this);
        this.mViewHolder.mButtonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Integer id = view.getId();
        switch (id) {
            case R.id.radio_present: {

                break;
            }
            case R.id.radio_not_confirmed: {

                break;
            }
            case R.id.radio_absent: {

                break;
            }
            case R.id.button_save: {
                this.handleSave();
                break;
            }
        }

    }

    private void handleSave() {
        if (!this.validateSave()) {
            return;
        }

        GuestEntity guestEntity = new GuestEntity();
        guestEntity.setName(this.mViewHolder.mEditName.getText().toString());
        guestEntity.setDocument(this.mViewHolder.mEditDocument.getText().toString());
        if (this.mViewHolder.mRadioNotConfirmed.isChecked()) {
            guestEntity.setConfirmed(GuestConstants.CONFIRMATION.NOT_CONFIRMED);
        } else if (this.mViewHolder.mRadioPresent.isChecked()) {
            guestEntity.setConfirmed(GuestConstants.CONFIRMATION.PRESENT);
        } else {
            guestEntity.setConfirmed(GuestConstants.CONFIRMATION.ABSENT);
        }


        if(this.mGuestID == 0){
            if (this.mGuestBusiness.insert(guestEntity)) {
                Toast.makeText(this,getString(R.string.convidado_salvo),Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,getString(R.string.erro_ao_salvar_convidado),Toast.LENGTH_LONG).show();
            }
        }
        else {

            guestEntity.setId(this.mGuestID);
            if (this.mGuestBusiness.update(guestEntity)) {
                Toast.makeText(this,getString(R.string.convidado_salvo),Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,getString(R.string.erro_ao_salvar_convidado),Toast.LENGTH_LONG).show();
            }

        }


        finish();
    }

    private boolean validateSave() {
        if (this.mViewHolder.mEditName.getText().toString().isEmpty()) {
            this.mViewHolder.mEditName.setError(getString(R.string.nome_obrigatorio));
            return false;
        }
        return true;
    }

    private static class ViewHolder {
        EditText mEditName;
        EditText mEditDocument;
        RadioButton mRadioNotConfirmed;
        RadioButton mRadioAbsent;
        RadioButton mRadioPresent;
        Button mButtonSave;
    }
}
