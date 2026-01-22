package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import static android.R.id.tabhost;

public class MeasurementOfFemale extends AppCompatActivity {
TabHost tabhost2;
    Toolbar toolbar;
    DatabaseFemale db;
    // f =female
    //k=kameez
    EditText f_k_length,f_k_neck,f_k_shoulder,f_k_bust,f_k_waist,f_k_hip,f_k_borderm,f_k_sleeve,f_k_underarm,f_k_bicep,f_k_wrist;
    EditText f_shalwar_waist,f_shalwar_length,f_shalwar_bottom,f_shalwarkameez_description;
    Button f_sk_cancel_btn,f_sk_save_btn;

    //s=shirt
    //p=pant
    EditText f_s_bust,f_s_waist,f_s_hip,f_s_shoulder,f_s_length,f_s_sleeve,f_s_bicep,f_s_wrist;
    EditText f_p_waist,f_p_thighatcrotch,f_p_knee,f_p_calf,f_p_ankle,f_p_inseam,f_p_waisttocrotch,f_p_length,f_ps_description;
    Button f_ps_cancel_btn,f_ps_save_btn;

    int flage;
    int update=0;
    String id;
    int count;
    Cursor cr=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measurement_of_female_activity);
        db=new DatabaseFemale(this);

        toolbar=(Toolbar)findViewById(R.id.female_toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        toolbar.setTitle("Measurement List");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tabhost2=(TabHost)findViewById(R.id.femal_tabhost_id);
        tabhost2.setup();

        TabHost.TabSpec spec = tabhost2.newTabSpec("Tab One");
        spec.setContent(R.id.female_tab_shalwar_id);
        spec.setIndicator("shalwar Kameez");
        tabhost2.addTab(spec);


        spec = tabhost2.newTabSpec("Tab two");
        spec.setContent(R.id.female_tab_pantshirt_id);
        spec.setIndicator("Pantshirt");
        tabhost2.addTab(spec);
        conectivityOfShalwarKameezEdtAndButton();//there is all connectivity of the shalwarKameez edt and button connectivity
        connectivityOfPantshirtEdtAndButton();//there is all connectivity of the pantshirt edt and button connectivity

     Intent viewclass=getIntent();
        flage=viewclass.getIntExtra("flage",0);
        id=viewclass.getStringExtra("id");


        if(flage==1){
            tabhost2.setCurrentTab(0);
            tabhost2.getTabWidget().getChildTabViewAt(1).setEnabled(false);
            update=1;
             cr=db.spasificData(0,id);// the 0 is used for shalwarkameez table
            setShalwarkameezDataForUpdation();
        }else if(flage==2) {
            update=2;
            tabhost2.setCurrentTab(1);
            tabhost2.getTabWidget().getChildTabViewAt(0).setEnabled(false);
            cr=db.spasificData(1,id); // the 1 is select the pantshirttable table
            setPantshirtDataForUpdation();
        }


        f_sk_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromFromShalwarKameezEdt();
            }
        });

        f_ps_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromFromPantShirtEdt();
            }
        });
    }

    public void setShalwarkameezDataForUpdation(){
        cr.moveToFirst();

        if(cr.getCount()>0){
            do{
                f_k_length.setText(cr.getString(3));
                f_k_neck.setText(cr.getString(4));
                f_k_shoulder.setText(cr.getString(5));
                f_k_bust.setText(cr.getString(6));
                f_k_waist.setText(cr.getString(7));
                f_k_hip.setText(cr.getString(8));
                f_k_borderm.setText(cr.getString(9));
                f_k_sleeve.setText(cr.getString(10));
                f_k_underarm.setText(cr.getString(11));
                f_k_bicep.setText(cr.getString(12));
                f_k_wrist.setText(cr.getString(13));
                f_shalwar_waist.setText(cr.getString(14));
                f_shalwar_length.setText(cr.getString(15));
                f_shalwar_bottom.setText(cr.getString(16));
                f_shalwarkameez_description.setText(cr.getString(17));
            }while (cr.moveToNext());
        }
    }

    public void conectivityOfShalwarKameezEdtAndButton (){
        f_k_length=(EditText)findViewById(R.id.female_shk_length_edt_id);
        f_k_neck=(EditText)findViewById(R.id.female_shk_neck_edt_id);
        f_k_shoulder=(EditText)findViewById(R.id.female_shk_shoulder_edt_id);
        f_k_bust=(EditText)findViewById(R.id.female_shk_bust_edt_id);
        f_k_waist=(EditText)findViewById(R.id.female_shk_waist_edt_id);
        f_k_hip=(EditText)findViewById(R.id.female_shk_hip_edt_id);
        f_k_borderm=(EditText)findViewById(R.id.female_shk_borderm_edt_id);
        f_k_sleeve=(EditText)findViewById(R.id.female_shk_sleeve_edt_id);
        f_k_underarm=(EditText)findViewById(R.id.female_shk_underarm_edt_id);
        f_k_bicep=(EditText)findViewById(R.id.female_shk_bicep_edt_id);
        f_k_wrist=(EditText)findViewById(R.id.female_shk_wrist_edt_id);
        f_shalwar_waist=(EditText)findViewById(R.id.female_shalwar_waist_edt_id);
        f_shalwar_length=(EditText)findViewById(R.id.female_shk_shalwarlength_edt_id);
        f_shalwar_bottom=(EditText)findViewById(R.id.female_shk_bottom_edt_id);
        f_shalwarkameez_description=(EditText)findViewById(R.id.female_shk_description_edt_id);
        f_sk_cancel_btn=(Button)findViewById(R.id.female_shk_cancel_btn_id);
        f_sk_save_btn=(Button)findViewById(R.id.female_shk_save_btn_id);
    }

