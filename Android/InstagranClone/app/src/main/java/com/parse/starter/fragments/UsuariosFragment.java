package com.parse.starter.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.activity.FeedUsuariosActivity;
import com.parse.starter.adapter.UsuariosAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsuariosFragment extends Fragment {


    private ListView listView;
    private ArrayAdapter<ParseUser> parseUserAdapter;
    private ArrayList<ParseUser> usuarios;
    private ParseQuery<ParseUser> parseUserParseQuery;


    public UsuariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);


        usuarios = new ArrayList<>();
        this.listView = view.findViewById(R.id.lista_usuarios);
        this.parseUserAdapter = new UsuariosAdapter(getActivity(), usuarios);
        this.listView.setAdapter(parseUserAdapter);

        this.listView.setOnItemClickListener(onItemClickListener());

        this.getUsuarios();
        return view;
    }

    private AdapterView.OnItemClickListener onItemClickListener(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseUser parseUser = usuarios.get(position);
                String name = parseUser.getUsername();

                Intent intent = new Intent(getActivity(), FeedUsuariosActivity.class);
                intent.putExtra("username",name);
                startActivity(intent);
            }
        };
    }

    private void getUsuarios() {
        this.parseUserParseQuery = ParseUser.getQuery();
        this.parseUserParseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        this.parseUserParseQuery.orderByAscending("username");
        this.parseUserParseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e == null){
                    if(objects.size() > 0){
                        usuarios.clear();
                        for(ParseUser user : objects){
                            usuarios.add(user);
                        }
                        parseUserAdapter.notifyDataSetChanged();
                    }
                }else{
                    e.printStackTrace();
                }
            }
        });

     }

}
