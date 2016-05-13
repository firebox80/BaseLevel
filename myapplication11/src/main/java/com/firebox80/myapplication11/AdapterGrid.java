package com.firebox80.myapplication11;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Maxim on 24.11.2015.
 */
public class AdapterGrid<I> extends ArrayAdapter<Integer> {

    private Context context;
//    ArrayList<Integer> listUnusedAvatar;
    static final String AVATAR = "AVATAR";

    public AdapterGrid(Context context, int resource, ArrayList<Integer> listUnusedAvatar) {
        super(context, resource, listUnusedAvatar);
        this.context = context;
//        this.listUnusedAvatar = new ArrayList<Integer>(listUnusedAvatar);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = null;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.grid_item_custom, parent, false);
        } else{
            rootView = convertView;
        }
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        imageView.setImageResource(ListData.listUnusedAvatar.get(position));

        imageView.setTag(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionItem = (int) v.getTag();
                Integer avatar = ListData.listUnusedAvatar.get(positionItem);

                Intent in = ((Activity) context).getIntent();
                Bundle extras = in.getExtras();

                int srcAvatar = extras.getInt(AdapterList.FREND_AVATAR);

                if(srcAvatar != R.drawable.ice) {
//                    Toast.makeText(context.getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                    ListData.listUnusedAvatar.add(srcAvatar);
                }

                ListData.listUnusedAvatar.remove(positionItem);

                Intent out = new Intent();
                out.putExtra(AVATAR, avatar);
                ((Activity) context).setResult(Activity.RESULT_OK, out);
                ((Activity) context).finish();
            }
        });
        return rootView;
    }
}
