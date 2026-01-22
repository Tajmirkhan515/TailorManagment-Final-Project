package com.tajmirkhan.tailormanagment;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tajmir khan on 12/22/2017.
 */

public class CustomeListViewOfViewOrderDone extends BaseAdapter {
    Context context;
    ArrayList<ViewOrderData_Off_Mode_setter_getter> list=new ArrayList();
    CustomeListViewOfViewOrderDone(Context context, ArrayList<ViewOrderData_Off_Mode_setter_getter> list){
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
        if(view==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.showlistviewdata,null);
        }
        DataBaseHelper db=new DataBaseHelper(context);

        ImageView imageid=view.findViewById(R.id.imageView_id);

        Cursor cr=db.showOneSpacificImage(list.get(position).getImageID());

        while (cr.moveToNext()){
            byte[] arry =cr.getBlob(0);
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(arry, 0, arry.length);
                imageid.setImageBitmap(bitmap);
            }catch (Exception ex){

            }
        }
        TextView userid=view.findViewById(R.id.id_id);
        TextView username=view.findViewById(R.id.name_id);
        TextView quantity=view.findViewById(R.id.phone_id);
        TextView price=view.findViewById(R.id.number_id);
        TextView current_time_date=view.findViewById(R.id.current_time_id);
        TextView delivery_time=view.findViewById(R.id.delivery_time_id);

        userid.setText(list.get(position).getId());
        username.setText(list.get(position).getName());
        quantity.setText("Order "+list.get(position).getQuantity());
        price.setText(",Rs "+list.get(position).getPrice());
        current_time_date.setText(list.get(position).getCurrenttime_date());
        delivery_time.setText(list.get(position).getDeliverytime()+"  "+list.get(position).getDeliveryDate());

        return view;
    }
}
