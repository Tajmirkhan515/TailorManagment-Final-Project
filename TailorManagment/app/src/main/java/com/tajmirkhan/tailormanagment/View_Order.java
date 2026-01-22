package com.tajmirkhan.tailormanagment;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.checked;
import static android.R.attr.id;
import static android.R.id.list;
import static android.R.id.tabhost;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.tajmirkhan.tailormanagment.R.id.tabHost;

public class View_Order extends AppCompatActivity {
ListView listremaining,listdone;

    List selectNumbderOfData_List=new ArrayList();
    int count=0;

    TabHost tabhost;
    Toolbar toolbar;
    DataBaseHelper db;
    CustomeListViewOfViewOrder customlistorder;
    CustomeListViewOfViewOrderDone doneList;
    ArrayList<ViewOrderData_Off_Mode_setter_getter> arraylistoff=new ArrayList<>();
    ArrayList<View_Order_Data_setter_getter> arraylist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view__order_activity);

        listremaining=(ListView)findViewById(R.id.listview_view_order_id);
        listdone=(ListView)findViewById(R.id.listview_view_order_done_id);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Design");// it use for OptionMenu without this its not suport

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tabhost=(TabHost)findViewById(tabHost);
        tabhost.setup();

        //Tab 1
        TabHost.TabSpec spec = tabhost.newTabSpec("Tab One");
        spec.setContent(R.id.ShalwareKameez_tab);
        spec.setIndicator("Remaining");
        tabhost.addTab(spec);

        // tab 2
        spec = tabhost.newTabSpec("tab two");
        spec.setContent(R.id.pantshirt_tab);
        spec.setIndicator("Done");
        tabhost.addTab(spec);

        db=new DataBaseHelper(this);
        viewAllUserInListviewON();
        viewallUserinListviewOFF();
        listremaining.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listdone.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listdone.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode actionMode, int i, long l, boolean checked) {
                if(checked){
                    Log.e("aa","above the lis");
                    selectNumbderOfData_List.add(arraylistoff.get(i).getId().toString());
                    Log.e("aa","below the lis");
                    count++;
                    actionMode.setTitle(count+" selected");
                    Log.e("aa","bellow title");
                }else {Log.e("aa","else");
                    selectNumbderOfData_List.remove(arraylistoff.get(i).getId().toString());
                    count--;
                    Log.e("aa","bellow count");
                    actionMode.setTitle(count+" selected");
                    Log.e("aa","count");
                }
            }

            @Override
            public boolean onCreateActionMode(android.view.ActionMode actionMode, Menu menu) {
                             MenuInflater menuInflater=getMenuInflater();
                       menuInflater.inflate(R.menu.covert_menu,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode actionMode, MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.delete_one_id){
                    for(Object item:selectNumbderOfData_List){
                        db.deletTheSpacificOrderTable(item.toString());
                    }
                    arraylistoff.clear();
                    viewallUserinListviewOFF();
                    doneList.notifyDataSetChanged();
                    actionMode.finish();
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode actionMode) {

            }
        });
       listremaining.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
           @Override
           public void onItemCheckedStateChanged(android.view.ActionMode actionMode, int position, long l, boolean checked) {
               if(checked)
               {
                   selectNumbderOfData_List.add(arraylist.get(position).getId().toString());
                   count++;
                   actionMode.setTitle(count+" selected");
                 }
               else {
                   selectNumbderOfData_List.remove(arraylist.get(position).getId().toString());
                   count--;
                   actionMode.setTitle(count+" selected");
               }
           }

           @Override
           public boolean onCreateActionMode(android.view.ActionMode actionMode, Menu menu) {
               MenuInflater menuInflater=getMenuInflater();
               menuInflater.inflate(R.menu.covert_menu,menu);
               return true;           }

           @Override
           public boolean onPrepareActionMode(android.view.ActionMode actionMode, Menu menu) {
               return false;
           }

           @Override
           public boolean onActionItemClicked(android.view.ActionMode actionMode, MenuItem menuItem) {
               if(menuItem.getItemId()==R.id.delete_one_id)
               {
                   for(Object item:selectNumbderOfData_List)
                   {
                       db.deletTheSpacificOrderTable(item.toString());
                       // list.remove(selectNumbderOfData_Listt.get(a));
                   }
                   arraylist.clear();
                   viewAllUserInListviewON();
                   customlistorder.notifyDataSetChanged();
                   actionMode.finish();
               }
               return true;
           }

           @Override
           public void onDestroyActionMode(android.view.ActionMode actionMode) {

           }
       });

        listremaining.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(View_Order.this,Details_ViewOrderOfMale.class);
                if(tabhost.getCurrentTab()==0){
                String id=arraylist.get(i).getId();
                intent.putExtra("id",id);
                    intent.putExtra("tab","0");

                    startActivity(intent);
            }}
        });

        listdone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(View_Order.this,Details_ViewOrderOfMale.class);
                String id=arraylistoff.get(i).getId();
                intent.putExtra("id",id);
                intent.putExtra("tab","1");

                startActivity(intent);
            }
        });
    }


public void viewallUserinListviewOFF(){
    Cursor cr=db.showOffOrderData();
    cr.moveToFirst();
    if(cr.getCount()>0){
        ViewOrderData_Off_Mode_setter_getter data;
        do{
            data=new ViewOrderData_Off_Mode_setter_getter();
            data.setId(cr.getString(0));
            data.setName(cr.getString(1));
            data.setQuantity(cr.getString(2));
            data.setPrice(cr.getString(3));
            data.setCurrenttime_date(cr.getString(4));
            data.setDeliverytime(cr.getString(5));
            data.setDeliveryDate(cr.getString(6));
            String shkid=cr.getString(7);
            if(shkid.equals("")){
                data.setShk_pantshir_id(cr.getString(8));
            }
            else {
                data.setShk_pantshir_id(shkid);
            }
            data.setImageID(cr.getString(9));
            arraylistoff.add(data);


        }while (cr.moveToNext());
        doneList =new CustomeListViewOfViewOrderDone(getApplicationContext(),arraylistoff);
        listdone.setAdapter(doneList);

    }
}
   public void viewAllUserInListviewON(){
       Cursor cr=db.showOnOrderData();

       cr.moveToFirst();
       if(cr.getCount()>0){
           View_Order_Data_setter_getter data;
           do{
               data=new View_Order_Data_setter_getter();
               data.setId(cr.getString(0));
               data.setName(cr.getString(1));
               data.setQuantity(cr.getString(2));
               data.setPrice(cr.getString(3));
               data.setCurrenttime_date(cr.getString(4));
               data.setDeliverytime(cr.getString(5));
               data.setDeliveryDate(cr.getString(6));
               String shkid=cr.getString(7);
               if(shkid.equals("")){
                   data.setShk_pantshir_id(cr.getString(8));
               }
               else {
                   data.setShk_pantshir_id(shkid);
               }
               data.setImageID(cr.getString(9));
               arraylist.add(data);


           }while (cr.moveToNext());
           customlistorder =new CustomeListViewOfViewOrder(getApplicationContext(),arraylist);
       listremaining.setAdapter(customlistorder);
       }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            arraylistoff.clear();
            viewallUserinListviewOFF();
            doneList.notifyDataSetChanged();
            listdone.setAdapter(doneList);

            arraylist.clear();
            viewAllUserInListviewON();
            customlistorder.notifyDataSetChanged();
            listremaining.setAdapter(customlistorder);
        }catch (Exception e){
            Log.e("ddd",e.toString());
        }
    }
}
