package com.firebox80.myapplication9;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main22Activity extends AppCompatActivity implements View.OnClickListener {

    ScrollView svView;
    LinearLayout llRoot;
    LinearLayout llLayout;
    Custom cuif;
    TextView note;
    HashMap<Integer, Custom> hm;
    Film film;
    Film[] films = Film.values();
    Intent in;
    Intent i;
    //public static final String KEY_FILM_POSITION = "KEY_FILM_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hm = new HashMap<Integer, Custom>();
        llRoot = new LinearLayout(getApplicationContext());
        llLayout = new LinearLayout(getApplicationContext());
        svView = new ScrollView(getApplicationContext());
        note = new TextView(getApplicationContext());
        note.setTextSize(20);
        note.setTextColor(Color.BLACK);

        note.setText("Найденнные фильмы:");
        llRoot.setOrientation(LinearLayout.VERTICAL);
        llLayout.setOrientation(LinearLayout.VERTICAL);

        findFilm();

        svView.addView(llLayout);
        llRoot.addView(note);
        llRoot.addView(svView);
        setContentView(llRoot);
    }

    public void findFilm(){
        in = getIntent();
        Bundle extras = in.getExtras();
        Integer key = 0;
        Integer year = (Integer)extras.getInt(MainActivity.KEY_FILM_YEAR);
        String genre = extras.getString(MainActivity.KEY_FILM_GENRE, "NO_VALUE");
        String actor = extras.getString(MainActivity.KEY_FILM_ACTOR, "NO_VALUE");
        String type = extras.getString(MainActivity.KEY_FILM_TYPE, "NO_VALUE");

        for(Film a:films){
            if (year.toString().equals(a.getYear())) {
                if (genre.equals(a.getGenre()) || genre.equals("NO_VALUE")) {
                    if (actor.equals(a.getNameActor()) || actor.equals("NO_VALUE")) {
                        if (type.equals(a.getType()) || type.equals("NO_VALUE")) {
                            cuif = new Custom(getApplicationContext(), null);
                            cuif.ivFilm.setImageResource(a.getPic());
                            cuif.tvTitleС.setText(a.getNameFilm());
                            cuif.tvYearС.setText(a.getYear());
                            cuif.tvGenreС.setText(a.getGenre());
                            cuif.btn3.setOnClickListener(this);
                            cuif.btn3.setId(key);
                            hm.put(key, cuif);
                            llLayout.addView(cuif);
                        }
                    }
                }
            }
            key++;
        }
    };

    @Override
    public void onClick(View v) {
        i = new Intent(getApplicationContext(),
                Main2Activity.class);
        Set<Map.Entry<Integer, Custom>> set = hm.entrySet();
        for(Map.Entry<Integer, Custom> temp : set){
            if(temp.getValue().btn3.getId() == v.getId()) {
                i.putExtra(MainActivity.KEY_FILM_POSITION, (int)temp.getKey());
            }
        }
        startActivity(i);
    }
}
