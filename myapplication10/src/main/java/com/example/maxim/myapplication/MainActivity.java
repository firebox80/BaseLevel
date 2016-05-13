package com.example.maxim.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    AdapterData ad;
    String pathParent = "/";
    String backItem = "..";
    File f;
    FileFilter directoryFilter;
    FileFilter fileFilter;
    ArrayList<Data> currentData;
    ArrayList<String> namesList;
    ArrayList<String> size;
    ArrayList<Boolean> file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        directoryFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        };
        fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        };

        f = new File(pathParent);
        namesList = new ArrayList<String>();
        fillNameList(directoryFilter);
        fillNameList(fileFilter);
        size = getSize(namesList);
        file = getFile(namesList);
        currentData = fillData(namesList, size, file, pathParent);
        lv = (ListView)findViewById(R.id.lv);
        ad = new AdapterData(this, R.layout.item_layout_custom, currentData);
//        ad = new AdapterData(this, currentData);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String childName = ((TextView)view.findViewById(R.id.tvName)).getText().toString();
        File f1 = new File(pathParent, childName);

        if (f1.isDirectory() || childName == backItem) {
            namesList.clear();
            size.clear();
            file.clear();

            if (childName == backItem) {
                pathParent = f.getParent();
                f = new File(pathParent);
            } else {
                f = new File(pathParent, childName);
            }

            pathParent = f.getAbsolutePath();

            if (isFull(f)) {
                fillNameList(directoryFilter);
                fillNameList(fileFilter);
            }

            if (!pathParent.equals("/")) {
                namesList.add(0, backItem);
            }

            size = getSize(namesList);
            file = getFile(namesList);
            currentData = fillData(namesList, size, file, pathParent);

//            ad = new AdapterData(this, R.layout.item_layout_custom, currentData);
//            lv.setAdapter(ad);
            ad.notifyDataSetChanged(currentData);
        }
    }

    private Boolean isFull(File file) {
            return (file.isDirectory() && file.list() != null && (file.list().length > 0));
    }

    private ArrayList<String> getSize (ArrayList<String> namesList) {
        ArrayList<String> size = new ArrayList<>();
        File tmp;
        String value;

        for (String name: namesList) {
            value = "";
            tmp = new File(pathParent, name);
            if (!name.equals("..") && tmp.isFile()) {
                value = Long.toString(tmp.length());
            }
            size.add(value);
        }
        return size;
    }

    private ArrayList<Boolean> getFile (ArrayList<String> namesList) {
        ArrayList<Boolean> file = new ArrayList<>();
        File tmp;
        Boolean value;

        for (String name: namesList) {
            tmp = new File(pathParent, name);

            value = tmp.isFile();
            if (name=="..") {
                value = false;
            }
            file.add(value);
        }
        return file;
    }

    private ArrayList<Data> fillData (ArrayList<String> namesList, ArrayList<String> sizeList,
                                      ArrayList<Boolean> fileList,String pathParent){
        ArrayList<Data> currentData = new ArrayList<Data>();
        Data tmp;

        for (int i = 0; i < namesList.size(); i++) {
            tmp = new Data(namesList.get(i), sizeList.get(i), fileList.get(i), pathParent);
            currentData.add(tmp);
        }

        return currentData;
    }
    private void fillNameList (FileFilter valueFilter) {
        File[] arrName = f.listFiles(valueFilter);
        for (File l : arrName) {
            namesList.add(l.getName());
        }
    }

    public void notifyDataSetChanged(ArrayList<Data> currentData) {
        this.currentData = new ArrayList<Data>(currentData);
        ad.notifyDataSetChanged();
    }
}
