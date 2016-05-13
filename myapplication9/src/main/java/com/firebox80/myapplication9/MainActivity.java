package com.firebox80.myapplication9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
                        implements  CompoundButton.OnCheckedChangeListener,
                                    View.OnClickListener,
                                    AdapterView.OnItemSelectedListener {
    Switch switch1;
    Spinner spFilm;
    LinearLayout llFind;
    TextView tvFind;
    TextView tvYear;
    NumberPicker numberPicker;
    TextView tvGenre1;
    Spinner spGenre;
    TextView tvRole;
    Spinner spRole;
    RadioGroup rgFilm;
    Button btFind;
    final int MAX_VALUE = 2015;
    final int MIN_VALUE = 2000;
    final int CURRENT_VALUE = 2008;
    Intent i;
    public static final String KEY_FILM_POSITION = "KEY_FILM_POSITION";
    public static final String KEY_FILM_YEAR = "KEY_FILM_YEAR";
    public static final String KEY_FILM_GENRE = "KEY_FILM_GENRE";
    public static final String KEY_FILM_ACTOR = "KEY_FILM_ACTOR";
    public static final String KEY_FILM_TYPE = "KEY_FILM_TYPE";

    Film film;
    Film[] films = Film.values();
    ArrayList<String> arrFilm = new ArrayList<String>();
    ArrayList<String> arrGenre = new ArrayList<String>();
    ArrayList<String> arrActor = new ArrayList<String>();


    private void fillArray(Film value, ArrayList<String>arFilm, ArrayList<String>arGenre,
                           ArrayList<String>arActor){
        for(Film el : films){
            arFilm.add(el.getNameFilm());
            if(!arGenre.contains(el.getGenre())) {
                arGenre.add(el.getGenre());
            }
            if(!arActor.contains(el.getNameActor())) {
                arActor.add(el.getNameActor());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillArray(film, arrFilm, arrGenre, arrActor);

        ArrayAdapter<String> adapterGenre =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrGenre);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapterActor =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrActor);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapterFilm =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrFilm);
        adapterFilm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        switch1 = (Switch)findViewById(R.id.switch1);
        spFilm = (Spinner)findViewById(R.id.spFilm);
        spFilm.setAdapter(adapterFilm);
        spFilm.setEnabled(false);
        spFilm.setVisibility(View.INVISIBLE);
        llFind = (LinearLayout)findViewById(R.id.llFind);
        tvFind = (TextView)findViewById(R.id.tvFind);
        tvYear = (TextView)findViewById(R.id.tvYear);
        numberPicker = (NumberPicker)findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(MAX_VALUE);
        numberPicker.setMinValue(MIN_VALUE);
        numberPicker.setValue(CURRENT_VALUE);
        tvGenre1 = (TextView)findViewById(R.id.tvGenre1);
        spGenre = (Spinner)findViewById(R.id.spGenre);
        spGenre.setAdapter(adapterGenre);
        tvRole = (TextView)findViewById(R.id.tvRole);
        spRole = (Spinner)findViewById(R.id.spRole);
        spRole.setAdapter(adapterActor);
        rgFilm = (RadioGroup)findViewById(R.id.rgGroup);
        btFind = (Button)findViewById(R.id.btFind);

        spFilm.setOnItemSelectedListener(this);
//        rgFilm.setOnClickListener(this);
        btFind.setOnClickListener(this);
        switch1.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            spFilm.setEnabled(true);
            spFilm.setVisibility(View.VISIBLE);

            tvFind.setEnabled(false);
            tvYear.setEnabled(false);
            numberPicker.setEnabled(false);
            tvGenre1.setEnabled(false);
            spGenre.setEnabled(false);
            tvRole.setEnabled(false);
            spRole.setEnabled(false);
            rgFilm.setEnabled(false);
            btFind.setEnabled(false);
            tvFind.setVisibility(View.INVISIBLE);
            tvYear.setVisibility(View.INVISIBLE);
            numberPicker.setVisibility(View.INVISIBLE);
            tvGenre1.setVisibility(View.INVISIBLE);
            spGenre.setVisibility(View.INVISIBLE);
            tvRole.setVisibility(View.INVISIBLE);
            spRole.setVisibility(View.INVISIBLE);
            rgFilm.setVisibility(View.INVISIBLE);
            btFind.setVisibility(View.INVISIBLE);
        } else {
            spFilm.setEnabled(false);
            spFilm.setVisibility(View.INVISIBLE);

            tvFind.setEnabled(true);
            tvYear.setEnabled(true);
            numberPicker.setEnabled(true);
            tvGenre1.setEnabled(true);
            spGenre.setEnabled(true);
            tvRole.setEnabled(true);
            spRole.setEnabled(true);
            rgFilm.setEnabled(true);
            btFind.setEnabled(true);
            tvFind.setVisibility(View.VISIBLE);
            tvYear.setVisibility(View.VISIBLE);
            numberPicker.setVisibility(View.VISIBLE);
            tvGenre1.setVisibility(View.VISIBLE);
            spGenre.setVisibility(View.VISIBLE);
            tvRole.setVisibility(View.VISIBLE);
            spRole.setVisibility(View.VISIBLE);
            rgFilm.setVisibility(View.VISIBLE);
            btFind.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        String type = "NO_VALUE";
        i = new Intent(getApplicationContext(),
                Main22Activity.class);
        int checkedRadioButtonId = rgFilm.getCheckedRadioButtonId();
        if(checkedRadioButtonId != -1) {
            RadioButton myradiobutton = (RadioButton) findViewById(checkedRadioButtonId);
            type = myradiobutton.getText().toString();
        }

        i.putExtra(KEY_FILM_YEAR, numberPicker.getValue());
        i.putExtra(KEY_FILM_GENRE, spGenre.getSelectedItem().toString());
        i.putExtra(KEY_FILM_ACTOR, spRole.getSelectedItem().toString());
        i.putExtra(KEY_FILM_TYPE, type);

        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position!=0) {
            i = new Intent(this,
                    Main2Activity.class);
            i.putExtra(KEY_FILM_POSITION, position);
            Log.d("LIST_VIEW_LOG", "позиция : ");
            startActivity(i);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}