package com.tajmirkhan.tailormanagment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.tajmirkhan.tailormanagment.R.id.bottom;
import static com.tajmirkhan.tailormanagment.R.id.tabHost;
import static com.tajmirkhan.tailormanagment.R.id.view_offset_helper;

public class Female_ViewAllCustomer extends AppCompatActivity {
    TabHost tabhost;
    Toolbar toolbar;
    ListView sk_listview,pantshirt_listview;
    ArrayList<Female_ShalwarKameez_Data> shalwarKameelist=new ArrayList<>();
    ArrayList<FemalePantshirt_Data> pantshirtlist=new ArrayList<>();

    CustomListViewOfShalwarKameezOfFemale femaleshalwarkameezadapter;

    CustomListViewOfPantshirtOfFemale femalePantshirtAdapeter;

    DatabaseFemale db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.female__view_all_customer_activity);

        sk_listview=(ListView)findViewById(R.id.listview_shalwarkameez_id);
        pantshirt_listview=(ListView)findViewById(R.id.listview_pantshirt_id);

        db=new DatabaseFemale(this);
        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        toolbar.setTitle("All Customer");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tabhost=(TabHost)findViewById(R.id.tabHost);
        tabhost.setup();



        //Tab 1
        TabHost.TabSpec spec = tabhost.newTabSpec("Tab One");
        spec.setContent(R.id.ShalwareKameez_tab);
        spec.setIndicator("shalwar Kameez");
        tabhost.addTab(spec);

        // tab 2
        spec = tabhost.newTabSpec("wel");
        spec.setContent(R.id.pantshirt_tab);
        spec.setIndicator("pantshirt");
        tabhost.addTab(spec);

        showDataMethodOfShalwarKameez();
        showDataMethodOfPantshirt();

        registerForContextMenu(sk_listview);
        registerForContextMenu(pantshirt_listview);


        sk_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialog=new Dialog(Female_ViewAllCustomer.this,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
                dialog.setContentView(R.layout.show_item_of_shalwarkameezlist);
                Toolbar  toolbar=(Toolbar)dialog.findViewById(R.id.toolbar_id);
                toolbar.setNavigationIcon(R.drawable.ic_action_cancel);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Select Design");// it use for OptionMenu without this its not suport
                toolbar.setNavigationOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                TextView texv=(TextView)dialog.findViewById(R.id.show_all_item_txv_id);

                texv.setText("\n\n     ID:                   "+shalwarKameelist.get(i).getId()+
                         "\n\n     Name:                 "+shalwarKameelist.get(i).getId()
                        +"\n\n     Phone:                "+shalwarKameelist.get(i).getPhone()
                        +"\n\n     KameezLength:     "+shalwarKameelist.get(i).getKameezLength()
                        +"\n\n     Neck:                 "+shalwarKameelist.get(i).getNeck()
                        +"\n\n     Shoulder:           "+shalwarKameelist.get(i).getShoulder()
                        +"\n\n     Bust:                "+shalwarKameelist.get(i).getBust()
                        +"\n\n     Kameez waist:   "+shalwarKameelist.get(i).getKameezWaist()
                        +"\n\n     Hip:                "+shalwarKameelist.get(i).getHip()
                        +"\n\n     Border Measurement:"+shalwarKameelist.get(i).getBorderMeasurement()
                        +"\n\n     Sleeve Length:     "+shalwarKameelist.get(i).getSleeveLength()
                        +"\n\n     UnderArm:           "+shalwarKameelist.get(i).getUnderAram()
                        +"\n\n     Bicep:                "+shalwarKameelist.get(i).getBicep()
                        +"\n\n     Wrist:                "+shalwarKameelist.get(i).getWrist()
                        +"\n\n     ShalwarWaist:    "+shalwarKameelist.get(i).getShalwarWaist()
                        +"\n\n     ShalwarLength:   "+shalwarKameelist.get(i).getShalwarLength()
                        +"\n\n     Bottom:             "+shalwarKameelist.get(i).getBottom()
                        +"\n\n     Description:       "+shalwarKameelist.get(i).getShalwarDescription()
                +"\n\n\n\n\n");

                dialog.show();

            }
        });

        pantshirt_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final Dialog dialog=new Dialog(Female_ViewAllCustomer.this,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
                dialog.setContentView(R.layout.show_item_of_shalwarkameezlist);
                Toolbar  toolbar=(Toolbar)dialog.findViewById(R.id.toolbar_id);
                toolbar.setNavigationIcon(R.drawable.ic_action_cancel);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Select Design");// it use for OptionMenu without this its not suport
                toolbar.setNavigationOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                TextView texv=(TextView)dialog.findViewById(R.id.show_all_item_txv_id);

                  texv.setText("\n\n        ID:             "+pantshirtlist.get(i).getId()
                          +"\n\n        Name:        "+pantshirtlist.get(i).getName()
                          +"\n\n        Phone:       "+pantshirtlist.get(i).getPhone()
                          +"\n\n        Bust:          "+pantshirtlist.get(i).getBust()
                          +"\n\n        Shirt Waist: "+pantshirtlist.get(i).getWaist()
                          +"\n\n        Hip:              "+pantshirtlist.get(i).getHip()
                          +"\n\n        Shoulder:    "+pantshirtlist.get(i).getShoulder()
                          +"\n\n        ShirtLength:"+pantshirtlist.get(i).getShirtLength()
                          +"\n\n        Sleeve:          "+pantshirtlist.get(i).getSleeve()
                          +"\n\n        Bicep:           "+pantshirtlist.get(i).getBicep()
                          +"\n\n        Wrist:           "+pantshirtlist.get(i).getWrist()
                          +"\n\n        PantWaist:  "+pantshirtlist.get(i).getPantWaist()
                          +"\n\n        ThighCrotch: "+pantshirtlist.get(i).getThighCrotch()
                          +"\n\n        Knee:            "+pantshirtlist.get(i).getKnee()
                          +"\n\n       Calf:              "+pantshirtlist.get(i).getCalf()
                          +"\n\n        Ankle:           "+pantshirtlist.get(i).getAnkle()
                          +"\n\n        Inseam:         "+pantshirtlist.get(i).getInseam()
                          +"\n\n        waist:           "+pantshirtlist.get(i).getCroatchWaist()
                          +"\n\n        PantLength: "+pantshirtlist.get(i).getPantLength()
                          +"\n\n        Description: "+pantshirtlist.get(i).getPantDescription()
                  +"\n\n\n\n\n\n");
            dialog.show();
            }

        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select the action");
        MenuInflater inflater=getMenuInflater();
       inflater.inflate(R.menu.update_delete_exit_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;


        switch (item.getItemId()){
       case R.id.update_id:
           Intent intent=new Intent(Female_ViewAllCustomer.this,InsertNewCustomerFemale.class);
            if(0==tabhost.getCurrentTab()){
                intent.putExtra("flage",1);
                String id=shalwarKameelist.get(listPosition).getId();
                intent.putExtra("id",id);
                startActivity(intent);
            }else if(1==tabhost.getCurrentTab()){
                  intent.putExtra("flage",2);
                String id=pantshirtlist.get(listPosition).getId();
                intent.putExtra("id",id);
                startActivity(intent);
            }

           return true;
            case  R.id.delet_id:
           if(0==tabhost.getCurrentTab()){
               String id=shalwarKameelist.get(listPosition).getId();
               boolean check=db.deletRecord(0,id);
               if(check==true){
                   shalwarKameelist.remove(listPosition);
                   femaleshalwarkameezadapter.notifyDataSetChanged();
                   Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
               }
           }else if(1==tabhost.getCurrentTab()){
               String id=pantshirtlist.get(listPosition).getId();
               boolean check=db.deletRecord(1,id);
               if(check==true){
                   pantshirtlist.remove(listPosition);
                   femalePantshirtAdapeter.notifyDataSetChanged();
                   Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
               }
           }
           return true;

       case R.id.exit_id:
                 finish();
           return true;
          }
        return super.onContextItemSelected(item);
    }

    public void showDataMethodOfShalwarKameez() {
        Cursor cr = db.femaleDataShowOfShalwarKameez();

        cr.moveToFirst();
        Female_ShalwarKameez_Data data;
        if (cr.getCount() > 0) {
            do{
                data=new Female_ShalwarKameez_Data();
               data.setId(cr.getString(0));
                data.setName(cr.getString(1));
                data.setPhone(cr.getString(2));
                data.setKameezLength(cr.getString(3));
                data.setNeck(cr.getString(4));
                data.setShoulder(cr.getString(5));
                data.setBust(cr.getString(6));
                data.setKameezWaist(cr.getString(7));
                data.setHip(cr.getString(8));
                data.setBorderMeasurement(cr.getString(9));
                data.setSleeveLength(cr.getString(10));
                data.setUnderAram(cr.getString(11));
                data.setBicep(cr.getString(12));
                data.setWrist(cr.getString(13));
                data.setShalwarWaist(cr.getString(14));
                data.setShalwarLength(cr.getString(15));
                data.setBottom(cr.getString(16));
                data.setShalwarDescription(cr.getString(17));

             shalwarKameelist.add(data);
            } while (cr.moveToNext()) ;
            femaleshalwarkameezadapter = new CustomListViewOfShalwarKameezOfFemale(getApplicationContext(),shalwarKameelist);
            sk_listview.setAdapter(femaleshalwarkameezadapter);
        } else {
            Toast.makeText(this, "there is no data entered", Toast.LENGTH_SHORT).show();
        }
    }

      public void showDataMethodOfPantshirt(){
          Cursor cr=db.femaleDataShowOfPantshrit();
          cr.moveToFirst();

          FemalePantshirt_Data data;

          if(cr.getCount()>0){
              do{

                  data=new FemalePantshirt_Data();
                  data.setId(cr.getString(0));
                  data.setName(cr.getString(1));
                  data.setPhone(cr.getString(2));
                  data.setBust(cr.getString(3));
                  data.setWaist(cr.getString(4));
                  data.setHip(cr.getString(5));
                  data.setShoulder(cr.getString(6));
                  data.setShirtLength(cr.getString(7));
                  data.setSleeve(cr.getString(8));
                  data.setBicep(cr.getString(9));
                  data.setWrist(cr.getString(10));
                  data.setWaist(cr.getString(11));
                  data.setThighCrotch(cr.getString(12));
                  data.setKnee(cr.getString(13));
                  data.setCalf(cr.getString(14));
                  data.setAnkle(cr.getString(15));
                  data.setInseam(cr.getString(16));
                  data.setCroatchWaist(cr.getString(17));
                  data.setPantLength(cr.getString(18));
                  data.setPantDescription(cr.getString(19));

                  pantshirtlist.add(data);


              }while (cr.moveToNext());

              femalePantshirtAdapeter=new CustomListViewOfPantshirtOfFemale(getApplicationContext(),pantshirtlist);
              pantshirt_listview.setAdapter(femalePantshirtAdapeter);

          }
          else {

              Toast.makeText(this, "The pantshirt list is empty", Toast.LENGTH_SHORT).show();
          }
      }

    @Override
    protected void onResume() {
        super.onResume();
        shalwarKameelist.clear();

        showDataMethodOfShalwarKameez();
        //femaleshalwarkameezadapter.notifyDataSetChanged();
        pantshirtlist.clear();
        showDataMethodOfPantshirt();

        //femalePantshirtAdapeter.notifyDataSetChanged();
    }
}
