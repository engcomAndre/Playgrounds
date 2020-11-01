package com.parse.starter.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.parse.ParseObject;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends ArrayAdapter<ParseObject> {

    private Context mContext;
    private ArrayList<ParseObject> postagens;

    public HomeAdapter(Context context, ArrayList<ParseObject> objects) {
        super(context, 0, objects);
        this.mContext = context;
        this.postagens = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.layout_postagem, parent, false);
        }

        if (postagens.size() > 0) {
            ImageView imageView = view.findViewById(R.id.image_lista_postagem);
            ParseObject object = this.postagens.get(position);

            Picasso.with(this.mContext)
                    .load(object.getParseFile("imagem").getUrl())
                    .fit()
                    .into(imageView);

        }

        return view;
    }
}
