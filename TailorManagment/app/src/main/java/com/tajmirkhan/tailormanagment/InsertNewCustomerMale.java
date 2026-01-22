package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

public class InsertNewCustomerMale extends AppCompatActivity {
    Toolbar toolbar;
    Button insertbtn;
    DataBaseHelper db;
    int flage=0;


    EditText userid_edt,username_edt,phone_edt;
    ArrayList<ShalwarKameezData> shalwarKameelist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_new_customer_male_activity);
        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("CustomeToolbar");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InsertNewCustomerMale().finishActivity(3);
                finish();
            }
        });

        insertbtn=(Button)findViewById(R.id.nextbtn_id);

        userid_edt=(EditText)findViewById(R.id.userid_male_edt_id);
        username_edt=(EditText)findViewById(R.id.username_male_edt_id);
        phone_edt=(EditText)findViewById(R.id.phone_male_edt_id);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String namee=preferences.getString("id_autoincreament","0");  // there get the stored data from the sharedpreferences
        userid_edt.setText(namee);
       userid_edt.setEnabled(false);  // there we not write anything in the edittext
        userid_edt.setTextColor(Color.BLACK);


        Intent viewclass=getIntent();
        flage=viewclass.getIntExtra("flage",0);
        String id=viewclass.getStringExtra("id");
        if(flage==1){
            db=new DataBaseHelper(this);
            Cursor cr=db.spasificData(0,id);

            cr.moveToFirst();
            do{
                userid_edt.setText(cr.getString(0));
                username_edt.setText(cr.getString(1));
                phone_edt.setText(cr.getString(2));
            }while (cr.moveToNext());
         }
         else if(flage==2)
        {
            db=new DataBaseHelper(this);
            Cursor cr=db.spasificData(1,id); // the 1 is select the pantshirttable table
            cr.moveToFirst();
            do{
                userid_edt.setText(cr.getString(0));
                username_edt.setText(cr.getString(1));
                phone_edt.setText(cr.getString(2));
            }while (cr.moveToNext());
        }





        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(InsertNewCustomerMale.this, MeasurmentOfMale.class);

                    String id = userid_edt.getText().toString();
                    String name = username_edt.getText().toString();
                    String phone = phone_edt.getText().toString();

                    intent.putExtra("flage",flage);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);

                if(id.equals("")|| name.equals("")||phone.equals(""))
                {
                    Toast.makeText(InsertNewCustomerMale.this, "you must be enter these three requirement ", Toast.LENGTH_SHORT).show();
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
