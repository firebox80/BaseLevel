package com.firebox80.myapplication9;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivFilm;
    TextView tvFilm2;
    TextView tvYear2;
    TextView tvGenre2;
    TextView tvRole2;
    Button button2;
    Film film;
    Film[] films = Film.values();
    Intent in;
    Intent out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        in = getIntent();
        out = new Intent();
        Bundle extras = in.getExtras();
        int index = extras.getInt(MainActivity.KEY_FILM_POSITION);
        film = films[index];

        ivFilm = (ImageView)findViewById(R.id.ivFilm);
        tvFilm2 = (TextView)findViewById(R.id.tvFilm2);
        tvYear2 = (TextView)findViewById(R.id.tvYear2);
        tvGenre2 = (TextView)findViewById(R.id.tvGenre2);
        tvRole2 = (TextView)findViewById(R.id.tvRole2);
        button2 = (Button)findViewById(R.id.button2);

        ivFilm.setImageResource(film.getPic());
        tvFilm2.setText(film.getNameFilm());
        tvYear2.setText(film.getYear());
        tvGenre2.setText(film.getGenre());
        tvRole2.setText(film.getNameActor());

        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        out.setAction(Intent.ACTION_VIEW);
        out.setData(Uri.parse(film.getUrl()));
        startActivity(out);
    }
}
