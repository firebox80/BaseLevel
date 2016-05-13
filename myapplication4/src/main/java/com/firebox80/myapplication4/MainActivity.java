package com.firebox80.myapplication4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener,
        NumberPicker.OnValueChangeListener,
        NumberPicker.OnScrollListener {

    TextView tvPassword;
    EditText etPassword;
    CheckBox register;
    RadioButton upperCase;
    RadioButton lowerCase;
    RadioGroup caseLeter;
    CheckBox numeral;
    CheckBox punctuation;
    NumberPicker quantity;
    RadioButton directSeq;
    RadioButton reverseSeq;
    RadioButton editPassword;
    RadioGroup event;
    CheckBox autogenerate;
    Button generate;
    final int MAX_VALUE = 14;
    final int MIN_VALUE = 6;
    final int CURRENT_VALUE = 6;
    boolean regi = false;
    boolean upperLetter = false;
    boolean lowerLetter = false;
    boolean digit = false;
    boolean symbol = false;
    boolean reverse = false;
    boolean auto = false;

    MyPsw one = new MyPsw();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPassword = (EditText)  findViewById(R.id.editText);
        tvPassword = (TextView) findViewById(R.id.textViewOutPass);
        register = (CheckBox) findViewById(R.id.checkBoxRegister);
        upperCase = (RadioButton) findViewById(R.id.radioButtonUpperCase);
        upperCase.setEnabled(false);
        lowerCase = (RadioButton) findViewById(R.id.radioButtonLowerCase);
        lowerCase.setEnabled(false);
        caseLeter = (RadioGroup) findViewById(R.id.radioGroupLetter);

        numeral = (CheckBox) findViewById(R.id.checkBoxNumeral);
        punctuation = (CheckBox) findViewById(R.id.checkBoxPunctuation);

        quantity = (NumberPicker) findViewById(R.id.numberPicker);
        quantity.setMaxValue(MAX_VALUE);
        quantity.setMinValue(MIN_VALUE);
        quantity.setValue(CURRENT_VALUE);

        directSeq = (RadioButton) findViewById(R.id.radioButtonDirectSeq);
        reverseSeq = (RadioButton) findViewById(R.id.radioButtonReverseSeq);
        editPassword = (RadioButton) findViewById(R.id.radioButtonEditPass);
        event = (RadioGroup) findViewById(R.id.radioGroupEvent);

        autogenerate = (CheckBox) findViewById(R.id.checkBoxAutogeneration);
        generate = (Button) findViewById(R.id.buttonGeneration);

        register.setOnCheckedChangeListener(this);
        numeral.setOnCheckedChangeListener(this);
        punctuation.setOnCheckedChangeListener(this);
        quantity.setOnValueChangedListener(this);
        autogenerate.setOnCheckedChangeListener(this);
        generate.setOnClickListener(this);
        caseLeter.setOnCheckedChangeListener(this);
        event.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(editPassword.isChecked()) {
            event.clearCheck();
            tvPassword.setEnabled(true);
            tvPassword.setVisibility(View.VISIBLE);
            etPassword.setEnabled(false);
            etPassword.setVisibility(View.INVISIBLE);
        }
        if(R.id.buttonGeneration == ((Button)v).getId()) {
            if (!regi) {
                if (digit && symbol) {
                    one.heavy();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
                if (digit) {
                    one.digit();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
                if (symbol) {
                    one.symbol();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
            } else {
                if (upperLetter && digit && symbol) {
                    one.upperCaseHeavy();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
                if (upperLetter && symbol) {
                    one.upperCaseSymbol();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
                if (upperLetter && digit) {
                    one.upperCaseDigit();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
                if (upperLetter) {
                    one.upperCase();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
                if (lowerLetter && digit && symbol) {
                    one.lowerCaseHeavy();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }

                if (lowerLetter && symbol) {
                    one.lowerCaseSymbol();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
                if (lowerLetter && digit) {
                    one.lowerCaseDigit();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
                if (lowerLetter) {
                    one.lowerCase();
                    tvPassword.setText(one.passwordChars, 0, one.passLength);
                    return;
                }
            }
            one.letters();
            tvPassword.setText(one.passwordChars, 0, one.passLength);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (((CheckBox)buttonView).getId()) {

            case R.id.checkBoxRegister:
                if (isChecked) {
                    regi = true;
                    upperCase.setEnabled(true);
                    lowerCase.setEnabled(true);
                } else {
                    caseLeter.clearCheck();
                    upperCase.setEnabled(false);
                    lowerCase.setEnabled(false);

                    upperLetter = false;
                    lowerLetter = false;
                    regi = false;
                }
                break;
            case R.id.checkBoxNumeral:
                digit = numeral.isChecked();
                onCheckedChanged(autogenerate, auto);
                break;
            case R.id.checkBoxPunctuation:
                symbol = punctuation.isChecked();
                onCheckedChanged(autogenerate, auto);
                break;
            case R.id.checkBoxAutogeneration:
                if(isChecked){
                    onClick(generate);
                    generate.setEnabled(false);
                    auto = true;
                } else {
                    generate.setEnabled(true);
                    auto = false;
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId) {
                case -1:

                    break;
                case R.id.radioButtonUpperCase:
                    upperLetter = true;
                    lowerLetter = false;
                    onCheckedChanged(autogenerate, auto);
                    break;
                case R.id.radioButtonLowerCase:
                    upperLetter = false;
                    lowerLetter = true;
                    onCheckedChanged(autogenerate, auto);
                    break;
                case R.id.radioButtonEditPass:
                    tvPassword.setEnabled(false);
                    tvPassword.setVisibility(View.INVISIBLE);
                    etPassword.setEnabled(true);
                    etPassword.setVisibility(View.VISIBLE);
                    etPassword.setText(tvPassword.getText());
                    break;
                case R.id.radioButtonDirectSeq:
                    tvPassword.setEnabled(true);
                    tvPassword.setVisibility(View.VISIBLE);
                    etPassword.setEnabled(false);
                    etPassword.setVisibility(View.INVISIBLE);
                    if(reverse){
                        tvPassword.setText(reversString(tvPassword.getText()), 0, tvPassword.getText().length());
                        reverse = false;
                    }
                    break;
                case R.id.radioButtonReverseSeq:
                    tvPassword.setEnabled(true);
                    tvPassword.setVisibility(View.VISIBLE);
                    etPassword.setEnabled(false);
                    etPassword.setVisibility(View.INVISIBLE);
                    tvPassword.setText(reversString(tvPassword.getText()), 0, tvPassword.getText().length());
                    reverse = true;
                    break;
            }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        one.passLength = quantity.getValue();
        onCheckedChanged(autogenerate, auto);
    }

    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {

    }

    public char[] reversString (CharSequence value) {
        char[] arrValue  = value.toString().toCharArray();
        char[] tmp  = new char[arrValue.length];

        for (int i=0, j=arrValue.length - 1; i<arrValue.length; j--,i++ ){
            tmp[i] = arrValue[j];
        }
        return tmp;
    }
}
