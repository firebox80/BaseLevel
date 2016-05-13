package com.firebox80.myapplication11;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 19.10.2015.
 */
public class AdapterList extends ArrayAdapter<Frend> {
    private Context context;
    private ArrayList<Frend> listFrends;
    static final String FREND_AVATAR = "FREND_AVATAR";
    static int positionItem;

    public AdapterList(Context context, int resource, ArrayList<Frend> listFrends) {
        super(context, resource, listFrends);
        this.context = context;
        this.listFrends = new ArrayList<Frend>(listFrends);
    }

    void notifyDataSetChanged(ArrayList<Frend> valueListFrends) {
        this.listFrends = valueListFrends;
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listFrends.size();
    }

    @Override
    public Frend getItem(int position) {
        return listFrends.get(position);
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
        ImageView ivAvatar = (ImageView) rootView.findViewById(R.id.ivAvatar);
        ivAvatar.setImageResource(listFrends.get(position).avatar);
        TextView tvFrendName = (TextView) rootView.findViewById(R.id.tvFrendName);
        tvFrendName.setText(listFrends.get(position).name);
        TextView tvFrends = (TextView) rootView.findViewById(R.id.tvFrends);
        tvFrends.setText(listFrends.get(position).getFrendsName());
        ivAvatar.setTag(position);
        ivAvatar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                positionItem = (int) v.getTag();

                int srcAvatar = listFrends.get(positionItem).avatar;
                Intent i = new Intent(context.getApplicationContext(), GridItem.class);

                if (!ListData.listUnusedAvatar.isEmpty()) {
                    i.putExtra(FREND_AVATAR, srcAvatar);
                    ((Activity) context).startActivityForResult(i, 1);
                 } else {
                    Toast.makeText(context.getApplicationContext(), "NO_VALUE", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}
