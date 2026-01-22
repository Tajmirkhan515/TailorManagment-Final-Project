package com.tajmirkhan.tailormanagment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Tajmir khan on 12/14/2017.
 */

public class CustomListViewOfFemaleDesign extends BaseAdapter {
    ArrayList<Female_Design_Data> list=new ArrayList<>();
    Context context;

    public CustomListViewOfFemaleDesign(Context context,ArrayList<Female_Design_Data> list){
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
            view=inflater.inflate(R.layout.gallery_photo_for_custom_layout,null);
        }
        ImageView imageview=view.findViewById(R.id.imageView_id);

        TextView text2=view.findViewById(R.id.textview_id);
        String name=list.get(position).getName();
        text2.setText(name);
        byte[] arry =list.get(position).getImage();
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(arry, 0, arry.length);
            imageview.setImageBitmap(bitmap);
        }catch (Exception ex){

        }
        return view;
    }
}
