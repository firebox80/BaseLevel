package com.firebox80.myapplication6;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by user on 01.10.2015.
 */
public class CustomUserInfoView extends TableRow {

    public ImageView ivPhoto;
    public LinearLayout llData;
    public LinearLayout llLocation;
    public TextView tvName;
    public ImageView ivFlag;
    public TextView tvCountry;
    public RatingBar rbRating;

    public CustomUserInfoView(Context context) {

//        super(context);
        this(context, null);

    }

    public CustomUserInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TableRow trRoot = (TableRow)inflater.
                inflate(R.layout.custom_user_info_layout, this, true);
        llLocation = (LinearLayout) trRoot.findViewById(R.id.llLocation);

        ivPhoto = (ImageView) trRoot.findViewById(R.id.ivPhoto);
        llData = (LinearLayout) trRoot.findViewById(R.id.llData);
        tvName = (TextView) trRoot.findViewById(R.id.tvName);
        tvName.setText("Maxim Kashpur");
        ivFlag = (ImageView) trRoot.findViewById(R.id.ivFlag);
        tvCountry = (TextView) trRoot.findViewById(R.id.tvCountry);
        tvCountry.setText("Ukraine");
        rbRating = (RatingBar) trRoot.findViewById(R.id.rbRating);
        rbRating.setRating(3.7f);
    }
}