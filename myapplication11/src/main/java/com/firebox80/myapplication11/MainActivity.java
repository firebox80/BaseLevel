package com.firebox80.myapplication11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,
        DialogInterface.OnClickListener{

    private Button btAddFrend;
    private Button btnDialogOkAddFrend;
    private Button btnDialogAdd;
    private Button btnDialogDel;
    private Button btnDialogYes;
    private Button btnDialogCancel;
    private EditText etName;
    private AdapterList ad;
    private AlertDialog.Builder builder;
    private AlertDialog dialogAdd, dialogDel, dialogNewFrend, dialogFrendsFrend, dialogNofrendsFrend;
    private ArrayList<Frend> listFrends;
    private ArrayList<Integer> listUnusedAvatar;
    private ArrayList<String> listNoFrendsFrend;
    private ArrayList<String> listFrendsFrend;
    private int positionFrend;
    private Frend newFrend;
    private ListView lv;
    private LayoutInflater li;
    private String textNameNewFrend;
    private SparseBooleanArray sbArray;
    private View contentLayout;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sp = getSharedPreferences("my_prefs", MODE_PRIVATE);
//        editor = sp.edit();
        sbArray = new SparseBooleanArray();
        listFrends = (ArrayList<Frend>) getLastNonConfigurationInstance();
        if(listFrends == null){
            listFrends = ListData.initListFrends();
        }

//        Set<Frend> set = new HashSet<Frend>();
//        set.addAll(listFrends);
//        editor.putString(TASKS, ObjectSerializer.serialize(listFrends));
//        editor.commit();

        listUnusedAvatar = ListData.createUnusedAvatar(listFrends);

        lv = (ListView) findViewById(R.id.lv);
        lv.setItemsCanFocus(false);

        ad = new AdapterList(this, R.layout.item_layout_custom, listFrends);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
        lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        btAddFrend = (Button) findViewById(R.id.btAddFrend);
        btAddFrend.setOnClickListener(this);
    }

