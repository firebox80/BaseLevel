package com.example.maxim.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Maxim on 30.01.2016.
 */
public class FragmentButton extends Fragment implements View.OnClickListener {

    private Button left;
    private Button right;
    private Button up;
    private Button down;

    private int setValueWidth = 100;
    private int setValueHeight = 100;

    private onSomeEventListener someEventListener;

    public interface onSomeEventListener {
        void someEvent(int x, int y);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            someEventListener = (onSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootLayout = inflater.inflate(R.layout.fragment_button,
                container);

        up = (Button) rootLayout.findViewById(R.id.btnUp);
        down = (Button) rootLayout.findViewById(R.id.btnDown);
        left = (Button) rootLayout.findViewById(R.id.btnLeft);
        right = (Button) rootLayout.findViewById(R.id.btnRight);

        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);

        return rootLayout;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnUp:
                setValueHeight -= 20;
                Log.d("setValueHeight" , String.valueOf(setValueHeight));
                break;
            case R.id.btnDown:
                setValueHeight += 20;
                Log.d("setValueHeight" , String.valueOf(setValueHeight));
                break;
            case R.id.btnLeft:
                setValueWidth -= 20;
                Log.d("setValueWidth" , String.valueOf(setValueWidth));
                break;
            case R.id.btnRight:
                setValueWidth += 20 ;
                Log.d("setValueWidth" , String.valueOf(setValueWidth));
                break;
        }

        Log.d("setValueWidth", String.valueOf(setValueWidth) );
        Log.d("setValueWidth", String.valueOf(setValueHeight) );
        someEventListener.someEvent(setValueWidth, setValueHeight);
    }
}
