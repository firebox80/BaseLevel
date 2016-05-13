package com.firebox80.myapplication9;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by user on 08.10.2015.
 */
public class Custom extends TableRow{

    public ImageView ivFilm;
    public TextView tvTitleС;
    public TextView tvYearС;
    public TextView tvGenreС;
    public Button btn3;


    public Custom(Context context) {
//        super(context);
        this(context, null);
    }

    public Custom(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TableRow trRoot = (TableRow)inflater.
                inflate(R.layout.activity_custom, this, true);
        ivFilm = (ImageView) trRoot.findViewById(R.id.ivFilm);
        tvTitleС = (TextView) trRoot.findViewById(R.id.tvTitleС);
        tvTitleС.setText("Сюрприз");
        tvYearС = (TextView) trRoot.findViewById(R.id.tvYearС);
        tvYearС.setText("2015");
        tvGenreС = (TextView) trRoot.findViewById(R.id.tvGenreС);
        tvGenreС.setText("Комедия");
        btn3 = (Button) trRoot.findViewById(R.id.button3);
    }
}
