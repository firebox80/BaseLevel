package com.firebox80.myapplication11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class GridItem extends AppCompatActivity {

    private AdapterGrid<Integer> adapter;
    private GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        gv = (GridView) findViewById(R.id.gv);
        adapter = new AdapterGrid<Integer>(this, android.R.layout.simple_list_item_1, ListData.listUnusedAvatar);
        gv.setAdapter(adapter);
        gv.setNumColumns(3);
//        gv.setVerticalSpacing(50);
//        gv.setHorizontalSpacing(50);
    }
}
