package com.parse.starter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ListView listView;
    private ArrayList<ParseObject> postagens;
    private ArrayAdapter<ParseObject> adapter;
    private ParseQuery<ParseObject> parseQuery;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.postagens = new ArrayList<>();
        this.listView = (ListView) view.findViewById(R.id.list_postagem_home);
        this.adapter = new HomeAdapter(getActivity(), postagens);
        this.listView.setAdapter(adapter);
        this.getPostagens();

        // Inflate the layout for this fragment
        return view;
    }

    private void getPostagens() {
        this.parseQuery = ParseQuery.getQuery("Imagem");
        this.parseQuery.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        this.parseQuery.orderByDescending("createdAt");

        this.parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects.size() > 0) {
                    postagens.clear();
                    for (ParseObject object : objects) {
                        postagens.add(object);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(),"Problemas ao carregar postagens",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void atualizaPostagens(){
       this.getPostagens();
    }
}
