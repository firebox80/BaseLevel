package com.example.maxim.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Maxim on 09.01.2016.
 */

public class AdapterData extends ArrayAdapter<Data> {
    Context context;
    ArrayList<Data> currentData;


//    public AdapterData(Context context, ArrayList<Data> currentData) {
//        this.context = context;
//        this.currentData = new ArrayList<Data>(currentData);
//    }

    public AdapterData(Context context, int resource, ArrayList<Data> currentData) {
        super(context, resource, currentData);
        this.context = context;
        this.currentData = new ArrayList<Data>(currentData);
    }

    public void notifyDataSetChanged(ArrayList<Data> value) {
        this.currentData = value;
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return currentData.size();
    }

    @Override
    public Data getItem(int position) {
        return currentData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = null;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.item_layout_custom, parent, false);
        } else{
            rootView = convertView;
        }

        ImageView ivFolderIcon = (ImageView)rootView.findViewById(R.id.ivFolderIcon);

        ivFolderIcon.setImageResource(R.drawable.picd);

        if(!currentData.get(position).pathParent.equals("/")) {
            ivFolderIcon.setImageResource(R.drawable.pica);
        }

        if(position!=0){
            if(currentData.get(position).file){
                ivFolderIcon.setImageResource(R.drawable.picf);
            } else {
                ivFolderIcon.setImageResource(R.drawable.picd);
            }
        }

        TextView tvName = (TextView)rootView.findViewById(R.id.tvName);
        tvName.setText(currentData.get(position).name);
        TextView tvSize = (TextView)rootView.findViewById(R.id.tvSize);
        tvSize.setText(currentData.get(position).size);

        return rootView;
    }
}