public void setPantshirtDataForUpdation(){
    cr.moveToFirst();
    if(cr.getCount()>0){
        do{
            f_s_bust.setText(cr.getString(3));
            f_s_waist.setText(cr.getString(4));
            f_s_hip.setText(cr.getString(5));
            f_s_shoulder.setText(cr.getString(6));
            f_s_length.setText(cr.getString(7));
            f_s_sleeve.setText(cr.getString(8));
            f_s_bicep.setText(cr.getString(9));
            f_s_wrist.setText(cr.getString(10));
            f_p_waist.setText(cr.getString(11));
            f_p_thighatcrotch.setText(cr.getString(12));
            f_p_knee.setText(cr.getString(13));
            f_p_calf.setText(cr.getString(14));
            f_p_ankle.setText(cr.getString(15));
            f_p_inseam.setText(cr.getString(16));
            f_p_waisttocrotch.setText(cr.getString(17));
            f_p_length.setText(cr.getString(18));
            f_ps_description.setText(cr.getString(19));
        }while (cr.moveToNext());
    }
}

    public void connectivityOfPantshirtEdtAndButton(){
        f_s_bust=(EditText)findViewById(R.id.female_shirt_bust_edt_id);
        f_s_waist=(EditText)findViewById(R.id.female_shirt_waist_edt_id);
        f_s_hip=(EditText)findViewById(R.id.female_shirt_hip_edt_id);
        f_s_shoulder=(EditText)findViewById(R.id.female_shirt_shoulder_edt_id);
        f_s_length=(EditText)findViewById(R.id.female_shirt_length_edt_id);
        f_s_sleeve=(EditText)findViewById(R.id.female_shirt_sleeve_edt_id);
        f_s_bicep=(EditText)findViewById(R.id.female_shirt_bicep_edt_id);
        f_s_wrist=(EditText)findViewById(R.id.female_shirt_wrist_edt_id);
        f_p_waist=(EditText)findViewById(R.id.female_pant_waist_edt_id);
        f_p_thighatcrotch=(EditText)findViewById(R.id.female_pant_thigh_edt_id);
        f_p_knee=(EditText)findViewById(R.id.female_pant_knee_edt_id);
        f_p_calf=(EditText)findViewById(R.id.female_pant_calf_edt_id);
        f_p_ankle=(EditText)findViewById(R.id.female_pant_ankle_edt_id);
        f_p_inseam=(EditText)findViewById(R.id.female_pant_inseam_edt_id);
        f_p_waisttocrotch=(EditText)findViewById(R.id.female_pant_waisttocrotch_edt_id);
        f_p_length=(EditText)findViewById(R.id.female_pant_length_edt_id);
        f_ps_description=(EditText) findViewById(R.id.female_pantshirt_description_edt_id);
        f_ps_cancel_btn=(Button)findViewById(R.id.female_pantshirt_cancel_btn_id);
        f_ps_save_btn=(Button)findViewById(R.id.female_pantshirt_save_btn_id);
    }

    public void getTextFromFromShalwarKameezEdt(){
        Intent intent=getIntent();

        String id=intent.getStringExtra("id");
        String name=intent.getStringExtra("name");
        String phone=intent.getStringExtra("phone");
        String f_k_length_str=f_k_length.getText().toString().trim();
        String f_k_neck_str=f_k_neck.getText().toString().trim();
        String f_k_shoulder_str=f_k_bust.getText().toString().trim();
        String f_k_bust_str=f_k_bust.getText().toString().trim();
        String f_k_waist_str=f_k_waist.getText().toString().trim();
        String f_k_hip_str=f_k_hip.getText().toString().trim();
        String f_k_borderm_str=f_k_borderm.getText().toString().trim();
        String f_k_sleeve_str=f_k_sleeve.getText().toString().trim();
        String f_k_underarm_str=f_k_underarm.getText().toString().trim();
        String f_k_bicep_str=f_k_bicep.getText().toString().trim();
        String f_k_wrist_str=f_k_wrist.getText().toString().trim();
        String f_shalwar_waist_str=f_shalwar_waist.getText().toString().trim();
        String f_shalwar_length_str=f_shalwar_length.getText().toString().trim();
        String f_shalwar_botton_str=f_shalwar_bottom.getText().toString().trim();
        String description=f_shalwarkameez_description.getText().toString().trim();

        if(update==0) {
            boolean check = db.inserteFemaleShalwarKameez(id, name, phone, f_k_length_str, f_k_neck_str, f_k_shoulder_str, f_k_bust_str, f_k_waist_str, f_k_hip_str, f_k_borderm_str, f_k_sleeve_str, f_k_underarm_str, f_k_bicep_str, f_k_wrist_str, f_shalwar_waist_str, f_shalwar_length_str, f_shalwar_botton_str, description);

            // the below code i will use for the autoincreament of the insertnewCustomerMale class i want to set by default some number
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MeasurementOfFemale.this);
            count= Integer.parseInt(preferences.getString("female_id_autoincreament","0"));
            SharedPreferences.Editor editor = preferences.edit();

            count++;
            editor.putString("female_id_autoincreament", String.valueOf(count));
            editor.apply(); //insted of comit() method i used the apply() the apply method change every time the data


            if(check==true){
            Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();
                finish();
        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        } else  if(update==1) {
            boolean check = db.updataFemaleShalwarKameez(id, name, phone, f_k_length_str, f_k_neck_str, f_k_shoulder_str, f_k_bust_str, f_k_waist_str, f_k_hip_str, f_k_borderm_str, f_k_sleeve_str, f_k_underarm_str, f_k_bicep_str, f_k_wrist_str, f_shalwar_waist_str, f_shalwar_length_str, f_shalwar_botton_str, description);

            if(check==true){
                Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "occurs the error", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void getTextFromFromPantShirtEdt(){
        Intent intent=getIntent();

        String id=intent.getStringExtra("id");
        String name=intent.getStringExtra("name");
        String phone=intent.getStringExtra("phone");

        String f_s_bust_str=f_s_bust.getText().toString().trim();
        String f_s_waist_str=f_s_waist.getText().toString().trim();
        String f_s_hip_str=f_s_hip.getText().toString().trim();
        String f_s_shoulder_str=f_s_shoulder.getText().toString().trim();
        String f_s_length_str=f_s_length.getText().toString().trim();
        String f_s_sleeve_str=f_s_sleeve.getText().toString().trim();
        String f_s_bicep_str=f_s_bicep.getText().toString().trim();
        String f_s_wrist_str=f_s_wrist.getText().toString().trim();
        String f_p_waist_str=f_p_waist.getText().toString().trim();
        String f_p_thighatcroatch_str=f_p_thighatcrotch.getText().toString().trim();
        String f_p_knee_str=f_p_knee.getText().toString().trim();
        String f_p_calf_str=f_p_calf.getText().toString().trim();
        String f_p_ankle_str=f_p_ankle.getText().toString().trim();
        String f_p_inseam_str=f_p_inseam.getText().toString().trim();
        String f_p_waisttocrotch_str=f_p_waisttocrotch.getText().toString().trim();
        String f_p_length_str=f_p_length.getText().toString().trim();
        String description=f_ps_description.getText().toString().trim();
        if(update==0){
       boolean check=db.inserteFemalePantshirt(id,name,phone,f_s_bust_str,f_s_waist_str,f_s_hip_str,f_s_shoulder_str,f_s_length_str,f_s_sleeve_str,f_s_bicep_str,f_s_wrist_str,f_p_waist_str, f_p_thighatcroatch_str,f_p_knee_str,f_p_calf_str,f_p_ankle_str,f_p_inseam_str,f_p_waisttocrotch_str,f_p_length_str,description);

            // the below code i will use for the autoincreament of the insertnewCustomerMale class i want to set by default some number
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MeasurementOfFemale.this);
            count= Integer.parseInt(preferences.getString("female_id_autoincreament","0"));
            SharedPreferences.Editor editor = preferences.edit();

            count++;
            editor.putString("female_id_autoincreament", String.valueOf(count));
            editor.apply(); //insted of comit() method i used the apply() the apply method change every time the data


            if(check==true){
            Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();
                finish();
        }else {
            Toast.makeText(this, "occurs the error", Toast.LENGTH_SHORT).show();
        }}else if(update==2){
            boolean check=db.updataFemalePantshirt(id,name,phone,f_s_bust_str,f_s_waist_str,f_s_hip_str,f_s_shoulder_str,f_s_length_str,f_s_sleeve_str,f_s_bicep_str,f_s_wrist_str,f_p_waist_str, f_p_thighatcroatch_str,f_p_knee_str,f_p_calf_str,f_p_ankle_str,f_p_inseam_str,f_p_waisttocrotch_str,f_p_length_str,description);
            if(check==true){
                Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "occurs the error", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
