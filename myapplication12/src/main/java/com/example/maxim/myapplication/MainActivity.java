package com.example.maxim.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements FragmentButton.onSomeEventListener {

//    FragmentButton fragmentButton;
    private FragmentManager fragmentManager;
    private FragmentLayout fragmentLayout;
    private FragmentTransaction ft;
    private FrameLayout flContainerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        fragmentLayout = (FragmentLayout) fragmentManager.findFragmentById(R.id.fragmentLayout);
//        fragmentLayout = new FragmentLayout();
//        ft = getFragmentManager().beginTransaction();
//        ft.add(R.id.flContainerLayout, fragmentLayout);
//        ft.commit();

//        if (fragmentLayout == null) {
//            fragmentLayout = new FragmentLayout();
//            fm.beginTransaction()
//                    .add(R.id.fragmentLayout, fragmentLayout)
//                    .commit();
//        }

//        flContainerLayout = (FrameLayout)findViewById(R.id.flContainerLayout);
//        Log.d("s", String.valueOf(flContainerLayout.getWidth()));
//        Log.d("s", String.valueOf(flContainerLayout.getHeight()));
    }

    @Override
    public void someEvent(int x, int y) {

        fragmentLayout.rl1Params.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x, getResources().getDisplayMetrics());
        fragmentLayout.rl1Params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y, getResources().getDisplayMetrics());
        fragmentLayout.rl1.setLayoutParams(fragmentLayout.rl1Params);

//        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
//       ((TextView)frag1.getView().findViewById(R.id.textView)).setText("Text from Fragment 2:" + s);
    }
}
