package com.parse.starter.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;
import com.parse.starter.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;


public class FeedUsuariosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private String username;
    private ArrayAdapter<ParseObject> objectArrayAdapter;
    private ArrayList<ParseObject> postagens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuarios);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        this.postagens = new ArrayList<>();

        this.toolbar = findViewById(R.id.toolbar_feed_usuarios);
        this.toolbar.setTitle(username);
        this.toolbar.setTitleTextColor(Color.BLACK);
        this.toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(this.toolbar);

        this.listView = findViewById(R.id.list_feed_usuario);
        this.objectArrayAdapter = new HomeAdapter(this, postagens);

        this.listView.setAdapter(this.objectArrayAdapter);

        this.getPostagens();
    }

    private void getPostagens() {

        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Imagem");
        parseQuery.whereEqualTo("username", username)
                .orderByAscending("createdAt");

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if(objects.size() > 0){
                        postagens.clear();
                        for(ParseObject object :objects){
                            postagens.add(object);
                        }
                        objectArrayAdapter.notifyDataSetChanged();
                    }

                } else {
                    Toast.makeText(getApplication(),"Erro ao buscar postagens",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
