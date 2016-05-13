package com.example.maxim.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class CustomReel extends FrameLayout {

    public ImageView ivReel;
    public FrameLayout flRoot;


    public CustomReel(Context context) {
        super(context);
//        this(context, null);
    }

    public CustomReel(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        flRoot = (FrameLayout)inflater.
                inflate(R.layout.activity_custom_reel, this, true);
        ivReel = (ImageView)flRoot.findViewById(R.id.ivReel);
    }
}
