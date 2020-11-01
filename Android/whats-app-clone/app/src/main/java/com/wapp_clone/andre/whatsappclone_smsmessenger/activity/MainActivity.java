package com.wapp_clone.andre.whatsappclone_smsmessenger.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.job.JobInfo;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CpuUsageInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wapp_clone.andre.whatsappclone_smsmessenger.R;

import adapter.TabAdapter;
import config.ConfiguracaoFirebase;
import helper.Base64Custom;
import helper.Preferencias;
import helper.SlidingTabLayout;
import model.Contato;
import model.Usuario;


public class MainActivity extends AppCompatActivity {

    private Button btSair;
    private FirebaseAuth autenticacao;

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    private String identificadorContato;

    private DatabaseReference firebaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("WhatsApp");
        setSupportActionBar(toolbar);


        slidingTabLayout = findViewById(R.id.sltl_tabs);
        viewPager = findViewById(R.id.vp_pagina);

        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccente));

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_sair:
                deslogarUsuario();
                return true;
            case R.id.item_configuracoes:
                return true;
            case R.id.item_adicionar:
                abrirCadastroContato();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroContato() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Novo Contato");
        builder.setMessage("Email do Usuário");
        builder.setCancelable(false);

        final EditText editText = new EditText(MainActivity.this);

        builder.setView(editText);

        builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String emailContato = editText.getText().toString();
                if (emailContato.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha o Email", Toast.LENGTH_LONG).show();
                } else {
                    identificadorContato = Base64Custom.codificarBase64(emailContato);

                    firebaseReference = ConfiguracaoFirebase.getFirebase();
                    firebaseReference = firebaseReference.child("usuarios").child(identificadorContato);

                    firebaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null) {

                                Usuario usuarioContato = dataSnapshot.getValue(Usuario.class);

                                String identificadorUsuarioLogado = new Preferencias(MainActivity.this).getIdentificador();
                                firebaseReference = ConfiguracaoFirebase.getFirebase();
                                firebaseReference = firebaseReference
                                        .child("Contatos")
                                        .child(identificadorUsuarioLogado)
                                        .child(identificadorContato);
                                Contato contato = new Contato(
                                        identificadorContato,
                                        usuarioContato.getNome(),
                                        usuarioContato.getEmail());


                                firebaseReference.setValue(contato);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();

    }

    private void deslogarUsuario() {
        autenticacao.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActitvity.class);
        startActivity(intent);
        finish();
    }
}
