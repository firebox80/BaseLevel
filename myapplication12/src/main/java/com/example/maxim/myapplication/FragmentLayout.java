package com.example.maxim.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Maxim on 30.01.2016.
 */
public class FragmentLayout extends Fragment {

    private Activity activity;

    RelativeLayout rlRoot;
    RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;

    private RelativeLayout.LayoutParams rlRootParams;
    RelativeLayout.LayoutParams rl1Params;
    private RelativeLayout.LayoutParams rl2Params;
    private RelativeLayout.LayoutParams rl3Params;


    int setValueWidth = 100;
    int setValueHeight = 100;

    private int widthDpi;
    private int heightDpi;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        final float scale = getResources().getDisplayMetrics().density;
//        int dpWidthInPx  = (int) (250 * scale);
//        int dpHeightInPx = (int) (300 * scale);

        widthDpi = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, setValueWidth, getResources().getDisplayMetrics());
        heightDpi  = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, setValueHeight, getResources().getDisplayMetrics());

        rlRoot = new RelativeLayout(activity);
        rlRootParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rlRoot.setLayoutParams(rlRootParams);

        rl1 = new RelativeLayout(activity);
        rl1.setBackgroundColor(Color.RED);
        rl1Params = new RelativeLayout.LayoutParams(widthDpi, heightDpi);
        rl1.setLayoutParams(rl1Params);
        rl1.setId(R.id.rl1);

        rl2 = new RelativeLayout(activity);
        rl2.setBackgroundColor(Color.BLUE);
        rl2Params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rl2Params.addRule(RelativeLayout.BELOW, R.id.rl1);
        rl2.setLayoutParams(rl2Params);
        rl2.setId(R.id.rl2);

        rl3 = new RelativeLayout(activity);
        rl3.setBackgroundColor(Color.GREEN);
        rl3Params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rl3Params.addRule(RelativeLayout.RIGHT_OF, R.id.rl1);
        rl3Params.addRule(RelativeLayout.END_OF, R.id.rl1);
        rl3Params.addRule(RelativeLayout.ABOVE, R.id.rl2);
        rl3.setLayoutParams(rl3Params);
        rl3.setId(R.id.rl3);

        rlRoot.addView(rl1);
        rlRoot.addView(rl2);
        rlRoot.addView(rl3);

        return rlRoot;
    }
}