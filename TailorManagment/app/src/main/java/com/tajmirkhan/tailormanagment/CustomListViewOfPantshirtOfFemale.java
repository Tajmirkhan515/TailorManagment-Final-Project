package com.tajmirkhan.tailormanagment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tajmir khan on 12/14/2017.
 */

public class CustomListViewOfPantshirtOfFemale extends BaseAdapter {
    Context context;
    ArrayList<FemalePantshirt_Data> list;

    public CustomListViewOfPantshirtOfFemale(Context context,ArrayList<FemalePantshirt_Data> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.showlistviewdata,null);
        }
        TextView id =view.findViewById(R.id.id_id);
        TextView name=view.findViewById(R.id.name_id);
        TextView phone=view.findViewById(R.id.phone_id);
        String strid=list.get(position).getId();
        id.setText("ID:"+strid);
        String strname=list.get(position).getName();
        name.setText("Name:"+strname);
        String strphone=list.get(position).getPhone();
        phone.setText(strphone);

        return view;
    }
}
