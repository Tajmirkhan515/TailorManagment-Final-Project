package com.tajmirkhan.tailormanagment;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.id;
import static android.media.CamcorderProfile.get;
import static com.tajmirkhan.tailormanagment.R.id.tabHost;

public class ViewAllCustomer extends AppCompatActivity {
    ListView sk_listview,pantshirt_listview;
    DataBaseHelper db;
    TabHost tabhost;
    Toolbar toolbar;
    ArrayList<ShalwarKameezData> shalwarKameelist=new ArrayList<>();
    ArrayList<PantShirtData> pantShirtList=new ArrayList<>();
    ShalwarKameezData data;
    PantShirtData data1;

    CustomeListViewOfPantShirt pantshiradapter;
    MainActivity.CustomeListViewOfShalwarkameez shalwarkameezadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_customer_activity);
     Log.e("dd","indisoncreate mthod");

        sk_listview=(ListView)findViewById(R.id.listview_shalwarkameez_id);
        pantshirt_listview=(ListView)findViewById(R.id.listview_pantshirt_id);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        toolbar.setTitle("Design");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        db=new DataBaseHelper(this);

        tabhost=(TabHost)findViewById(tabHost);
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

        showdataMethodofShalwarKameez();
        showDataMethodOfPantshirt();

        registerForContextMenu(sk_listview);
        registerForContextMenu(pantshirt_listview);

      sk_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {  // it show the all measurement and information of male Customer

        /* Intent intent=new Intent(ViewAllCustomer.this,ShalwarK_ShowAllInformation.class);
              String id=shalwarKameelist.get(i).getId();
              intent.putExtra("id",id);
              startActivity(intent);
              */  //first i do the above code bust Mr.bilal is want to change

              final Dialog dialog=new Dialog(ViewAllCustomer.this,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
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
              texv.setText("\n\n\n    ID:                "+shalwarKameelist.get(i).getId()
                     +"\n\n   Name    :         "+shalwarKameelist.get(i).getName()
                      +"\n\n    Phone  :        "+shalwarKameelist.get(i).getPhone()
                      +"\n\n    Length  :       "+shalwarKameelist.get(i).getLength()
                      +"\n\n    Sleeve  :        "+shalwarKameelist.get(i).getSleeve()
                      +"\n\n    Shoulder:      "+shalwarKameelist.get(i).getShoulder()
                      +"\n\n    Chest   :        "+shalwarKameelist.get(i).getChest()
                      +"\n\n    Underarm:      "+shalwarKameelist.get(i).getUnderarm()
                      +"\n\n    Neck    :       "+shalwarKameelist.get(i).getNeck()
                      +"\n\n    Waist   :       "+shalwarKameelist.get(i).getWaist()
                      +"\n\n    Width   :       "+shalwarKameelist.get(i).getWidth()
                      +"\n\n    Abdomen :   "+shalwarKameelist.get(i).getAbdomen()
                      +"\n\n    Bicep   :        "+shalwarKameelist.get(i).getBicep()
                      +"\n\n    Wrist   :        "+shalwarKameelist.get(i).getWrist()
                      +"\n\n    ShalwarLength:  "+shalwarKameelist.get(i).getShalwarLength()
                      +"\n\n    ShalwarWasit :  "+shalwarKameelist.get(i).getShalwarWaist()
                      +"\n\n    Pancha  :            "+shalwarKameelist.get(i).getPancha()
                      +"\n\n    BinColor :         "+shalwarKameelist.get(i).getBinCollar()
                      +"\n\n    FrontPacket:       "+shalwarKameelist.get(i).getFrontPacket()
                      +"\n\n    ShalwarPacket:  "+shalwarKameelist.get(i).getShalwarPacket()
                      +"\n\n    SidePacket :       "+shalwarKameelist.get(i).getSidePacket()
                      +"\n\n    Description:       "+shalwarKameelist.get(i).getDescription());

            dialog.show();
          }
      });

        pantshirt_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Intent intent=new Intent(ViewAllCustomer.this,PantShirt_ShowAllInformation.class);
                String id=pantShirtList.get(i).getId();
                intent.putExtra("id",id);
                startActivity(intent); */
                //first i do the above code bust Mr.bilal is want to change
              final Dialog dialog=new Dialog(ViewAllCustomer.this,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
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
                texv.setText("\n\n\n   ID:              "+pantShirtList.get(i).getId()
                        +"\n\n  Name:           "+pantShirtList.get(i).getName()
                        +"\n\n  Phone:          "+pantShirtList.get(i).getPhone()
                        +"\n\n  Neck:            "+pantShirtList.get(i).getNeck()
                        +"\n\n  Chest:           "+pantShirtList.get(i).getChest()
                        +"\n\n  Shoulder:      "+pantShirtList.get(i).getShoulder()
                        +"\n\n  Sleeve:         "+pantShirtList.get(i).getSleeve()
                        +"\n\n  Bicep:           "+pantShirtList.get(i).getBicep()
                        +"\n\n  LengthOfWrist:  "+pantShirtList.get(i).getLengthOfwrist()
                        +"\n\n  Jocket Waist:    "+pantShirtList.get(i).getJocket_waist()
                        +"\n\n  Hip:                "+pantShirtList.get(i).getHip()
                        +"\n\n  Length:             "+pantShirtList.get(i).getLength()
                        +"\n\n  Waist:              "+pantShirtList.get(i).getWaist()
                        +"\n\n  HalfShoulder:     "+pantShirtList.get(i).getHalfshoulder()
                        +"\n\n  BackFullLength:  "+pantShirtList.get(i).getBackfulllength()
                        +"\n\n  HalfBackLength: "+pantShirtList.get(i).getHalfbacklength()
                        +"\n\n  PantWaist:          "+pantShirtList.get(i).getPantwaist()
                        +"\n\n  PantLength:         "+pantShirtList.get(i).getPantlength()
                        +"\n\n  InsideLength:      "+pantShirtList.get(i).getInsidelength()
                        +"\n\n  Crotch:            "+pantShirtList.get(i).getCrotch()
                        +"\n\n  PantThigh:      "+pantShirtList.get(i).getPantthigh()
                        +"\n\n  PantKnee:       "+pantShirtList.get(i).getPantknee()
                        +"\n\n  BenCollar:      "+pantShirtList.get(i).getBenCollar()
                        +"\n\n  FrontPacket:    "+pantShirtList.get(i).getFrontPcket()
                        +"\n\n  Description:    "+pantShirtList.get(i).getDescription()
                );
                dialog.show();
          }
        });


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("select The action");
        menu.add(0,v.getId(),0,"Delete");
        menu.add(0,v.getId(),0,"Update");
        menu.add(0,v.getId(),0,"Exit");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Delete")
        {Intent intent=new Intent();

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int listPosition = info.position;
           if(0==tabhost.getCurrentTab()){

               Toast.makeText(this, "positoin of "+listPosition, Toast.LENGTH_SHORT).show();
               String id=shalwarKameelist.get(listPosition).getId();
                 boolean str=db.deletRecord(0,id);  // the 0 is used for select the ShalwarTable name .its my logic not default
                     if(true==str) {
                         shalwarKameelist.remove(listPosition);
                         shalwarkameezadapter.notifyDataSetChanged();
                          Toast.makeText(this, "value is deleted " + id, Toast.LENGTH_SHORT).show();
                     }

           }else if(1==tabhost.getCurrentTab()){
              String id=pantShirtList.get(listPosition).getId();
               boolean str=db.deletRecord(1,id);   // the 1 is used for select the ShalwarTable name .its my logic not default
               if(true==str) {
                  pantShirtList.remove(listPosition);
                   pantshiradapter.notifyDataSetChanged();
                   Toast.makeText(this, "value is deleted " + id, Toast.LENGTH_SHORT).show();
               }
            Toast.makeText(this, "this is pantshirt  "+listPosition+"    id "+id, Toast.LENGTH_SHORT).show();
                      }
        }
        else if(item.getTitle()=="Update")
        {
            Intent intent=new Intent(ViewAllCustomer.this,InsertNewCustomerMale.class);

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int listPosition = info.position;

            if(0==tabhost.getCurrentTab()){
              intent.putExtra("flage",1); // if flage is one you select the shalwar table
                String id=shalwarKameelist.get(listPosition).getId();
                intent.putExtra("id",id);

                startActivity(intent);
                shalwarKameelist.clear();
                showdataMethodofShalwarKameez();
                shalwarkameezadapter.notifyDataSetChanged();
            }
            else if(1==tabhost.getCurrentTab()){
           String id=pantShirtList.get(listPosition).getId();
                intent.putExtra("flage",2);
                intent.putExtra("id",id);
                startActivity(intent);
            }

        }else if(item.getTitle()=="Exit"){
          System.exit(0);
        }

        return true;
    }



    public void showDataMethodOfPantshirt()
    { Cursor cr=db.datashowOfPantShirt();
        cr.moveToFirst();
        if(cr.getCount()>0){
            PantShirtData data;

            do{ data=new PantShirtData();
                data.setId(cr.getString(0));
                data.setName(cr.getString(1));
                data.setPhone(cr.getString(2));
                data.setNeck(cr.getString(3));
                data.setChest(cr.getString(4));
                data.setShoulder(cr.getString(5));
                data.setSleeve(cr.getString(6));
                data.setBicep(cr.getString(7));
                data.setLengthOfwrist(cr.getString(8));
                data.setJocket_waist(cr.getString(9));
                data.setHip(cr.getString(10));
                data.setLength(cr.getString(11));
                data.setWaist(cr.getString(12));
                data.setHalfshoulder(cr.getString(13));
                data.setBackfulllength(cr.getString(14));
                data.setHalfbacklength(cr.getString(15));

                data.setPantwaist(cr.getString(16));
                data.setPantlength(cr.getString(17));
                data.setInsidelength(cr.getString(18));
                data.setCrotch(cr.getString(19));
                data.setPantthigh(cr.getString(20));
                data.setPantknee(cr.getString(21));
                data.setBenCollar(cr.getString(22));
                data.setFrontPcket(cr.getString(23));
                data.setDescription(cr.getString(24));
                pantShirtList.add(data);
            }while (cr.moveToNext());
             pantshiradapter =new CustomeListViewOfPantShirt(getApplicationContext(),pantShirtList);

            pantshirt_listview.setAdapter(pantshiradapter);
        }else {
            Toast.makeText(this, "there is no data enter", Toast.LENGTH_SHORT).show();
        }
    }





    public void showdataMethodofShalwarKameez()
    {
        Cursor cr=db.dataShowOfShalwarKameez();

        cr.moveToFirst();
        if(cr.getCount()>0)
            do {
                data=new ShalwarKameezData();
                data.setId(cr.getString(0));
                data.setName(cr.getString(1));
                data.setPhone(cr.getString(2));
                data.setLength(cr.getString(3));
                data.setSleeve(cr.getString(4));
                data.setShoulder(cr.getString(5));
                data.setChest(cr.getString(6));
                data.setUnderarm(cr.getString(7));
                data.setNeck(cr.getString(8));
                data.setWaist(cr.getString(9));
                data.setWidth(cr.getString(10));
                data.setAbdomen(cr.getString(11));
                data.setBicep(cr.getString(12));
                data.setWrist(cr.getString(13));
                data.setShalwarLength(cr.getString(14));
                data.setShalwarWaist(cr.getString(15));
                data.setPancha(cr.getString(16));
                data.setBinCollar(cr.getString(17));
                data.setFrontPacket(cr.getString(18));
                data.setShalwarPacket(cr.getString(19));
                data.setSidePacket(cr.getString(20));
                data.setDescription(cr.getString(21));
                shalwarKameelist.add(data);

            } while (cr.moveToNext());
         shalwarkameezadapter=new MainActivity.CustomeListViewOfShalwarkameez(getApplicationContext(),shalwarKameelist);
        sk_listview.setAdapter(shalwarkameezadapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        pantShirtList.clear();
        showDataMethodOfPantshirt();
       // pantshiradapter.notifyDataSetChanged();

        shalwarKameelist.clear();
        showdataMethodofShalwarKameez();
        shalwarkameezadapter.notifyDataSetChanged();

    }
}
