package com.tajmirkhan.tailormanagment;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Tajmir khan on 12/21/2017.
 */

public class MaleServiceClass extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        handler.post(runnable);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("dd","Start command is ca");
        return START_STICKY;
    }


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e("dd","Services the oncreate method");
            checkMale();
            checkFemale();
            handler.postDelayed(runnable,60000);
        }
    };

    public void checkFemale(){
DatabaseFemale db=new DatabaseFemale(this);
        Calendar c = Calendar.getInstance();  //its set the current time and date
        SimpleDateFormat sdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat stime = new SimpleDateFormat("hh:mm:a", Locale.getDefault());

        final String strDate = sdate.format(c.getTime());
        final String strtime=stime.format(c.getTime());
        Cursor cr= db.showDatandTimeDB();
        cr.moveToFirst();
        if(cr.getCount()>0){
            do{ Log.e("phone",cr.getString(2));
                if(strDate.equalsIgnoreCase(cr.getString(1)))  //this is date
                {  Log.e("strtime","time  "+cr.getString(0));
                    Log.e("strtime"," date  "+cr.getString(1));
                    Log.e("strtime","phone  "+cr.getString(2));
                    Log.e("strtime","id  "+cr.getString(3));
                    Log.e("strtime","id  "+cr.getString(4));

                    if(strtime.equalsIgnoreCase(cr.getString(0))){
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        v.vibrate(500);
                      boolean b=db.replaceON_OFF(cr.getString(3));

                        int per= ContextCompat.checkSelfPermission(MaleServiceClass.this, Manifest.permission.SEND_SMS);

                        if(per== PackageManager.PERMISSION_GRANTED)
                        {
                            SmsManager smsmanager=SmsManager.getDefault();
                            smsmanager.sendTextMessage(cr.getString(2),null,"FemaleTailor",null,null);

                            Log.e("db","permission successfull");
                            Toast.makeText(MaleServiceClass.this, "msg  sent", Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                            //ActivityCompat.requestPermissions(getApplicationContext() ,new String[]{Manifest.permission.SEND_SMS},123);

                            Toast.makeText(MaleServiceClass.this, "msg not sent", Toast.LENGTH_SHORT).show();
                            Log.e("db","not permission");
                        }



                        break;
                    }
                }
            }while (cr.moveToNext());
        }

    }


    public void checkMale(){
        DataBaseHelper db=new DataBaseHelper(this);
        Calendar c = Calendar.getInstance();  //its set the current time and date
        SimpleDateFormat sdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat stime = new SimpleDateFormat("hh:mm:a", Locale.getDefault());

        final String strDate = sdate.format(c.getTime());
        final String strtime=stime.format(c.getTime());
        Cursor cr= db.showDatandTimeDB();
        cr.moveToFirst();
        if(cr.getCount()>0){
            do{
                if(strDate.equalsIgnoreCase(cr.getString(1)))  //this is date
               {   Log.e("strtime",strtime);
                   Log.e("strdate",strDate);
                    Log.e("data",cr.getString(0));
                    if(strtime.equalsIgnoreCase(cr.getString(0))){
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        v.vibrate(500);

                        int per= ContextCompat.checkSelfPermission(MaleServiceClass.this, Manifest.permission.SEND_SMS);
                       boolean b= db.replaceON_OFF(cr.getString(3));

                        if(per== PackageManager.PERMISSION_GRANTED)
                        {
                            SmsManager smsmanager=SmsManager.getDefault();
                            smsmanager.sendTextMessage(cr.getString(2),null,"MaleTailor",null,null);

                            Log.e("db","permission successfull");
                            Toast.makeText(MaleServiceClass.this, "msg  sent", Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                           //ActivityCompat.requestPermissions(getApplicationContext() ,new String[]{Manifest.permission.SEND_SMS},123);
                            // only the permission code i wrote in the details_vieworderofmale class ther is not workin of the permission code
                            Toast.makeText(MaleServiceClass.this, "message not sent", Toast.LENGTH_SHORT).show();
                            Log.e("db","not permission");
                        }



                        break;
                    }
                }
            }while (cr.moveToNext());
        }
    }

}
