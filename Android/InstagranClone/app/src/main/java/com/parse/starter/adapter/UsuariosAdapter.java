package com.parse.starter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseUser;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

public class UsuariosAdapter extends ArrayAdapter<ParseUser> {

    private Context mContext;
    private ArrayList<ParseUser> usuarios;

    public UsuariosAdapter(Context context, ArrayList<ParseUser> objects) {
        super(context, 0, objects);
        this.mContext = context;
        this.usuarios = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.lista_usuarios, parent, false);
        }

        TextView username = view.findViewById(R.id.text_username);

        ParseUser parseUser = this.usuarios.get(position);
        username.setText(parseUser.getUsername());

        return view;
    }
}
