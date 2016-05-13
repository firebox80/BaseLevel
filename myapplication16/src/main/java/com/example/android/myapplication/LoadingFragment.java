package com.example.android.myapplication;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingFragment extends Fragment implements View.OnTouchListener, Animation.AnimationListener {

    private Activity activity;
    private View rootLayout;
    private ImageView center;
    private Animation animation;
    private Animation animation2;
    private Animation animation3;
    private ImageView ivWL1;
    private ImageView ivWL2;
    private ImageView ivWL3;
    private ImageView ivWR1;
    private ImageView ivWR2;
    private ImageView ivWR3;
    private boolean isConditionThread;
    private MyThread t;
    private onSetFrendListFragmentListener frendListFragmentListener;
    private Thread.State ts;

    private final int DURATION_ANIMATION = 2400;
    private final int DURATION_PAUSE = 2000;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

        try {
            frendListFragmentListener = (onSetFrendListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSetFrendListFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootLayout = inflater.inflate(R.layout.fragment_loading, container, false);

        center = (ImageView)rootLayout.findViewById(R.id.ivCenter);
        center.setOnTouchListener(this);

        ivWL1 = (ImageView)rootLayout.findViewById(R.id.ivWL1);
        ivWL2 = (ImageView)rootLayout.findViewById(R.id.ivWL2);
        ivWL3 = (ImageView)rootLayout.findViewById(R.id.ivWL3);
        ivWR1 = (ImageView)rootLayout.findViewById(R.id.ivWR1);
        ivWR2 = (ImageView)rootLayout.findViewById(R.id.ivWR2);
        ivWR3 = (ImageView)rootLayout.findViewById(R.id.ivWR3);

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.myanimation);
        animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.myanimation2);
        animation3 = AnimationUtils.loadAnimation(getActivity(), R.anim.myanimation3);
        animation.setAnimationListener(this);
        animation2.setAnimationListener(this);
        animation3.setAnimationListener(this);

        return rootLayout;
    }

    public void startAnimate(){
        ivWL1.startAnimation(animation);
        ivWL2.startAnimation(animation2);
        ivWL3.startAnimation(animation3);
        ivWR1.startAnimation(animation);
        ivWR2.startAnimation(animation2);
        ivWR3.startAnimation(animation3);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                center.setImageResource(R.drawable.center_touched);
                isConditionThread = true;

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        while (isConditionThread) {
                            Runnable process = new Runnable() {
                                @Override
                                public void run() {
                                    startAnimate();
                                }
                            };

                            activity.runOnUiThread(process);
                            try {
                                Thread.sleep(DURATION_ANIMATION);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(!isConditionThread){
                            Runnable process = new Runnable() {
                                @Override
                                public void run() {
                                    frendListFragmentListener.setFrendListFragment();
                                }
                            };
                            try {
                                Thread.sleep(DURATION_PAUSE);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            activity.runOnUiThread(process);
                        }
                    }
                };

//                if((t==null || ts == Thread.State.TERMINATED) && isConditionThread) {
                if(!(t!=null && t.isAlive()) && isConditionThread) {
                    t = new MyThread(r);
                    t.start();
//                    ts = t.getState();
                }
                return true;
            case MotionEvent.ACTION_UP:
                center.setImageResource(R.drawable.center);
                t.stopMe();
                return true;
        }
        return false;
    }

    public class MyThread extends Thread {

        public MyThread(Runnable runnable) {
            super(runnable);
        }

        public void stopMe() {
            isConditionThread = false;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        ivWL1.setVisibility(View.VISIBLE);
        ivWL2.setVisibility(View.VISIBLE);
        ivWL3.setVisibility(View.VISIBLE);
        ivWR1.setVisibility(View.VISIBLE);
        ivWR2.setVisibility(View.VISIBLE);
        ivWR3.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        ivWL1.setVisibility(View.INVISIBLE);
        ivWL2.setVisibility(View.INVISIBLE);
        ivWL3.setVisibility(View.INVISIBLE);
        ivWR1.setVisibility(View.INVISIBLE);
        ivWR2.setVisibility(View.INVISIBLE);
        ivWR3.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}

