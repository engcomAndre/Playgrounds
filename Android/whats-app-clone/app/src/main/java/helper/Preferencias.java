package helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;


public class Preferencias {
    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "whatsapp.preferencias";
    private Integer MODE = 0;
    private SharedPreferences.Editor editor;

    private String CHAVE_IDENTIFICADOR = "identificadorusuarioLogado";

    public Preferencias(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(NOME_ARQUIVO,MODE);
        this.editor = preferences.edit();
    }

    public void salvaDados(String identificadorUsuario) {
        editor.putString(CHAVE_IDENTIFICADOR,identificadorUsuario);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENTIFICADOR,null);
    }
}
