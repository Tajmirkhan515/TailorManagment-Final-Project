package com.tajmirkhan.tailormanagment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.R.id.list;
import static com.tajmirkhan.tailormanagment.R.id.tabHost;

public class FemaleNewOrder extends AppCompatActivity {

    ListView sk_listview,pantshirt_listview;
    DatabaseFemale db;
    TabHost tabhost;

    ArrayList<Female_ShalwarKameez_Data> shalwarKameelist=new ArrayList<>();
    ArrayList<FemalePantshirt_Data> pantshirtlist=new ArrayList<>();

    CustomListViewOfShalwarKameezOfFemale femaleshalwarkameezadapter;
    CustomListViewOfPantshirtOfFemale femalePantshirtAdapeter;


    CustomListViewOfFemaleDesign customlistview;          //design class objects
    ArrayList<Female_Design_Data> list=new ArrayList<>();
    GridView gridview;

    Toolbar toolbar;
    TextView current_time_date;
    TextView texv_user_id,texv_user_name;
    String phone_number;
    String message_send;
    Button select_user_btn;

    RadioGroup redg_select_design;
    Button design_select_btn;
    ImageView image_select_design;
    String image_id;
    String select_design="-1";  // if the design is 0 its menas its shalware kameez and if  1 its means pantshirt

    EditText quantity_edt,price_edt;
    TextView total_cost_txv;


    EditText deliver_time_edt;
    Button deliver_time_btn;

    EditText deliver_date_edt;
    Button deliver_date_btn;

