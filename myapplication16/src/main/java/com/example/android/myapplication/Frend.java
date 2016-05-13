package com.example.android.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;

/**
 * Created by Maxim on 12.02.2016.
 */
public class Frend extends Activity{

    private String name;
    private String birthday;
    private int avatar;
    private Bitmap bitmap;


    public Frend(String name, String birthday, int avatar) {
        this.name = name;
        this.birthday = birthday;
        this.avatar = avatar;
    }

    public Frend(String name, String birthday, Bitmap bitmap) {
        this.name = name;
        this.birthday = birthday;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }
    public String getBirthday() {
        return birthday;
    }
    public int getAvatar() {
        return avatar;
    }
    public Bitmap getBitmap() { return bitmap; }

    public void setName(String name) { this.name = name; }
}


