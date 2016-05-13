package com.example.android.myapplication;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements onSetLoadingFragmentListener,
        onSetFrendListFragmentListener, View.OnClickListener, onSetDialogDelFrendFragmentListener {

    private Button add;
    private Button clear;
    private LinearLayout llButton;
    private AuthFragment af;
    private FragmentTransaction ft;
    private FrendListFragment flf;
    private LoadingFragment lf;
    private DialogAddFrendFragment afdf;
    private DialogDelFrendFragment ddff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llButton = (LinearLayout) findViewById(R.id.llButton);
        add = (Button) findViewById(R.id.btnAdd);
        clear = (Button) findViewById(R.id.btnClear);

        add.setOnClickListener(this);
        clear.setOnClickListener(this);

        ft = getFragmentManager().beginTransaction();
        af = new AuthFragment();
        ft.add(R.id.flLayout, af);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                ft = getFragmentManager().beginTransaction();
                afdf = new DialogAddFrendFragment();
                afdf.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog);
                afdf.show(ft, "dialogAdd");
                break;
            case R.id.btnClear:
                flf.clearList();
                break;
        }
    }

    @Override
    public void setLoadingFragment() {
        ft = getFragmentManager().beginTransaction();
        lf = new LoadingFragment();
        ft.replace(R.id.flLayout, lf);
        ft.commit();
    }

    @Override
    public void setFrendListFragment() {
        ft = getFragmentManager().beginTransaction();
        flf = new FrendListFragment();
        ft.replace(R.id.flLayout, flf);
        ft.commit();
        llButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateFrendListFragment(String name, String birthday, int avatar){
        flf.updateList(name, birthday, avatar);
    }

    @Override
    public void updateFrendListFragment(String name, String birthday, Bitmap avatar){
        flf.updateList(name, birthday, avatar);
    }

    @Override
    public void deleteFrendListFragment() {
        flf.deleteFrend();
    }

    @Override
    public void setDialogDelFrendFragment() {
        ft = getFragmentManager().beginTransaction();
        ddff = new DialogDelFrendFragment();
        ddff.show(ft, "dialogDel");
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

}
