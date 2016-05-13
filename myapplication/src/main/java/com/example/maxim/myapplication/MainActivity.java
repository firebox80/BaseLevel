package com.example.maxim.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    LinearLayout llSelectPass;
    Spinner spinner;
    Spinner spinner2;
    Button add;
    Button clean;
    EditText etPassword;
    EditText etSelectPass;
    int lenPassword = 10;
    String[] number = {"0","1","2","3","4","5","6","7","8","9"};
    String[] symbol = {"!","#","$","%","(",")","*","+",",","-",".","/",":",";","=","&",">","<","?","\"","@"};
    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llSelectPass = (LinearLayout)findViewById(R.id.ll);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        add = (Button)findViewById(R.id.button);
        clean = (Button)findViewById(R.id.button2);
        etPassword = (EditText)findViewById(R.id.editText);
        etSelectPass = (EditText)findViewById(R.id.editText2);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<?> adapter2 =
                ArrayAdapter.createFromResource(this, R.array.symbol, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        add.setOnClickListener(this);
        clean.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {

        int option = (int)(Math.random() * 2);
        int index;
        CharSequence random;
        String password = etPassword.getText().toString();

//        Log.e("password", password);
        if (v.getId()==R.id.button) {
            if (password.length() < lenPassword) {
                if (option == 0) {
                    index = (int) (Math.random() * 10);
                    random = number[index];
                } else {
                    index = (int) (Math.random() * 21);
                    random = symbol[index];
                }
                password += random;
                Log.e("temp", password);
                etPassword.setText(password);
                etPassword.setSelection(etPassword.getText().length());
            }
        } else {
            etPassword.setText("");
            etSelectPass.setText("");
            llSelectPass.setBackgroundColor(Color.WHITE);
            error = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        String password = etPassword.getText().toString();
        String selectPass = etSelectPass.getText().toString();
        String selectSymb = spinner.getSelectedItem().toString();

        if (error){
            selectPass = selectPass.substring(0, selectPass.length()-1);
        }

        if (!password.isEmpty() && position != 0) {
            error = false;

            if ((password.length()-1 > selectPass.length()) && selectSymb.equals(password.substring(selectPass.length(), selectPass.length()+1))) {
                llSelectPass.setBackgroundColor(Color.YELLOW);
                selectPass += selectSymb;
            } else if ((password.length() > selectPass.length()) && selectSymb.equals(password.substring(selectPass.length(), selectPass.length()+1))) {
                llSelectPass.setBackgroundColor(Color.GREEN);
                selectPass += selectSymb;
            } else if(password.length() > selectPass.length()) {
                llSelectPass.setBackgroundColor(Color.RED);
                selectPass += selectSymb;
                error = true;
            }
            etSelectPass.setText(selectPass);
        }
        spinner.setSelection(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }
}
