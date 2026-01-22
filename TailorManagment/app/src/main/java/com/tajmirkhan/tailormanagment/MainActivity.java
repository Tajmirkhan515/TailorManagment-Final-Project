package com.tajmirkhan.tailormanagment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tajmir khan on 9/11/2017.
 */

public class MainActivity extends AppCompatActivity {
    Button malebtn,femalebtn;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        startService(new Intent(MainActivity.this,MaleServiceClass.class));

        malebtn=(Button)findViewById(R.id.male_id);             // two button conectivity
        femalebtn=(Button)findViewById(R.id.female_id);


        toolbar=(Toolbar)findViewById(R.id.toolbar_id);        /// conective with toolbar
        setSupportActionBar(toolbar);                       //set actionbar
        getSupportActionBar().setTitle("Tailor Management");    // set Title
        toolbar.setNavigationIcon(R.drawable.menue);           // set the menue button in the top left corener

      //  drawerLayout=(DrawerLayout)findViewById(R.id.draweble_id);

       /* toolbar.setNavigationOnClickListener(new View.OnClickListener() {   // if you click for the menue button its do the below work
         @Override
         public void onClick(View view) {

             if(drawerLayout.isDrawerOpen(Gravity.LEFT))
             {
                 drawerLayout.closeDrawer(Gravity.LEFT);
             }
             else
             {
                 drawerLayout.openDrawer(Gravity.LEFT);
             }


         }
     });
               */



        malebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(MainActivity.this,MaleClass.class));
            }
        });

        femalebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,Female_class.class));
            }
        });

    }

    /**
     * Created by Tajmir khan on 10/9/2017.
     */

    public static class CustomeListViewOfShalwarkameez extends BaseAdapter {

        Context context;
        ArrayList<ShalwarKameezData> list=new ArrayList();
         CustomeListViewOfShalwarkameez(Context context,ArrayList<ShalwarKameezData> list)
         {
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
}
