package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.data;
import static android.R.attr.id;

public class ShalwarK_ShowAllInformation extends AppCompatActivity {
 Toolbar toolbar;

    TextView userid_texv,username_texv,phone_texv;
    // shalwar kameez EditText
    TextView k_length_texv,k_sleeve_texv,k_shoulder_texv,k_chest_texv,k_underarm_texv,k_neck_texv,k_waist_texv,k_width_texv,k_abdomen_texv,k_bicep_texv,k_lengthOfWrist_texv,s_length_texv,s_waist_texv,s_pancha_texv,sk_description_texv;
    TextView k_bin_collar_texv,k_front_packet_texv,s_packet_texv,k_side_packet_texv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shalwark_showallinformation);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
         setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Information");

        allTextviewConnectionOfShalwarKameez();
        dataIsSetOnTextview();

    }





    public void allTextviewConnectionOfShalwarKameez()
    {

        userid_texv=(TextView) findViewById(R.id.userid_male_texv_id);
        username_texv=(TextView)findViewById(R.id.username_male_texv_id);
        phone_texv=(TextView)findViewById(R.id.phone_male_texv_id);


        k_length_texv=(TextView) findViewById(R.id.kameezlength_id);
        k_sleeve_texv=(TextView)findViewById(R.id.kameezsleeve_id);
        k_shoulder_texv=(TextView)findViewById(R.id.kameezshoulder_id);
        k_chest_texv=(TextView)findViewById(R.id.kameezchest_id);
        k_underarm_texv=(TextView)findViewById(R.id.kameezunderarm_id);
        k_neck_texv=(TextView)findViewById(R.id.kameezneck_id);
        k_waist_texv=(TextView)findViewById(R.id.kameezwaist_id);
        k_width_texv=(TextView)findViewById(R.id.kameezwidth_id);
        k_abdomen_texv=(TextView)findViewById(R.id.kameezabdomen_id);
        k_bicep_texv=(TextView)findViewById(R.id.kameezbicep_id);
        k_lengthOfWrist_texv=(TextView)findViewById(R.id.kameezwrist_id);
        s_length_texv=(TextView)findViewById(R.id.shalwarlength_id);
        s_waist_texv=(TextView)findViewById(R.id.shalwarwaist_id);
        s_pancha_texv=(TextView)findViewById(R.id.shalwarpancha_id);

        k_bin_collar_texv=(TextView)findViewById(R.id.shalwarkameez_ben_texv_id);
        k_front_packet_texv=(TextView)findViewById(R.id.shalwarkameez_frontpacket_id);
        s_packet_texv=(TextView)findViewById(R.id.shalwarkameez_shalwarpacket_id);
        k_side_packet_texv=(TextView)findViewById(R.id.sidepacket_id);

        sk_description_texv=(TextView)findViewById(R.id.shalwarkameezdescription_id);
    }


   public void dataIsSetOnTextview(){

    DataBaseHelper   db=new DataBaseHelper(this);
       Intent intent=getIntent();
       String id=intent.getStringExtra("id");
       Cursor cr=db.spasificData(0,id);// the 0 is used for shalwarkameez table
       cr.moveToFirst();
       if(cr.getCount()>0){
       do{
           userid_texv.setText("ID  :"+cr.getString(0));
           username_texv.setText("Name  :"+cr.getString(1));
           phone_texv.setText("Phone    :"+cr.getString(2));

           k_length_texv.setText("Length :"+cr.getString(3));
           k_sleeve_texv.setText("Sleeve :"+cr.getString(4));
           k_shoulder_texv.setText("Shoulder :"+cr.getString(5));
           k_chest_texv.setText("Chest   :"+cr.getString(6));
           k_underarm_texv.setText("Underarm :"+cr.getString(7));
           k_neck_texv.setText("Neck :"+cr.getString(8));
           k_waist_texv.setText("Waist   :"+cr.getString(9));
           k_width_texv.setText("Width   :"+cr.getString(10));
           k_abdomen_texv.setText("Abdomen   :"+cr.getString(11));
           k_bicep_texv.setText("Bicep   :"+cr.getString(12));
           k_lengthOfWrist_texv.setText("Wrist   :"+cr.getString(13));
           s_length_texv.setText("Shalwar Length:"+cr.getString(14));
           s_waist_texv.setText("Shalwar Waist:"+cr.getString(15));
           s_pancha_texv.setText("Panch  :"+cr.getString(16));

           k_bin_collar_texv.setText("Ben    :"+cr.getString(17));
           k_front_packet_texv.setText("Front Packet:"+cr.getString(18));
           s_packet_texv.setText("Shalwar Packet:"+cr.getString(19));
           k_side_packet_texv.setText("Kameez Side Packet:"+cr.getString(20));

           sk_description_texv.setText("Description  :"+cr.getString(21));
       }while (cr.moveToNext());
    }
   }

}
