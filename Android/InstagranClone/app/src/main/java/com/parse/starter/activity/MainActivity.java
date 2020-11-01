/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.R;
import com.parse.starter.adapter.TabsAdapter;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.util.ParseErrors;
import com.parse.starter.util.SlidingTabLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = this;
        this.mapComponents();
    }


    private void mapComponents() {
        this.mViewHolder.toolbarPrincipal = findViewById(R.id.toolbal_principal);
        this.mViewHolder.toolbarPrincipal.setLogo(R.drawable.instagramlogo);
        setSupportActionBar(this.mViewHolder.toolbarPrincipal);

        this.mViewHolder.slidingTabLayout = findViewById(R.id.sliding_tab_main);
        this.mViewHolder.viewPager = findViewById(R.id.viewpager_main);

        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), this.mContext);
        this.mViewHolder.viewPager.setAdapter(tabsAdapter);
        this.mViewHolder.slidingTabLayout.setCustomTabView(R.layout.tabview, R.id.text_item_tab);
        this.mViewHolder.slidingTabLayout.setDistributeEvenly(true);
        this.mViewHolder.slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this.mContext, R.color.cinzaescuro));
        this.mViewHolder.slidingTabLayout.setViewPager(this.mViewHolder.viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_configuracaoes: {
                break;
            }
            case R.id.action_compartilhar: {
                this.compartilharFoto();
                break;
            }
            case R.id.action_sair: {
                this.deslogarUsuario();
            }
            break;
            default: {
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void compartilharFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagem.compress(Bitmap.CompressFormat.PNG, 75, stream);
                byte[] byteArray = stream.toByteArray();

                SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmaaaahhmmss");
                String nomeImagem = dateFormat.format(new Date());
                ParseFile parseFile = new ParseFile(nomeImagem + "imagem.png", byteArray);

                ParseObject object = new ParseObject("Imagem");
                object.put("username", ParseUser.getCurrentUser().getUsername());
                object.put("imagem", parseFile);
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(mContext, "Imagem compartilhada com sucesso", Toast.LENGTH_LONG).show();
                            TabsAdapter newAdapter = (TabsAdapter) mViewHolder.viewPager.getAdapter();
                            HomeFragment homeFragment = (HomeFragment) newAdapter.getFragmentHashMap(0);
                            homeFragment.atualizaPostagens();

                        } else {
                            Toast.makeText(mContext, "Erro ao  salvar imagem.", Toast.LENGTH_LONG).show();

                        }
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void deslogarUsuario() {
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(mContext, "Logout efetuado com sucesso", Toast.LENGTH_LONG).show();
                    goToLoginActivity();
                } else {
                    ParseErrors parseErrors = new ParseErrors();
                    Toast.makeText(mContext, parseErrors.getErrors(e.getCode()), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this.mContext, LoginActivity.class);
        startActivity(intent);
    }

    private static class ViewHolder {
        public Toolbar toolbarPrincipal;
        public SlidingTabLayout slidingTabLayout;
        public ViewPager viewPager;

    }
}





