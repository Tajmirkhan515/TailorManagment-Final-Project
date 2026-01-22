package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PantShirt_ShowAllInformation extends AppCompatActivity {
    TextView shirt_neck,shirt_chest,shirt_shoulder,shirt_sleeve,shirt_bicep,shirt_lengthOfWrist,shirt_jocket_waist,shirt_hip,shirt_length,shirt_waist,shirt_half_shoulder,shirt_backFullLength,shirt_backHalfLength,pant_waist,pant_length,pant_insideLength,pant_crotch,pant_thigh,pant_knee,pant_description;
    TextView pant_binCollar,pant_frontPacket;
    TextView userid_texv,username_texv,phone_texv;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pant_shirt__show_all_information_activity);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        toolbar.setTitle("Design");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Information ");

        allTextviewConnection();
        dataIsSetInTextview();

    }



    public void allTextviewConnection()
    {
        userid_texv=(TextView) findViewById(R.id.userid_male_texv_id);
        username_texv=(TextView)findViewById(R.id.username_male_texv_id);
        phone_texv=(TextView)findViewById(R.id.phone_male_texv_id);

        shirt_neck=(TextView) findViewById(R.id.shirtneck_id);
        shirt_chest=(TextView) findViewById(R.id.shirtchest_id);
        shirt_shoulder=(TextView) findViewById(R.id.shirtshoulder_id);
        shirt_sleeve=(TextView) findViewById(R.id.shirtsleeve_id);
        shirt_bicep=(TextView) findViewById(R.id.shirtbicep_id);
        shirt_lengthOfWrist=(TextView) findViewById(R.id.shirtwrist_id);
        shirt_jocket_waist=(TextView) findViewById(R.id.shirtjoecktwaist_id);
        shirt_hip=(TextView) findViewById(R.id.shirthip_id);
        shirt_length=(TextView) findViewById(R.id.shirtlength_id);
        shirt_waist=(TextView) findViewById(R.id.shirtwaist_id);
        shirt_half_shoulder=(TextView) findViewById(R.id.shirthalfshoulder_id);
        shirt_backFullLength=(TextView) findViewById(R.id.shirtbackfulllength_id);
        shirt_backHalfLength=(TextView) findViewById(R.id.shirtbackhalflength_id);
        pant_waist=(TextView) findViewById(R.id.pantwaist_id);
        pant_length=(TextView) findViewById(R.id.pantlength_id);
        pant_insideLength=(TextView) findViewById(R.id.pantinsidelength_id);
        pant_crotch=(TextView) findViewById(R.id.pantcrotch_id);
        pant_thigh=(TextView) findViewById(R.id.pantthigh_id);
        pant_knee=(TextView) findViewById(R.id.pantknee_id);

        pant_binCollar=(TextView)findViewById(R.id.pantshir_bin_collor_id);
        pant_frontPacket=(TextView)findViewById(R.id.pant_front_packet_id);

        pant_description=(TextView) findViewById(R.id.pantdescription_id);
    }

    public void dataIsSetInTextview(){
        DataBaseHelper   db=new DataBaseHelper(this);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        Cursor cr=db.spasificData(1,id);// the 0 is used for shalwarkameez table
        cr.moveToFirst();
        if(cr.getCount()>0){
            do{
                userid_texv.setText("ID  :"+cr.getString(0));
                username_texv.setText("Name  :"+cr.getString(1));
                phone_texv.setText("Phone    :"+cr.getString(2));

                shirt_neck.setText("Neck    :"+cr.getString(3));
                shirt_chest.setText("Chest  :"+cr.getString(4));
                shirt_shoulder.setText("Shoulder    :"+cr.getString(5));
                shirt_sleeve.setText("Sleeve:"+cr.getString(6));
                shirt_bicep.setText("Bicep  :"+cr.getString(7));
                shirt_lengthOfWrist.setText("Wrist  :"+cr.getString(8));
                shirt_jocket_waist.setText("Jocket Waist    :"+cr.getString(9));
                shirt_hip.setText("Hip  :"+cr.getString(10));
                shirt_length.setText("Shirt Length  :"+cr.getString(11));
                shirt_waist.setText("Shirt_waist    :"+cr.getString(12));
                shirt_half_shoulder.setText("Shirt Half Shoulder :"+cr.getString(13));
                shirt_backFullLength.setText("Back Full Length :"+cr.getString(14));
                shirt_backHalfLength.setText("Back Half Length :"+cr.getString(15));
                pant_waist.setText("Pant Waist  :"+cr.getString(16));
                pant_length.setText("Pant Length:"+cr.getString(17));
                pant_insideLength.setText("Pant Inside Length :"+cr.getString(18));
                pant_crotch.setText("Crotch :"+cr.getString(19));
                pant_thigh.setText("Pant Thigh  :"+cr.getString(20));
                pant_knee.setText("Pant Knee    :"+cr.getString(21));
                pant_binCollar.setText("Ben OR Collar:"+cr.getString(22));
                pant_frontPacket.setText("Front Packet  :"+cr.getString(23));
                pant_description.setText("Description   :"+cr.getString(24));
            }while (cr.moveToNext());
          }

            }

}
