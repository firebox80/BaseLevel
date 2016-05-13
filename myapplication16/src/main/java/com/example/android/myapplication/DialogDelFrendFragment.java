package com.example.android.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by Maxim on 18.02.2016.
 */
public class DialogDelFrendFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private onSetFrendListFragmentListener  setFrendListFragment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            setFrendListFragment = (onSetFrendListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSetFrendListFragmentListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog alert = new AlertDialog.Builder(getActivity())
                .setTitle("Alert!!!")
                .setPositiveButton("Ok", this)
                .setNegativeButton("Cancel", this)
                .setMessage("Do you want to remove the friend?")
                .create();
        return alert;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case AlertDialog.BUTTON_POSITIVE:
                setFrendListFragment.deleteFrendListFragment();
                break;
            case AlertDialog.BUTTON_NEGATIVE:
                break;
        }

    }
}
