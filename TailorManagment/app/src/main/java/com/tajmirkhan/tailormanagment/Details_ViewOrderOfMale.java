package com.tajmirkhan.tailormanagment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.R.id.list;
import static android.R.id.tabhost;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Details_ViewOrderOfMale extends AppCompatActivity {
Toolbar toolbar;
    TextView txv_id,txv_name,txv_phone,txv_order,txv_percost,txv_totalcost,txv_current_time,txv_current_date,txv_delivery_time,txv_delivery_date;
    ImageButton change_time_btn,shaking_btn,message_send_btn;

    String delivery_time_str,delivery_date_str;
    String id;
    String tab;

    CollapsingToolbarLayout colopsing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details__view_order_of_male_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        colopsing=(CollapsingToolbarLayout)findViewById(R.id.collapsingtoolbar_id);

        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txv_id=(TextView)findViewById(R.id.id_id);
       txv_name =(TextView)findViewById(R.id.name_id);
       txv_phone =(TextView)findViewById(R.id.phone_id);
       txv_order =(TextView)findViewById(R.id.order_id);
       txv_percost=(TextView)findViewById(R.id.per_sout_id);
      txv_totalcost =(TextView)findViewById(R.id.total_codt_id);
      txv_current_time =(TextView)findViewById(R.id.time_id);
      txv_current_date =(TextView)findViewById(R.id.date_id);
       txv_delivery_time=(TextView)findViewById(R.id.delivery_time_details_id);
       txv_delivery_date=(TextView)findViewById(R.id.delivery_date_details_id);

        change_time_btn=(ImageButton)findViewById(R.id.change_time_id);
        shaking_btn=(ImageButton)findViewById(R.id.shaking_id);
        message_send_btn=(ImageButton)findViewById(R.id.send_message_id);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
         tab=intent.getStringExtra("tab");
        Log.e("we","above on");
        if("ON".equals(intent.getStringExtra("image"))){
            ImageView imag=(ImageView)findViewById(R.id.customer_icon_id);
            imag.setImageResource(R.drawable.customer_icon_female_id);
        }

        //the below code set the image on a button
        if(tab.equals("0")||tab.equals("2")){
             shaking_btn.setImageResource(R.drawable.delivery_byhand_icon);
        }
      else if(tab.equals("1")||tab.equals("3")){  //if the tab is one its means ,this is the Done list .inside the done list i set delete icon on imageButton.
           shaking_btn.setImageResource(R.drawable.delete_details_icon);
       }

        if (tab.equals("0")){
                 showDate(id);
        }else if(tab.equals("1")) {
        showDate(id);
        } else if(tab.equals("2")){
         showDateOfFemale(id);
        }else if(tab.equals("3")){
         showDateOfFemale(id);

 }

        message_send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tab.equals("0")|| tab.equals("1")) {
                    messageSend(id);
                }else if(tab.equals("2") || tab.equals("3")){
                    messageSendFemale(id);
                }
            finish();
            }
        });

        change_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTimeandDate(id,tab);
            }
        });

        shaking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shaking(id,tab);
                finish();
            }
        });

    }

    public void messageSend(String id){


        DataBaseHelper db=new DataBaseHelper(this);

        int per= ContextCompat.checkSelfPermission(Details_ViewOrderOfMale.this, Manifest.permission.SEND_SMS);
        boolean b= db.replaceON_OFF(id);

        if(per== PackageManager.PERMISSION_GRANTED)
        {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            v.vibrate(500);

            SmsManager smsmanager=SmsManager.getDefault();
          Cursor ph=db.selectPhoneNumberOfMale(id);
            if(ph.getCount()>0){
                ph.moveToFirst();

                smsmanager.sendTextMessage(ph.getString(0),null,"MaleTailor",null,null);

                Log.e("db","permission successfull");
                Toast.makeText(Details_ViewOrderOfMale.this, "msg  sent", Toast.LENGTH_SHORT).show();


            }

        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
            if (per == PackageManager.PERMISSION_GRANTED) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                v.vibrate(500);

                SmsManager smsmanager = SmsManager.getDefault();
                Cursor ph = db.selectPhoneNumberOfMale(id);
                if (ph.getCount() > 0) {
                    ph.moveToFirst();

                    smsmanager.sendTextMessage(ph.getString(0), null, "MaleTailor", null, null);

                    Log.e("db", "permission successfull");
                    Toast.makeText(Details_ViewOrderOfMale.this, "message sent", Toast.LENGTH_SHORT).show();
                }
             }
           }

    }

    public void messageSendFemale(String id){
        DatabaseFemale db=new DatabaseFemale(this);

        int per= ContextCompat.checkSelfPermission(Details_ViewOrderOfMale.this, Manifest.permission.SEND_SMS);
        boolean b= db.replaceON_OFF(id);

        if(per== PackageManager.PERMISSION_GRANTED)
        {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            v.vibrate(500);

            SmsManager smsmanager=SmsManager.getDefault();
            Cursor ph=db.selectPhoneNumberOfFemale(id);
            if(ph.getCount()>0){
                ph.moveToFirst();

                smsmanager.sendTextMessage(ph.getString(0),null,"MaleTailor",null,null);

                Log.e("db","permission successfull");
                Toast.makeText(Details_ViewOrderOfMale.this, "msg  sent", Toast.LENGTH_SHORT).show();


            }

        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
            if (per == PackageManager.PERMISSION_GRANTED) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                v.vibrate(500);

                SmsManager smsmanager = SmsManager.getDefault();
                Cursor ph = db.selectPhoneNumberOfFemale(id);
                if (ph.getCount() > 0) {
                    ph.moveToFirst();

                    smsmanager.sendTextMessage(ph.getString(0), null, "MaleTailor", null, null);

                    Log.e("db", "permission successfull");
                    Toast.makeText(Details_ViewOrderOfMale.this, "message sent", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void shaking(String id,String tab){
       if(tab.equals("0")){
        DataBaseHelper db=new DataBaseHelper(this);
        boolean check=db.replaceON_OFF(id);

           if(check==true){
            Toast.makeText(this, "you deliver the cloths by hand No need to send the message", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }}

          else if(tab.equals("1")){
           DataBaseHelper db=new DataBaseHelper(this);
           boolean check=db.deletOneSpacificOrder(id);
            if(check==true){
                Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show();
            }

       }

        else if(tab.equals("2")){
           DatabaseFemale db=new DatabaseFemale(this);
           boolean check=db.replaceON_OFF(id);
           if(check==true){
               Toast.makeText(this, "you deliver the cloths by hand No need to send the message", Toast.LENGTH_LONG).show();
           }
       }else if(tab.equals("3")){
           DatabaseFemale db=new DatabaseFemale(this);
           boolean check=db.deletOneSpacificOrderOfFemale(id);
           if(check==true){
               Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show();
           }
       }
    }

    public void selectTimeandDate(final String id, final String tab){
        Calendar mcurrentDate = Calendar.getInstance();

        int day   = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        int month = mcurrentDate.get(Calendar.MONTH);
        int year  = mcurrentDate.get(Calendar.YEAR);

        DatePickerDialog dpd;

        dpd = new DatePickerDialog(Details_ViewOrderOfMale.this, 0,
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
                            Log.e("m",m+"  "+month);
                        }else{
                            m=""+(month+1);
                            Log.e("m",m+" after "+month);

                        }

                        delivery_date_str=d+"-"+m+"-"+year;
                        Log.e("date",d+"-"+m+"-"+year);
                        if(tab.equals("0")|| tab.equals("1")){
                       DataBaseHelper db=new DataBaseHelper(Details_ViewOrderOfMale.this);
                       boolean check=db.updateTimeDate_OFF_ON(delivery_time_str,delivery_date_str,id); //
                            if(check==true){
                                Toast.makeText(Details_ViewOrderOfMale.this, "time is set", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Details_ViewOrderOfMale.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }else if(tab.equals("2")||tab.equals("3")){

                            DatabaseFemale db=new DatabaseFemale(Details_ViewOrderOfMale.this);
                            boolean check=db.updateTimeDate_OFF_ON_Female(delivery_time_str,delivery_date_str,id);
                              if(check==true){
                                  Toast.makeText(Details_ViewOrderOfMale.this, "time is set", Toast.LENGTH_SHORT).show();
                              }
                        }

                    }

                }, day, month, year);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis());//this method is start from the currnt date,bydefault its show the 1900
        dpd.show();



        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Details_ViewOrderOfMale.this, new TimePickerDialog.OnTimeSetListener() {
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

                 delivery_time_str=h+":"+m+":"+timeFormat;
                Log.e("date",h+":"+m+":"+timeFormat);

            }
        }, hour, minute, false);

        timePickerDialog.show();

    }

    public void showDate(String id){
        DataBaseHelper db=new DataBaseHelper(this);
        Cursor cr=db.showOneSpacificOrder(id);
        cr.moveToFirst();
        if(cr.getCount()>0){
                             do{ txv_id.setText(cr.getString(0));
                                 txv_name.setText(cr.getString(1));
                                 txv_phone.setText("ph#"+cr.getString(10));

                                 txv_order.setText(cr.getString(2));
                                 txv_percost.setText("Per Sout cost : "+cr.getString(3));
                                  txv_totalcost.setText(cr.getString(12));
                                  String time=cr.getString(4);
                                   Log.e("ti",time.substring(0,9));
                                 Log.e("ti",time.substring(9,20));


                                 txv_current_time.setText(time.substring(0,8));
                                 txv_current_date.setText(time.substring(9,time.length()));

                                 txv_delivery_time.setText(cr.getString(5));
                                 txv_delivery_date.setText(cr.getString(6));

                                 Cursor crr=db.showOneSpacificImage(cr.getString(9));
                                    if(crr.getCount()>0){
                                        crr.moveToFirst();
                                        ImageView imagee=(ImageView)findViewById(R.id.image_between_collapsing_ti);
                                        byte[] image = crr.getBlob(0);
                                        Bitmap bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
                                      imagee.setImageBitmap(bmp);
                                        //imageView.setImageBitmap(bmp);

                                    }

                             }while (cr.moveToNext());
        }

    }

    public void showDateOfFemale(String id){
        DatabaseFemale db=new DatabaseFemale(this);
        Cursor cr=db.showOneSpacificOrderOfFemale(id);
        cr.moveToFirst();
        if(cr.getCount()>0){
            do{
            txv_id.setText(cr.getString(0));
            txv_name.setText(cr.getString(1));
            txv_phone.setText("ph#"+cr.getString(10));

            txv_order.setText(cr.getString(2));
            txv_percost.setText("Per Sout cost : "+cr.getString(3));
            txv_totalcost.setText(cr.getString(12));
            String time=cr.getString(4);
            Log.e("ti",time.substring(0,9));
            Log.e("ti",time.substring(9,20));


            txv_current_time.setText(time.substring(0,8));
            txv_current_date.setText(time.substring(9,time.length()));

            txv_delivery_time.setText(cr.getString(5));
            txv_delivery_date.setText(cr.getString(6));

            Cursor crr=db.showOneSpacificImageOfFemale(cr.getString(9));

                if(crr.getCount()>0){
                    crr.moveToFirst();
                    ImageView imagee=(ImageView)findViewById(R.id.image_between_collapsing_ti);
                    byte[] image = crr.getBlob(0);
                    Bitmap bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
                    imagee.setImageBitmap(bmp);
                    //imageView.setImageBitmap(bmp);

                }

        }while (cr.moveToNext());

        }

    }

}
