package com.example.maxim.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startGame;
    private Button loadGame;
    static SharedPreferences sp;
    static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startGame = (Button)findViewById(R.id.btnStartGame);
        loadGame = (Button)findViewById(R.id.btnLoadGame);

        sp = getSharedPreferences("my_pref2", MODE_PRIVATE);
        editor = sp.edit();

//        if(MainActivity.sp!=null && MainActivity.sp.getBoolean("IS_CONDITION_SAVE", false)){
        if(sp.contains("IS_CONDITION_SAVE")){
            loadGame.setEnabled(true);
        }else{
            loadGame.setEnabled(false);
        }

        startGame.setOnClickListener(this);
        loadGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
        switch (v.getId()){
            case R.id.btnLoadGame:

            case R.id.btnStartGame:
                startActivity(i);
                finish();
                break;
        }

    }
}
