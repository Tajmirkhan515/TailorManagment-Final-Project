package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertNewCustomerFemale extends AppCompatActivity {
    Toolbar toolbar;
    Button insertbtn;
DatabaseFemale db;
    EditText userid_edt,username_edt,phone_edt;
  int flage=0;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_new_customer_female_activity);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Insert New Customer");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        insertbtn=(Button)findViewById(R.id.nextbtn_female_id);

        userid_edt=(EditText)findViewById(R.id.userid_female_edt_id);
        username_edt=(EditText)findViewById(R.id.username_female_edt_id);
        phone_edt=(EditText)findViewById(R.id.phone_female_edt_id);
        Intent intent=getIntent();
        flage=intent.getIntExtra("flage",0);
        id=intent.getStringExtra("id");
        if(flage==0){

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String namee=preferences.getString("female_id_autoincreament","0");  // there get the stored data from the sharedpreferences
            userid_edt.setText(namee);
            userid_edt.setEnabled(false);  // there we not write anything in the edittext
            userid_edt.setTextColor(Color.BLACK);


        } else if(flage==1){
            db=new DatabaseFemale(this);
            Cursor cr=db.spasificData(0,id);
            cr.moveToFirst();
            if(cr.getCount()>0){
                do{
                    userid_edt.setText(cr.getString(0));
                    userid_edt.setEnabled(false);
                    username_edt.setText(cr.getString(1));
                    phone_edt.setText(cr.getString(2));
                }while (cr.moveToNext());
            }
        }else if(flage==2) {
            db = new DatabaseFemale(this);
            Cursor cr = db.spasificData(1, id);
            cr.moveToFirst();
            if(cr.getCount()>0) {
                do {
                    userid_edt.setText(cr.getString(0));
                    username_edt.setText(cr.getString(1));
                    phone_edt.setText(cr.getString(2));
                } while (cr.moveToNext());
            }
        }
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InsertNewCustomerFemale.this,MeasurementOfFemale.class);
                String id=userid_edt.getText().toString().trim();
                String name=username_edt.getText().toString().trim();
                String phone=phone_edt.getText().toString().trim();

               intent.putExtra("flage",flage);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);

               if(id.equals("")|| name.equals("")||phone.equals(""))
               {
                    Toast.makeText(InsertNewCustomerFemale.this, "you must be enter these three requirement ", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(intent);
               }
              }
        });
      }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
