package com.listadetarefas.andre.listadetarefas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn;
    private ListView listView;
    private SQLiteDatabase bd;
    private ArrayAdapter<String> itensAdapter;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            editText = findViewById(R.id.editText_ID);
            listView = findViewById(R.id.listview_ID);
            btn = findViewById(R.id.button_ID);
            bd = openOrCreateDatabase("appTarefas", MODE_PRIVATE, null);

            bd.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER PRIMARY KEY AUTOINCREMENT,tarefa VARCHAR)");

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String texto = editText.getText().toString();
                    salvaTarefa(texto);
                    editText.setText("");
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("ITEM :  ",position+" / "+ids.get(position));
                    removerTarefa(ids.get(position),itens.get(position));
                }
            });

            mostrarTarefas();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void salvaTarefa(String texto) 