//    public void addTask(Frend t) {
//        if (null == listFrends) {
//            listFrends = new ArrayList<Frend>();
//        }
//        listFrends.add(t);
//
//        //save the task list to preference
//        SharedPreferences prefs = getSharedPreferences("SHARED_PREFS_FILE", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        try {
//            editor.putString("TASKS", ObjectSerializer.serialize(listFrends));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        editor.commit();
//    }


    @Override
    public Object onRetainNonConfigurationInstance() {
        return listFrends;
    }

    @Override
    public void onClick(View v) {
        onClickShowDialog(v);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        positionFrend = position;
        builder = new AlertDialog.Builder(this);
        li = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentLayout = li.inflate(R.layout.dialog_add_del_content, null);

        btnDialogAdd = (Button) contentLayout.findViewById(R.id.btnDialogAdd);
        btnDialogDel = (Button) contentLayout.findViewById(R.id.btnDialogDel);

        btnDialogAdd.setOnClickListener(this);
        btnDialogDel.setOnClickListener(this);
        builder.setView(contentLayout);

        dialogNewFrend = builder.create();
        dialogNewFrend.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        positionFrend = position;
        onClickShowDialog(view);
        return true;
    }

    public void onClickShowDialog(View v){
        int id = v.getId();
        contentLayout = null;
        builder = new AlertDialog.Builder(this);
        li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (id) {
            case R.id.btAddFrend:
                contentLayout = li.inflate(R.layout.dialog_add_contact, null);
                etName = (EditText) contentLayout.findViewById(R.id.etName);
                builder.setView(contentLayout);
                builder.setPositiveButton("Да", myClickListener);
                dialogAdd = builder.create();
                dialogAdd.show();
                btnDialogOkAddFrend = dialogAdd.getButton(DialogInterface.BUTTON_POSITIVE);
                btnDialogOkAddFrend.setBackgroundColor(Color.parseColor("#414141"));
                btnDialogOkAddFrend.setTextColor(Color.parseColor("#bab9b9"));
                break;
            case R.id.btnDialogDel:
                boolean[] checkedFrend = new boolean[listFrends.get(positionFrend).listFrends.size()];
                Arrays.fill(checkedFrend, true);

                listFrendsFrend = new ArrayList<String> (listFrends.get(positionFrend).listFrends);
                String[] arrFrends = listFrendsFrend.toArray(new String[listFrendsFrend.size()]);

                builder.setMultiChoiceItems(arrFrends, checkedFrend, onMultiChoiceClickListener);
                builder.setPositiveButton("Да", myClickListener);
                dialogFrendsFrend = builder.create();
                dialogFrendsFrend.setTitle("Список друзей");
                dialogFrendsFrend.show();
                break;
            case R.id.btnDialogAdd:
                boolean[] checkedNoFrend = new boolean[listFrends.get(positionFrend).listNoFrends.size()];
                Arrays.fill(checkedNoFrend, true);

                listNoFrendsFrend = new ArrayList<String> (listFrends.get(positionFrend).listNoFrends);
                String[] arrNoFrends = listNoFrendsFrend.toArray(new String[listNoFrendsFrend.size()]);

                builder.setMultiChoiceItems(arrNoFrends, checkedNoFrend, onMultiChoiceClickListener);

                builder.setPositiveButton("Да", myClickListener);
                dialogNofrendsFrend = builder.create();
                dialogNofrendsFrend.setTitle("Список не друзей");
                dialogNofrendsFrend.show();
                break;
            default :
                contentLayout = li.inflate(R.layout.dialog_del_content, null);
                builder.setView(contentLayout);

                builder.setPositiveButton(android.R.string.yes, myClickListener);
                builder.setNegativeButton(android.R.string.no, myClickListener);
                dialogDel = builder.create();
                dialogDel.show();

                btnDialogYes = dialogDel.getButton(DialogInterface.BUTTON_POSITIVE);
                btnDialogCancel = dialogDel.getButton(DialogInterface.BUTTON_NEGATIVE);

                btnDialogYes.setBackgroundColor(Color.parseColor("#414141"));
                btnDialogYes.setTextColor(Color.parseColor("#bab9b9"));
                btnDialogCancel.setBackgroundColor(Color.parseColor("#414141"));
                btnDialogCancel.setTextColor(Color.parseColor("#bab9b9"));
                break;
        }
    }

    private DialogInterface.OnClickListener myClickListener =
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dialog.equals(dialogDel)) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                ListData.listUnusedAvatar.add(listFrends.get(positionFrend).avatar);
//                                listUnusedAvatar.add(listFrends.get(positionFrend).avatar);
                                ListData.delFrend(positionFrend, listFrends);
                                break;
                        }
                    }
                    if (dialog.equals(dialogAdd)) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                textNameNewFrend = etName.getText().toString();
                                for(Frend value:listFrends){
                                    value.listNoFrends.add(textNameNewFrend);
                                }
                                newFrend = new Frend(textNameNewFrend, R.drawable.ice, listFrends);
                                listFrends.add(newFrend);
                                break;
                        }
                    }
                    if (dialog.equals(dialogNewFrend)) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                    if (dialog.equals(dialogFrendsFrend)) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                for (int i = 0; i < sbArray.size(); i++) {
                                    int key = sbArray.keyAt(i);
                                    String name = listFrendsFrend.get(key);
                                    if (!sbArray.get(key)) {
                                        listFrends.get(positionFrend).listNoFrends.add(name);
                                        listFrends.get(positionFrend).listFrends.remove(name);
                                    }
                                }
                            sbArray.clear();
                            break;
                        }
                    }
                    if (dialog.equals(dialogNofrendsFrend)) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                for (int i = 0; i < sbArray.size(); i++) {
                                    int key = sbArray.keyAt(i);
                                    String name = listNoFrendsFrend.get(key);
                                    if (!sbArray.get(key)){
                                        listFrends.get(positionFrend).listFrends.add(name);
                                        listFrends.get(positionFrend).listNoFrends.remove(name);
                                    }
                                }
                            sbArray.clear();
                            break;
                        }
                    }
                    ad.notifyDataSetChanged(listFrends);
                }
            };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:

                int avatar = data.getExtras().getInt(AdapterGrid.AVATAR);
                listFrends.get(AdapterList.positionItem).avatar = avatar;
                ad.notifyDataSetChanged(listFrends);

                break;
            default:
                Toast.makeText(this, "Аватарка не выбрана", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener =
            new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    sbArray = ((AlertDialog)dialog).getListView().getCheckedItemPositions();
                }
            };
    /**
     * This method will be invoked when a button in the dialog is clicked.
     *
     * @param dialog The dialog that received the click.
     * @param which  The button that was clicked (e.g.
     *               {@link DialogInterface#BUTTON1}) or the position
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
