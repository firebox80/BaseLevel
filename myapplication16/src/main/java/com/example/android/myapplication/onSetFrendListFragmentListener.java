package com.example.android.myapplication;

import android.graphics.Bitmap;

/**
 * Created by Maxim on 10.02.2016.
 */
public interface onSetFrendListFragmentListener {
    void setFrendListFragment();
    void updateFrendListFragment(String name, String birthday, int avatar);
    void updateFrendListFragment(String name, String birthday, Bitmap avatar);
    void deleteFrendListFragment();
}
