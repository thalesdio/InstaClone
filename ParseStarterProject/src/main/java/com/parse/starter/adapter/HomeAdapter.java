package com.parse.starter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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

    private Context context;
    private ArrayList<ParseObject> postagens;

    public HomeAdapter(Context c, ArrayList<ParseObject> objects) {
        super(c, 0, objects);
        this.context = c;
        this.postagens = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        //Inicializa objeto para montagem do layout
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta a view a partir do XML
            view = inflater.inflate(R.layout.lista_postagem, parent, false);
        }

        //Verificar se existe postagens
        if (postagens.size() > 0){
            ImageView imagemPostagens = (ImageView) view.findViewById(R.id.image_lista_postagem); //Recupera componentes da tela
            ParseObject parseObject = postagens.get(position);

            //Picasso
            Picasso.with(context).load(parseObject.getParseFile("imagem").getUrl()).fit().into(imagemPostagens);
        }

        return view;
    }
}
