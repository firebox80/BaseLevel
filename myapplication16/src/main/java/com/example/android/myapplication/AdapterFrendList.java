package com.example.android.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Maxim on 12.02.2016.
 */
public class AdapterFrendList extends ArrayAdapter<Frend>  {

    private Context context;
    private ArrayList<Frend> listFrend;

    public AdapterFrendList(Context context, int resource, ArrayList<Frend> listFrend) {
        super(context, resource, listFrend);
        this.context = context;
        this.listFrend = new ArrayList<Frend>(listFrend);
    }

    void notifyDataSetChanged(ArrayList<Frend> valueListFrend) {
        this.listFrend = valueListFrend;
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listFrend.size();
    }

    @Override
    public Frend getItem(int position) {
        return listFrend.get(position);
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
        } else {
            rootView = convertView;
        }

        ImageView ivAvatar = (ImageView) rootView.findViewById(R.id.ivAvatar);
        if(listFrend.get(position).getBitmap()==null){
            ivAvatar.setImageResource(listFrend.get(position).getAvatar());
        } else {
            ivAvatar.setImageBitmap(listFrend.get(position).getBitmap());
        }
        TextView tvFrendName = (TextView) rootView.findViewById(R.id.tvFrendName);
        tvFrendName.setText(listFrend.get(position).getName());
        TextView tvBirthday = (TextView) rootView.findViewById(R.id.tvBirthday);
        tvBirthday.setText(listFrend.get(position).getBirthday());

//        rootView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                return false;
//            }
//        });

//        ivAvatar.setTag(position);
//        ivAvatar.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                positionItem = (int) v.getTag();
//
//                int srcAvatar = listFrends.get(positionItem).avatar;
//                Intent i = new Intent(context.getApplicationContext(), GridItem.class);
//
//                if (!ListData.listUnusedAvatar.isEmpty()) {
//                    i.putExtra(FREND_AVATAR, srcAvatar);
//                    ((Activity) context).startActivityForResult(i, 1);
//                } else {
//                    Toast.makeText(context.getApplicationContext(), "NO_VALUE", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        return rootView;
    }
}