    Button save_all_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.female_new_order_activity);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        setSupportActionBar(toolbar);
        db=new DatabaseFemale(this);
        getSupportActionBar().setTitle("New Order");// it use for OptionMenu without this its not suport
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        gridview=(GridView)findViewById(R.id.gridview_id);  //design button connectivity
        db=new DatabaseFemale(this);

        Calendar c = Calendar.getInstance();  //its set the current time and date
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:aa  dd-MM-yyyy", Locale.getDefault());
        final String strDate = sdf.format(c.getTime());
        current_time_date=(TextView)findViewById(R.id.current_time_txv_id);
        current_time_date.setText(strDate);

        texv_user_id=(TextView)findViewById(R.id.id_id);          //this is getting the user information button and Editext
        texv_user_name=(TextView)findViewById(R.id.name_id);
        select_user_btn=(Button)findViewById(R.id.select_user_btn_id);

        redg_select_design=(RadioGroup)findViewById(R.id.pant_shalwar_rdg_id);  // there is select the design information
        design_select_btn=(Button)findViewById(R.id.design_select_btn_id);
        image_select_design=(ImageView)findViewById(R.id.design_image_id);


        quantity_edt=(EditText)findViewById(R.id.quantity_edt_id);  //prise and quntity of the sout
        price_edt=(EditText)findViewById(R.id.prise_edt_id);
        total_cost_txv=(TextView)findViewById(R.id.total_cost_female_txv);

        deliver_time_edt=(EditText) findViewById(R.id.time_edt_id);  //this is the delivery date Costig and
        deliver_time_btn=(Button)findViewById(R.id.time_btn_id);

        deliver_date_edt=(EditText)findViewById(R.id.date_edt_id);  // this is the deivery date costing
        deliver_date_btn=(Button)findViewById(R.id.date_btn_id);

        save_all_btn=(Button)findViewById(R.id.save_all_record_btn_id);

         quantity_edt.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if(price_edt.getText().toString().equals("")){
                     price_edt.setError("pleas price must be enter");
                 }
             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void afterTextChanged(Editable editable) {
                 float a,b,div;

                 if(price_edt.getText().toString().equals("") || quantity_edt.getText().toString().equals("")){
                     Toast.makeText(FemaleNewOrder.this, "pleas fill ", Toast.LENGTH_SHORT).show();
                 }else {
                     a = Float.parseFloat(price_edt.getText().toString());
                     b = Float.parseFloat(quantity_edt.getText().toString());


                     total_cost_txv.setText("  Total Cost: "+String.valueOf(a * b));
                 }

             }
         });


        save_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{
                String name=texv_user_name.getText().toString();
                String quantity=quantity_edt.getText().toString().trim();
                String price=price_edt.getText().toString().trim();
                String current_time=strDate;
                String delivery_time=deliver_time_edt.getText().toString().trim();
                String delivery_date=deliver_date_edt.getText().toString().trim();
                String shk_userid=null;
                String pant_userid=null;
                message_send="ON";
                String total_cost_str=total_cost_txv.getText().toString();
                try {                                                        //try catch i use for the null value by defalut the select_design value is null
                    if (select_design.equals("0")) {                           /*if the radio button is select of the shalwarkameez its means this is the shalwarkameez table user
                                                                            this time i assign value to the shalwarkameez forighn key value and the pantshir  is null
                                                                      */
                        shk_userid = texv_user_id.getText().toString().trim();
                        pant_userid = null;
                    } else {
                        pant_userid = texv_user_id.getText().toString().trim();
                        shk_userid = null;
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(FemaleNewOrder.this, "errror occure", Toast.LENGTH_SHORT).show();
                }
                if(quantity.equals("") || current_time.equals("") || delivery_time.equals("") || delivery_date.equals("") || image_id.equals("") || name.equals(""))
                {
                    Toast.makeText(FemaleNewOrder.this, "Pleas fill the all View ", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean check = db.insertInToFemaleOrdertable(name, quantity, price, current_time, delivery_time, delivery_date, shk_userid, pant_userid, image_id,phone_number,message_send,total_cost_str);
                    if(check==true)
                        {
                        Toast.makeText(FemaleNewOrder.this, "value is inserted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    else
                       {
                        Toast.makeText(FemaleNewOrder.this, "value is not inserted", Toast.LENGTH_SHORT).show();
                       }
                  }}
               catch (Exception e){

               }

                }
        });

        redg_select_design.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.shalwar_rdbtn_id:
                        select_design = "0";
                        break;
                    case R.id.shirt_rdbtn_id:
                        select_design = "1";
                        break;
                }}
        });

        select_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(FemaleNewOrder.this, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
                dialog.setContentView(R.layout.view_all_customer_activity);
                Toolbar toolbar;

                toolbar=(Toolbar)dialog.findViewById(R.id.toolbar_id);
                toolbar.setNavigationIcon(R.drawable.ic_action_cancel);
                toolbar.setTitle("Select User");
                toolbar.setNavigationOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                sk_listview=(ListView)dialog.findViewById(R.id.listview_shalwarkameez_id); //first dialog name and id texview
                pantshirt_listview=(ListView)dialog.findViewById(R.id.listview_pantshirt_id);

                pantshirtlist.clear();  //first clean the list then set the all data
                shalwarKameelist.clear();//first clean the list then set the all data in a list
                showDataMethodOfShalwarKameez();
                showDataMethodOfPantshirt();


                if (select_design=="-1"){  //if the select_design is null its means the radio button is unchecked
                    Toast.makeText(FemaleNewOrder.this, "pleas select the design", Toast.LENGTH_SHORT).show();
                }else if(select_design=="0"){

                    tabhost=(TabHost)dialog.findViewById(tabHost);
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

                    tabhost.setCurrentTab(0);
                    tabhost.getTabWidget().getChildTabViewAt(1).setEnabled(false);
                    dialog.show();

                } else if(select_design=="1"){
                    tabhost=(TabHost)dialog.findViewById(R.id.tabHost);
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

                    tabhost.setCurrentTab(1);
                    tabhost.getTabWidget().getChildTabViewAt(0).setEnabled(false);
                    dialog.show();

                }


                sk_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String id=shalwarKameelist.get(i).getId();
                        String name=shalwarKameelist.get(i).getName();
                        phone_number=shalwarKameelist.get(i).getPhone();
                        texv_user_id.setText(""+id);
                        texv_user_name.setText(""+name);

                        dialog.cancel();
                    }
                });


                pantshirt_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String id=pantshirtlist.get(i).getId();
                        String name=pantshirtlist.get(i).getName();
                        phone_number=shalwarKameelist.get(i).getPhone();
                        texv_user_id.setText(id);
                        texv_user_name.setText(name);

                        dialog.cancel();
                    }
                });

            }
        });

        design_select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (select_design=="-1"){  //if the select_design is null its means the radio button is unchecked
                    Toast.makeText(FemaleNewOrder.this, "pleas select the design", Toast.LENGTH_SHORT).show();
                }
                else {
                    final Dialog dialog = new Dialog(FemaleNewOrder.this, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
                    dialog.setContentView(R.layout.update_design_activity);

                    Toolbar toolbar;


                    gridview = (GridView) dialog.findViewById(R.id.gridview_id);

                    toolbar=(Toolbar)dialog.findViewById(R.id.toolbar_id);
                    toolbar.setNavigationIcon(R.drawable.ic_action_cancel);
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setTitle("Select Design");// it use for OptionMenu without this its not suport
                    toolbar.setNavigationOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });

                    list.clear();
                   getImageDataFromDB();
                    customlistview=new CustomListViewOfFemaleDesign(getApplicationContext(),list);
                    gridview.setAdapter(customlistview);

                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            image_id=list.get(i).getId().toString();  //its get id of the image

                            byte[] arry = list.get(i).getImage();
                            try {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(arry, 0, arry.length);
                                image_select_design.setImageBitmap(bitmap);
                            } catch (Exception ex) {

                            }
                            dialog.cancel();
                        }
                    });

                    dialog.show();
                }
            }
        });

        deliver_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(FemaleNewOrder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String timeFormat;

                        if (hourOfDay == 0) {
                            hourOfDay += 12;
                            timeFormat = "AM";
                        } else if (hourOfDay == 12) {
                            timeFormat = "PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            timeFormat = "PM";
                        } else {
                            timeFormat = "AM";
                        }
                        String m;
                        if(minute<10){
                            m="0"+minute;
                        }else {
                            m=""+minute;
                        }
                        String h;
                        if(hourOfDay<10){
                            h="0"+hourOfDay;
                        }else {
                            h=""+hourOfDay;
                        }

                        deliver_time_edt.setText(h+":"+m+":"+timeFormat);
                    }
                }, hour, minute, false);

                timePickerDialog.show();

            }
        });

        deliver_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();

                int day   = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                int month = mcurrentDate.get(Calendar.MONTH);
                int year  = mcurrentDate.get(Calendar.YEAR);

                DatePickerDialog dpd;

                dpd = new DatePickerDialog(FemaleNewOrder.this, 0,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {

                                String d,m;
                                if(day<10){
                                    d="0"+day;
                                }else {
                                    d=""+day;
                                }
                                if(month<10){
                                    m=""+month+1;
                                }else{
                                    m=""+(month+1); // its start month from the zero
                                     }
                                deliver_date_edt.setText(d+"-"+m+"-"+year);
                            }

                        }, day, month, year);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());//this method is start from the currnt date,bydefault its show the 1900
                dpd.show();
            }
        });
    }

    public void getImageDataFromDB()
    {
        Cursor cr=db.imageDataShow();

        if(cr.getCount()>0) {
            cr.moveToFirst();
            Female_Design_Data obj;
            do {
                obj = new Female_Design_Data();

                obj.setId(cr.getString(0));
                obj.setName(cr.getString(1));
                obj.setImage(cr.getBlob(2));
                obj.setFlage(cr.getString(3));
                list.add(obj);
            } while (cr.moveToNext());
        }
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
}
