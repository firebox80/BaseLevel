package com.example.android.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AuthFragment extends Fragment implements View.OnClickListener {

    private EditText name;
    private EditText password;
    private Button enter;
    private onSetLoadingFragmentListener loadingFragmentListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            loadingFragmentListener = (onSetLoadingFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSetLoadingFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootLayout = inflater.inflate(R.layout.fragment_auth, container, false);

        name = (EditText) rootLayout.findViewById(R.id.etName);
        password = (EditText) rootLayout.findViewById(R.id.etPassword);
        enter = (Button) rootLayout.findViewById(R.id.btnEnter);

        enter.setOnClickListener(this);

        return rootLayout;
    }

    @Override
    public void onClick(View v) {
        loadingFragmentListener.setLoadingFragment();
    }

}
