package com.example.android.myapplication;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by Maxim on 13.02.2016.
 */
public class DialogAddFrendFragment extends DialogFragment implements View.OnClickListener{

    private String[] day = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
            "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
            "28", "29", "30", "31"};
    private String[] Month = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август",
            "сентябрь", "октябрь", "ноябрь", "декабрь"};
    private String[] Year = {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
            "2010", "2011", "2012", "2013", "2015", "2016"};

    private onSetFrendListFragmentListener frendListFragmentListener;
    private Activity activity;
    private Bitmap resizedBitmap;

    private final int REQUEST_CODE_PHOTO = 1;
    private final String TAG = "TAG";

    private int avatar = R.drawable.ice;
    private int result;

    private String nameFrend;
    private String selectDay;
    private String selectMonth;
    private String selectYear;
    private String birthday;

    private View rootView;
    private ImageView ivAvatar;
    private Button cancel;
    private Button ok;
    private EditText name;
    private Spinner spDay;
    private Spinner spMonth;
    private Spinner spYear;

    private ArrayAdapter<?> adapterDay;
    private ArrayAdapter<?> adapterMonth;
    private ArrayAdapter<?> adapterYear;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dialog_fragment_layout, container, false);

        ivAvatar = (ImageView) rootView.findViewById(R.id.ivAvatar);
        name = (EditText) rootView.findViewById(R.id.etName);
        spDay = (Spinner)  rootView.findViewById(R.id.spDay);
        spMonth = (Spinner)  rootView.findViewById(R.id.spMonth);
        spYear = (Spinner)  rootView.findViewById(R.id.spYear);
        cancel = (Button) rootView.findViewById(R.id.btnCancel);
        ok = (Button) rootView.findViewById(R.id.btnOk);

        getDialog().setTitle("Add frend!");

        adapterDay = ArrayAdapter.createFromResource(
                getActivity(), R.array.day_array, android.R.layout.simple_spinner_item);
        adapterMonth = ArrayAdapter.createFromResource(
                getActivity(), R.array.month_array, android.R.layout.simple_spinner_item);
        adapterYear = ArrayAdapter.createFromResource(
                getActivity(), R.array.year_array, android.R.layout.simple_spinner_item);

        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spDay.setAdapter(adapterDay);
        spMonth.setAdapter(adapterMonth);
        spYear.setAdapter(adapterYear);

        ivAvatar.setOnClickListener(this);
        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnOk:
                nameFrend = name.getText().toString();
                selectDay = spDay.getSelectedItem().toString();
                selectMonth = spMonth.getSelectedItem().toString();
                selectYear = spYear.getSelectedItem().toString();
                birthday = selectDay + "/" + selectMonth + "/" + selectYear;
                if(result == getActivity().RESULT_OK) {
                    frendListFragmentListener.updateFrendListFragment(nameFrend, birthday, resizedBitmap);
                } else {
                    frendListFragmentListener.updateFrendListFragment(nameFrend, birthday, avatar);
                }
                dismiss();
                break;
            case R.id.ivAvatar:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_PHOTO);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == getActivity().RESULT_OK) {
                if (intent == null) {
                    Log.d(TAG, "Intent is null");
                } else {
                    Log.d(TAG, "Photo uri: " + intent.getData());
                    Bundle bndl = intent.getExtras();
                    if (bndl != null) {
                        Object obj = intent.getExtras().get("data");
                        if (obj instanceof Bitmap) {
                            Bitmap bitmap = (Bitmap) obj;
                            resizedBitmap = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                            ivAvatar.setImageBitmap(bitmap);
                            result = resultCode;
                        }
                    }
                }
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                Log.d(TAG, "Canceled");
            }
        }
    }
}
