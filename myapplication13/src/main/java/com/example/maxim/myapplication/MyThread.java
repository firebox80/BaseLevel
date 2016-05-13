package com.example.maxim.myapplication;

/**
 * Created by Maxim on 29.01.2016.
 */
public class MyThread extends Thread {

    boolean finished = true;

    public MyThread(Runnable runnable) {
        super(runnable);
    }

    public void stopMe()
    {
        finished = false;

    }
}
