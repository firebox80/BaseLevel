package com.example.android.myapplication;


import android.app.Activity;
import android.app.ListFragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class FrendListFragment extends ListFragment {
    private int position;
    private ArrayList listFrend;
    private AdapterFrendList mArrayAdapter;
    private onSetDialogDelFrendFragmentListener dialogDelFrendFragmentListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            dialogDelFrendFragmentListener = (onSetDialogDelFrendFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSetDialogDelFrendFragmentListener");
        }

        listFrend = ListData.initListFrends();
        mArrayAdapter = new AdapterFrendList(getActivity(), android.R.layout.simple_list_item_1, listFrend);

        setListAdapter(mArrayAdapter);
    }

    public void clearList(){
        listFrend.clear();
        mArrayAdapter.notifyDataSetChanged(listFrend);
    }

    public void updateList(String name, String birthday, int avatar){
        listFrend.add(new Frend(name, birthday, avatar));
        mArrayAdapter.notifyDataSetChanged(listFrend);
    }

    public void updateList(String name, String birthday, Bitmap avatar){
        listFrend.add(new Frend(name, birthday, avatar));
        mArrayAdapter.notifyDataSetChanged(listFrend);
    }

    public void deleteFrend(){
        listFrend.remove(position);
        mArrayAdapter.notifyDataSetChanged(listFrend);
    }

//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                position = arg2;
                dialogDelFrendFragmentListener.setDialogDelFrendFragment();
                return true;
            }
        });
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//
//        return textView;
//    }


}
