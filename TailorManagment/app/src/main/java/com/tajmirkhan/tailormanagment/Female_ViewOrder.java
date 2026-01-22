package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
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

import static com.tajmirkhan.tailormanagment.R.id.tabHost;

public class Female_ViewOrder extends AppCompatActivity {
    ListView listremaining,listdone;
    TabHost tabhost;
    Toolbar toolbar;
    List selectNumbderOfData_List=new ArrayList();
    int count=0;

    DatabaseFemale db;
    CustomeListViewOfFemale_ViewOrder customelistview;
    ArrayList<Female_ViewOrder_Data>  arrylist=new ArrayList<>();
    ArrayList<FemaleViewOrderData_OFF_Mode_GetterSetter> arraylistoff=new ArrayList<>();
    CustomeListviewOfFemaleOrderDone doneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.female__view_order_activity);
        db=new DatabaseFemale(this);

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
        viewAllUserInListview();
        viewallUserinListviewOFF();

        listremaining.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listremaining.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Female_ViewOrder.this,Details_ViewOrderOfMale.class);
                 intent.putExtra("id",arrylist.get(i).getId());
                 intent.putExtra("image","ON");
                 intent.putExtra("tab","2");
                startActivity(intent);
            }
        });

        listdone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Female_ViewOrder.this,Details_ViewOrderOfMale.class);
                intent.putExtra("id",arraylistoff.get(i).getId());
                intent.putExtra("image","ON");
                intent.putExtra("tab","3");
                startActivity(intent);
            }
        });

        listdone.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listdone.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean checked) {
                if(checked){
                    selectNumbderOfData_List.add(arraylistoff.get(i).getId().toString());
                    count++;
                    actionMode.setTitle(count+" selected");
                }else {
                    selectNumbderOfData_List.remove(arraylistoff.get(i).getId().toString());
                    count--;
                    actionMode.setTitle(count+" selected");
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater menuInflater=getMenuInflater();
                menuInflater.inflate(R.menu.covert_menu,menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.delete_one_id){
                    for(Object item:selectNumbderOfData_List){
                        db.deletTheSpacificOrderTable(item.toString());
                    } arraylistoff.clear();
                    viewallUserinListviewOFF();
                    doneList.notifyDataSetChanged();
                    actionMode.finish();
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
        listremaining.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode actionMode, int position, long l, boolean checked) {
                if(checked)
                {
                    selectNumbderOfData_List.add(arrylist.get(position).getId().toString());
                    count++;
                    actionMode.setTitle(count+" selected");
                }
                else {
                    selectNumbderOfData_List.remove(arrylist.get(position).getId().toString());
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
                    arrylist.clear();
                    viewAllUserInListview();
                    customelistview.notifyDataSetChanged();
                    actionMode.finish();
                }
                return true;           }

            @Override
            public void onDestroyActionMode(android.view.ActionMode actionMode) {

            }
        });
    }

    public void viewallUserinListviewOFF(){
        Cursor cr=db.showOffOrderDataFemale();
        cr.moveToFirst();
        if(cr.getCount()>0){
            FemaleViewOrderData_OFF_Mode_GetterSetter data;
            do{
                data=new FemaleViewOrderData_OFF_Mode_GetterSetter();
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
            doneList =new CustomeListviewOfFemaleOrderDone(getApplicationContext(),arraylistoff);
            listdone.setAdapter(doneList);

        }
    }

    public void viewAllUserInListview(){
        Cursor cr=db.showOnOrderDataFemale();

        cr.moveToFirst();
        if(cr.getCount()>0){
            Female_ViewOrder_Data data;
            do{
                data=new Female_ViewOrder_Data();
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
                arrylist.add(data);


            }while (cr.moveToNext());
            customelistview =new CustomeListViewOfFemale_ViewOrder(getApplicationContext(),arrylist);
            listremaining.setAdapter(customelistview);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
try {


        arrylist.clear();
        viewAllUserInListview();
        customelistview.notifyDataSetChanged();
        listremaining.setAdapter(customelistview);

        arraylistoff.clear();
        viewallUserinListviewOFF();
        doneList.notifyDataSetChanged();
       listdone.setAdapter(doneList);
    }catch (Exception e){

    }

    }


}
