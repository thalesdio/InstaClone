package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;
import com.parse.starter.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class FeedUsuarioActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private String userName;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> postagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuario);

        //Recuperar dados enviados pela intent
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");

        //Configurar Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_feed_usuario);
        toolbar.setTitle(userName);
        toolbar.setTitleTextColor(android.R.color.black);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);

        //Configurar ListView e Adaptet
        postagens = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_feed_usuario);
        adapter = new HomeAdapter(getApplicationContext(), postagens);
        listView.setAdapter(adapter);

        //Recuperar postagens
        getPostagens();
    }

    private void getPostagens(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Imagens");
        query.whereEqualTo("username", userName);
        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){ //Sucesso

                    //Verifica se existe postagens
                    if (objects.size() > 0){
                        postagens.clear();
                        for (ParseObject parseObject : objects){
                            postagens.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }else { //Erro
                    Toast.makeText(getApplicationContext(), "Erro ao recuperar feed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
