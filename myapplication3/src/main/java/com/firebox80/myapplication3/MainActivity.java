package com.firebox80.myapplication3;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        CompoundButton.OnCheckedChangeListener {

    FrameLayout fl;
    SeekBar sbR;
    SeekBar sbG;
    SeekBar sbB;
    TextView tvH;
    TextView tvD;
    CheckBox cb;
    int colorFone;
    int invertCcolorFone;
    final int COLORGB_END = 255;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl = (FrameLayout) findViewById(R.id.frameLayoutColor);
        sbR = (SeekBar) findViewById(R.id.seekBarR);
        sbG = (SeekBar) findViewById(R.id.seekBarG);
        sbB = (SeekBar) findViewById(R.id.seekBarB);
        tvH = (TextView) findViewById(R.id.textViewValueHex);
        tvD = (TextView) findViewById(R.id.textViewValueDec);
        cb =  (CheckBox) findViewById(R.id.checkBox);

        sbR.setOnSeekBarChangeListener(this);
        sbG.setOnSeekBarChangeListener(this);
        sbB.setOnSeekBarChangeListener(this);
        cb.setOnCheckedChangeListener(this);
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
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int value_R = sbR.getProgress();
        int value_G = sbG.getProgress();
        int value_B = sbB.getProgress();

        Action(value_R, value_G, value_B);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void Action(int value_R, int value_G, int value_B) {
        // TODO Auto-generated method stub
        String value_Total;

        sbR.setProgress(value_R);
        sbG.setProgress(value_G);
        sbB.setProgress(value_B);

        value_Total = Integer.toHexString(value_R + value_G + value_B);
        tvH.setText(value_Total);
        tvD.setText(value_R + ", " + value_G + ", " + value_B);

        colorFone = Color.rgb(value_R, value_G, value_B);

        fl.setBackgroundColor(colorFone);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.isChecked()){
            int value_R = COLORGB_END - sbR.getProgress();
            int value_G = COLORGB_END - sbG.getProgress();
            int value_B = COLORGB_END - sbB.getProgress();

            invertCcolorFone = Color.rgb(value_R, value_G, value_B);
            fl.setBackgroundColor(invertCcolorFone);

            Action(value_R, value_G, value_B);
        }

    }
}
