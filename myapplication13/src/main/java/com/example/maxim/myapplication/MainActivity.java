package com.example.maxim.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {

    private final int QUANTITY_REELS = 5;
    private final int THREAD_SLEEP_CHANGE = 100;
    private final String RUN_ON_UI_THREAD = "RunOnUiThread";
    private final String HANDLER = "Handler";
    private final String ASYNC_TASK = "AsyncTask";

    private String changeMethod = RUN_ON_UI_THREAD;
    private boolean isConditionPause;
    private boolean isConditionSave;
    private boolean isConditionGame;
    private int threadSleep;
    private int onQuantityTurnReel;
    private int offQuantityTurnReel;
    private int randomPic;
    private int visibleReels = QUANTITY_REELS;

    private MyThread t;
    private Handler h;
    private AsyncTask<Void, UpdateMessage, Void> at;
    private UpdateMessage um;

//    private LinearLayout llRoot;
//    private LinearLayout llSeekBar;
//    private LinearLayout llSeekBar1;
//    private FrameLayout flSeekBar11;
//    private FrameLayout flSeekBar12;
//    private LinearLayout llSeekBar2;
//    private FrameLayout flSeekBar21;
//    private FrameLayout flSeekBar22;
//    private LinearLayout llReel;
//    private FrameLayout flReel;
//    private LinearLayout llButton;
//    private FrameLayout flButton1;
//    private FrameLayout flButton2;
//    private FrameLayout flButton3;
//    private TextView tvNumberReel;
//    private TextView tvRotatesDrum;
//    static SharedPreferences sp;
//    private static SharedPreferences.Editor editor;
//    private CustomReel reel;
//    private LinearLayout.LayoutParams loparams;
//    private ArrayList<FrameLayout> listReeliv;

    private ArrayList<ImageView> listReel;
    private ArrayList<FrameLayout> listReelFrame;
    private ArrayList<Integer> listPic;

    private int[] arrPic = { R.drawable.ava, R.drawable.avaa, R.drawable.avab,
            R.drawable.avac, R.drawable.avad, R.drawable.avae,
            R.drawable.avb, R.drawable.avc, R.drawable.avd,
            R.drawable.ave, R.drawable.avf, R.drawable.avg};

    private RadioGroup rgMethod;
    private RadioButton rbRunOnUiThread;
    private RadioButton rbHandler;
    private RadioButton rbAsyncTask;

    private SeekBar sbNumber;
    private SeekBar sbTime;

    private Button start;
    private Button stop;
    private Button save;
    private Button pause;

    private ImageView ivPic1;
    private ImageView ivPic2;
    private ImageView ivPic3;
    private ImageView ivPic4;
    private ImageView ivPic5;

    private FrameLayout flPic1;
    private FrameLayout flPic2;
    private FrameLayout flPic3;
    private FrameLayout flPic4;
    private FrameLayout flPic5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        llRoot = new LinearLayout(this);
//        llRoot.setOrientation(LinearLayout.VERTICAL);
//        llSeekBar = new LinearLayout(this);
//        llSeekBar.setOrientation(LinearLayout.VERTICAL);
//        llSeekBar1 = new LinearLayout(this);
//        llSeekBar1.setOrientation(LinearLayout.VERTICAL);
//        flSeekBar11 = new FrameLayout(this);
//        flSeekBar12 = new FrameLayout(this);
//        llSeekBar2 = new LinearLayout(this);
//        llSeekBar2.setOrientation(LinearLayout.VERTICAL);
//        flSeekBar21 = new FrameLayout(this);
//        flSeekBar22 = new FrameLayout(this);
//        llReel = new LinearLayout(this);
//        llReel.setOrientation(LinearLayout.HORIZONTAL);
//        flReel = new FrameLayout(this);
//        llButton = new LinearLayout(this);
//        llButton.setOrientation(LinearLayout.VERTICAL);
//        flButton1 = new FrameLayout(this);
//        flButton2 = new FrameLayout(this);
//        flButton3 = new FrameLayout(this);
//        tvNumberReel = new TextView(this);
//        tvNumberReel.setText("Number of reels");
//        tvRotatesDrum = new TextView(this);
//        tvRotatesDrum.setText("Rotates drum");
//        sbNumber = new SeekBar(this);
//        sbNumber.setMax(5);
//        sbNumber.setProgress(1);
//        sbTime = new SeekBar(this);
//        sbNumber.setMax(5);
//        sbNumber.setProgress(1);
//        ivPic = new ImageView(this);
//        start = new Button(this);
//        start.setText("Start");
//        stop = new Button(this);
//        stop.setText("Stop");
//        save = new Button(this);
//        save.setText("Save");
//        flSeekBar11.addView(tvNumberReel);
//        flSeekBar12.addView(sbNumber);
//        llSeekBar1.addView(flSeekBar11);
//        llSeekBar1.addView(flSeekBar12);
//        flSeekBar21.addView(tvRotatesDrum);
//        flSeekBar22.addView(sbTime);
//        llSeekBar2.addView(flSeekBar21);
//        llSeekBar2.addView(flSeekBar22);
//        llSeekBar.addView(llSeekBar1);
//        llSeekBar.addView(llSeekBar2);
//        flReel.addView(ivPic);
//        llReel.addView(flReel);
//        flButton1.addView(start);
//        flButton2.addView(stop);
//        flButton3.addView(save);
//        llButton.addView(flButton1);
//        llButton.addView(flButton2);
//        llButton.addView(flButton3);
//        llRoot.addView(llSeekBar);
//        llRoot.addView(llReel);
//        llRoot.addView(llButton);
//        setContentView(llRoot);
//        llReel = (LinearLayout)findViewById(R.id.llReel);
//        sp = getSharedPreferences("my_pref", MODE_PRIVATE);
//        editor = sp.edit();
//        editor.clear();

        listReel = new ArrayList<ImageView>();
        listReelFrame = new ArrayList<FrameLayout>();

        listPic = new ArrayList<Integer>();
        listPic.add(0);
        listPic.add(0);
        listPic.add(0);
        listPic.add(0);
        listPic.add(0);

        ivPic1 = (ImageView)findViewById(R.id.ivPic1);
        ivPic2 = (ImageView)findViewById(R.id.ivPic2);
        ivPic3 = (ImageView)findViewById(R.id.ivPic3);
        ivPic4 = (ImageView)findViewById(R.id.ivPic4);
        ivPic5 = (ImageView)findViewById(R.id.ivPic5);
        listReel.add(ivPic1);
        listReel.add(ivPic2);
        listReel.add(ivPic3);
        listReel.add(ivPic4);
        listReel.add(ivPic5);

        flPic1 = (FrameLayout)findViewById(R.id.flPic1);
        flPic2 = (FrameLayout)findViewById(R.id.flPic2);
        flPic3 = (FrameLayout)findViewById(R.id.flPic3);
        flPic4 = (FrameLayout)findViewById(R.id.flPic4);
        flPic5 = (FrameLayout)findViewById(R.id.flPic5);
        listReelFrame.add(flPic1);
        listReelFrame.add(flPic2);
        listReelFrame.add(flPic3);
        listReelFrame.add(flPic4);
        listReelFrame.add(flPic5);

        rgMethod = (RadioGroup)findViewById(R.id.rgMethod);
        rbRunOnUiThread = (RadioButton)findViewById(R.id.rbRunOnUiThread);
        rbHandler = (RadioButton)findViewById(R.id.rbHandler);
        rbAsyncTask = (RadioButton)findViewById(R.id.rbAsyncTask);
        sbNumber = (SeekBar)findViewById(R.id.sbNumber);
        sbTime = (SeekBar)findViewById(R.id.sbTime);
        start = (Button)findViewById(R.id.btnStart);
        stop = (Button)findViewById(R.id.btnStop);
        save = (Button)findViewById(R.id.btnSave);
        pause = (Button)findViewById(R.id.btnPause);

        if(StartActivity.sp.contains("IS_CONDITION_SAVE")){
            isConditionSave = StartActivity.sp.getBoolean("IS_CONDITION_SAVE", false);
            if(isConditionSave) {
                save.setText("Load");
            } else {
                save.setText("Save");
            }
            Log.d("w", String.valueOf(isConditionSave));
        }
//        listReel = new ArrayList<CustomReel>();
//        listReeliv = new ArrayList<FrameLayout>();
//        listReeliv.add(ivPic);
//        reel = (CustomReel)findViewById(R.id.activity_custom_reel);
//        reel.ivReel.setBackgroundColor(Color.BLUE);
//        reel = new CustomReel(getApplicationContext(), null);
//        reel.ivReel.setBackgroundColor(Color.BLUE);
//        listReel.add(reel);
//        llReel.addView(reel);
//        addReel();

        h = new MyHandler(this);
/*
        h = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle b = msg.getData();
                Log.d("onQuantityTurnReel", String.valueOf(b.getInt("onQuantityTurnReel")));
                Log.d("randomPic", String.valueOf(b.getInt("randomPic")));
                listReel.get(b.getInt("onQuantityTurnReel")).setImageResource(arrPic[b.getInt("randomPic")]);
            }
        };
*/
        rgMethod.setOnCheckedChangeListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        save.setOnClickListener(this);
        pause.setOnClickListener(this);
        sbNumber.setOnSeekBarChangeListener(this);
        sbTime.setOnSeekBarChangeListener(this);
    }

    static class MyHandler extends Handler {
        WeakReference<MainActivity> wrActivity;

        public MyHandler(MainActivity activity) {
            wrActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            MainActivity activity = wrActivity.get();
            if (activity != null) {
                Bundle b = msg.getData();
                activity.listReel.get(b.getInt("onQuantityTurnReel")).setImageResource(activity.arrPic[b.getInt("randomPic")]);
            }
        }
    }

    private class ValidAsyncTask extends AsyncTask<Void, UpdateMessage, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            while (isConditionGame) {
                onQuantityTurnReel = offQuantityTurnReel;
                for (onQuantityTurnReel = offQuantityTurnReel; onQuantityTurnReel < visibleReels && !isConditionPause; onQuantityTurnReel++) {
                    randomPic = (int) (Math.random() * (arrPic.length));
                    listPic.set(onQuantityTurnReel, randomPic);
                    Log.d("RUN", "AsyncTask");
                    try {
                        Thread.sleep(threadSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    um = new UpdateMessage(onQuantityTurnReel, randomPic);
                    publishProgress(um);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(UpdateMessage... values) {
            super.onProgressUpdate(values);
            listReel.get(values[0].onQuantityTurnReel).setImageResource(arrPic[values[0].randomPic]);
        }
    }

    private class UpdateMessage{

        int onQuantityTurnReel;
        int randomPic;

        public UpdateMessage(int onQuantityTurnReel, int randomPic) {
            super();
            this.onQuantityTurnReel = onQuantityTurnReel;
            this.randomPic = randomPic;
        }
    }

//    public void addReel(){
//        reel = new CustomReel(getApplicationContext(), null);
//        loparams = (FrameLayout.LayoutParams)reel.flRoot.getLayoutParams();
//        loparams.width = 50;
//        loparams.weight = 1;
//        listReel.add(reel);
//        llReel.addView(reel);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        isConditionGame = false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnStart:
                if(!isConditionPause) {
                    isConditionGame = true;
                    offQuantityTurnReel = 0;
                    threadSleep = THREAD_SLEEP_CHANGE * (sbTime.getProgress() + 1);
                }
                break;
            case R.id.btnStop:
//                t.stopMe();
                if(!isConditionPause) {
                    offQuantityTurnReel += 1;
                    if (offQuantityTurnReel == visibleReels) {
                        isConditionGame = false;
                    }
                }
                break;
            case R.id.btnPause:
                if(isConditionPause){
                    pause.setText("Pause");
                    isConditionPause = false;
                }else{
                    pause.setText("Continue");
                    isConditionPause = true;
                }
                break;
            case R.id.btnSave:
                if(isConditionSave){
                    save.setText("Save");

                    isConditionSave = false;
                    StartActivity.editor.putBoolean("IS_CONDITION_SAVE", isConditionSave);

//                    if(!changeMethod.equals(StartActivity.sp.getString("CHANGE_METHOD", changeMethod))){
//                        t.stopMe();
//                        Log.d("isConditionGame", String.valueOf(isConditionGame));
//                    }

                    changeMethod = StartActivity.sp.getString("CHANGE_METHOD", changeMethod);

                    switch (changeMethod){
                        case RUN_ON_UI_THREAD:
                            rbRunOnUiThread.setChecked(true);
                            break;
                        case HANDLER:
                            rbHandler.setChecked(true);
                            break;
                        case ASYNC_TASK:
                            rbAsyncTask.setChecked(true);
                            break;
                    }

                    visibleReels = StartActivity.sp.getInt("VISIBLE_REELS", visibleReels);
                    sbNumber.setProgress(visibleReels);

                    threadSleep = StartActivity.sp.getInt("THREAD_SLEEP", threadSleep);
                    sbTime.setProgress(threadSleep/THREAD_SLEEP_CHANGE - 1);

                    isConditionPause = StartActivity.sp.getBoolean("IS_CONDITION_PAUSE", isConditionPause);

                    if(isConditionPause){
                        pause.setText("Continue");
                    } else {
                        pause.setText("Pause");
                    }

                    isConditionGame = StartActivity.sp.getBoolean("IS_CONDITION_GAME", isConditionGame);
                    offQuantityTurnReel = StartActivity.sp.getInt("OFF_QUANTITY_TURN_REEL", offQuantityTurnReel);
                    onQuantityTurnReel = StartActivity.sp.getInt("ON_QUANTITY_TURN_REEL", onQuantityTurnReel);
                    listReel.get(0).setImageResource(arrPic[StartActivity.sp.getInt("IV_PIC1", listPic.get(0))]);
                    listReel.get(1).setImageResource(arrPic[StartActivity.sp.getInt("IV_PIC2", listPic.get(1))]);
                    listReel.get(2).setImageResource(arrPic[StartActivity.sp.getInt("IV_PIC3", listPic.get(2))]);
                    listReel.get(3).setImageResource(arrPic[StartActivity.sp.getInt("IV_PIC4", listPic.get(3))]);
                    listReel.get(4).setImageResource(arrPic[StartActivity.sp.getInt("IV_PIC5", listPic.get(4))]);
                }else{
                    isConditionSave = true;
                    save.setText("Load");

                    StartActivity.editor.putString("CHANGE_METHOD", changeMethod);
                    StartActivity.editor.putInt("VISIBLE_REELS", visibleReels);
                    StartActivity.editor.putInt("THREAD_SLEEP", threadSleep);
                    StartActivity.editor.putBoolean("IS_CONDITION_SAVE", isConditionSave);
                    StartActivity.editor.putBoolean("IS_CONDITION_PAUSE", isConditionPause);
                    StartActivity.editor.putBoolean("IS_CONDITION_GAME", isConditionGame);
                    StartActivity.editor.putInt("OFF_QUANTITY_TURN_REEL", offQuantityTurnReel);
                    StartActivity.editor.putInt("ON_QUANTITY_TURN_REEL", onQuantityTurnReel);
                    StartActivity.editor.putInt("IV_PIC1", listPic.get(0));
                    StartActivity.editor.putInt("IV_PIC2", listPic.get(1));
                    StartActivity.editor.putInt("IV_PIC3", listPic.get(2));
                    StartActivity.editor.putInt("IV_PIC4", listPic.get(3));
                    StartActivity.editor.putInt("IV_PIC5", listPic.get(4));
                }
                StartActivity.editor.commit();
                break;
        }

        switch (changeMethod) {

            case RUN_ON_UI_THREAD:
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        while (isConditionGame) {
                            for (onQuantityTurnReel = offQuantityTurnReel; onQuantityTurnReel < visibleReels && !isConditionPause; onQuantityTurnReel++) {
                                randomPic = (int) (Math.random() * (arrPic.length));
                                listPic.set(onQuantityTurnReel, randomPic);

                                Log.d("RUN", "RUN");

                                Runnable process = new Runnable() {
                                    @Override
                                    public void run() {
                                        listReel.get(onQuantityTurnReel).setImageResource(arrPic[randomPic]);
                                    }
                                };
                                runOnUiThread(process);
                                try {
                                    Thread.sleep(threadSleep);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                };
                if(!(t!=null && t.isAlive()) && isConditionGame) {
                    t = new MyThread(r);
                    t.start();
                }
                break;

            case HANDLER:
                Runnable r1 = new Runnable() {
                    @Override
                    public void run() {
                        while (isConditionGame) {
                            for (onQuantityTurnReel = offQuantityTurnReel; onQuantityTurnReel < visibleReels && !isConditionPause; onQuantityTurnReel++) {
                                randomPic = (int) (Math.random() * (arrPic.length));
                                listPic.set(onQuantityTurnReel, randomPic);

                                Log.d("RUN", "HANDLER");

                                try {
                                    Thread.sleep(threadSleep);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Message msg = new Message();
                                Bundle data = new Bundle();
                                data.putInt("onQuantityTurnReel", onQuantityTurnReel);
                                data.putInt("randomPic", randomPic);
                                msg.setData(data);
                                h.sendMessage(msg);
                            }
                        }
                    }
                };
                if(!(t!=null && t.isAlive()) && isConditionGame) {
                    t = new MyThread(r1);
                    t.start();
                }
                break;
            case ASYNC_TASK:
                at = new ValidAsyncTask();
                at.execute();
                break;
        }
    }

    public class MyThread extends Thread {

        public MyThread(Runnable runnable) {
            super(runnable);
        }

        public void stopMe(){
            isConditionGame = false;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.sbNumber:
                visibleReels = sbNumber.getProgress();

                for (int i = visibleReels; i<listReelFrame.size(); i++){
                    listReelFrame.get(i).setVisibility(View.GONE);
                }
                for (int i = 0; i< visibleReels; i++){
                    listReelFrame.get(i).setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sbTime:
                threadSleep = THREAD_SLEEP_CHANGE * (sbTime.getProgress() + 1);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){
            case R.id.rbRunOnUiThread:
                changeMethod = RUN_ON_UI_THREAD;
                break;
            case R.id.rbHandler:
                changeMethod = HANDLER;
                break;
            case R.id.rbAsyncTask:
                changeMethod = ASYNC_TASK;
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
