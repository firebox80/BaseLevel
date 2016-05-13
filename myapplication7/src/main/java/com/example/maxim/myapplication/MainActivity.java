package com.example.maxim.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    int counter;
    TextView tv;
    Button bConter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.run_on_ui_thread_review_layout);
        tv = (TextView) findViewById(R.id.tv);
        bConter = (Button) findViewById(R.id.bCounter);
        bConter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        while (counter <= 10) {
                            Runnable process = new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText("" + (counter++));
                                }
                            };
                            runOnUiThread(process);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                Thread t = new Thread(r);
                t.start();
            }
        });
    }
}