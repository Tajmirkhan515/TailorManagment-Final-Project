package com.tajmirkhan.tailormanagment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import uk.co.senab.photoview.PhotoView;

import static com.tajmirkhan.tailormanagment.R.id.start;
import static com.tajmirkhan.tailormanagment.R.id.tabHost;


public class MeasurmentOfMale extends AppCompatActivity {
    // k means kameez
    // s means shalwar
    // shalwar kameez image buttons
    // rdg means radiogroup

     ImageButton klengthbtn,ksleevebtn,kshoulderbtn,kchestbtn,kmindabtn,kneckbtn,kwaistbtn,kwidthbtn,kabdomenbtn,kbicepbtn,kwristbtn,slengthbtn,swaistbtn,spanchbtn;
    ImageButton sk_binbtn,sk_collarbtn,kfrontpacketbtn,spacketbtn,ksidepacketbtn;

    Button sk_save_btn,sk_cancel_btn;

    DataBaseHelper db;
       int update=0;// if the update is zero then it save the data of the measumentOfMale class if update is one then it sava data of the updation
       int flage;
    int count; //for sharedpreferences

    // pant shirt image buttons
    ImageButton shirt_neck_btn,shirt_chest_btn,shirt_shoulder_btn,shirt_sleeve_btn,shirt_bicep_btn,shirt_lengthofwrist_btn,shirt_jocketlength_waist_btn;
    ImageButton shirt_hip_btn,shirt_length_btn,shirt_waist_btn,shirt_halfshoulder_btn,shirt_backfulllength_btn,shirt_backhalflength_btn,pant_waist_btn,pant_length_btn;
    ImageButton pant_insidelength_btn,pant_crotch_btn,pant_thigh_btn,pant_knee_btn,shirt_bin_rdbtn,shirt_collar_rdbtn,shirt_frontpacket_rdbtn;

    Button pantshirt_save_btn;
    Toolbar toolbar;
    TabHost tabhost;
       // shalwar kameez EditText
    EditText k_length,k_sleeve,k_shoulder,k_chest,k_underarm,k_neck,k_waist,k_width,k_abdomen,k_bicep,k_lengthOfWrist,s_length,s_waist,s_pancha,sk_description;
    String k_bin_collar,k_front_packet,s_packet,k_side_packet;
    RadioGroup k_bin_collar_rdg,k_front_packet_rdg,k_sidepacket_rdg,s_pocket_rdg;
       //pantshirt EditText
    EditText shirt_neck,shirt_chest,shirt_shoulder,shirt_sleeve,shirt_bicep,shirt_lengthOfWrist,shirt_jocket_waist,shirt_hip,shirt_length,shirt_waist,shirt_half_shoulder,shirt_backFullLength,shirt_backHalfLength,pant_waist,pant_length,pant_insideLength,pant_crotch,pant_thigh,pant_knee,pant_description;
    RadioGroup p_bin_collar_rdg,p_frontpacket_rdg;
      String pant_binCollar,pant_frontPacket;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measurment_of_male_activity);
         db=new DataBaseHelper(this);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        toolbar.setTitle("Measurement List");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        //editText  connection of the pantshirt
        shirt_neck=(EditText) findViewById(R.id.shirtneck_id);
        shirt_chest=(EditText) findViewById(R.id.shirtchest_id);
        shirt_shoulder=(EditText) findViewById(R.id.shirtshoulder_id);
        shirt_sleeve=(EditText) findViewById(R.id.shirtsleeve_id);
        shirt_bicep=(EditText) findViewById(R.id.shirtbicep_id);
        shirt_lengthOfWrist=(EditText) findViewById(R.id.shirtwrist_id);
        shirt_jocket_waist=(EditText) findViewById(R.id.shirtjoecktwaist_id);
        shirt_hip=(EditText) findViewById(R.id.shirthip_id);
        shirt_length=(EditText) findViewById(R.id.shirtlength_id);
        shirt_waist=(EditText) findViewById(R.id.shirtwaist_id);
        shirt_half_shoulder=(EditText) findViewById(R.id.shirthalfshoulder_id);
        shirt_backFullLength=(EditText) findViewById(R.id.shirtbackfulllength_id);
        shirt_backHalfLength=(EditText) findViewById(R.id.shirtbackhalflength_id);
        pant_waist=(EditText) findViewById(R.id.pantwaist_id);
        pant_length=(EditText) findViewById(R.id.pantlength_id);
        pant_insideLength=(EditText) findViewById(R.id.pantinsidelength_id);
        pant_crotch=(EditText) findViewById(R.id.pantcrotch_id);
        pant_thigh=(EditText) findViewById(R.id.pantthigh_id);
        pant_knee=(EditText) findViewById(R.id.pantknee_id);
        pant_description=(EditText) findViewById(R.id.pantdescription_id);
        /////////////////////////////////////////////////////////////////////////////////


        //radioGroup connection of pantshirt
        p_bin_collar_rdg=(RadioGroup)findViewById(R.id.pantshirt_collar_bin_rd_id);
        p_frontpacket_rdg=(RadioGroup)findViewById(R.id.shirt_frontpacket_rg_id);
          pantshirt_save_btn=(Button)findViewById(R.id.pantshirt_save_btn_id);


        // radiogruoup connection and save cancel button of the shalwar kameez
        /////////////////////////////////////////////////////////////////////////////////////////////
        sk_save_btn=(Button)findViewById(R.id.shalwarkameez_save_btn_id);
        sk_cancel_btn=(Button)findViewById(R.id.cancel_btn_id);


        k_bin_collar_rdg=(RadioGroup)findViewById(R.id.shalwarkameez_ben_collar_rdg_id);
        k_front_packet_rdg=(RadioGroup)findViewById(R.id.shalwarkameez_frontpacket_rdg_id);
        k_sidepacket_rdg=(RadioGroup)findViewById(R.id.kameez_side_packet_rdg_id);
        s_pocket_rdg=(RadioGroup)findViewById(R.id.shalwar_packet_rdg_id);
       //////////////////////////////////////////////////////////////////////////////////////////////
        tabhostAllWork(); // this is user define method      Intent viewclass=getIntent();


        Intent viewclass=getIntent();
         flage=viewclass.getIntExtra("flage",0);

        String id=viewclass.getStringExtra("id");
        if(flage==1){

            update=1;
            db=new DataBaseHelper(this);
            Cursor cr=db.spasificData(0,id);// the 0 is used for shalwarkameez table
            cr.moveToFirst();
            do{
                k_length.setText(cr.getString(3));
                k_sleeve.setText(cr.getString(4));
                k_shoulder.setText(cr.getString(5));
                k_chest.setText(cr.getString(6));
                k_underarm.setText(cr.getString(7));
                k_neck.setText(cr.getString(8));
                k_waist.setText(cr.getString(9));
                k_width.setText(cr.getString(10));
                k_abdomen.setText(cr.getString(11));
                k_bicep.setText(cr.getString(12));
                k_lengthOfWrist.setText(cr.getString(13));
                s_length.setText(cr.getString(14));
                s_waist.setText(cr.getString(15));
                s_pancha.setText(cr.getString(16));

              String bin_collor=cr.getString(17);

                RadioGroup bin_c=(RadioGroup)findViewById(R.id.shalwarkameez_ben_collar_rdg_id);
                RadioButton ben_btn=(RadioButton)findViewById(R.id.shalwarkameez_ben_id);
                RadioButton collar_btn=(RadioButton)findViewById(R.id.shalwarkameez_collar_id);

                if(bin_collor.equalsIgnoreCase("Ben"))
                {
                    ben_btn.setChecked(true);
                }else if(bin_collor.equals("Collar"))
                {
                    collar_btn.setChecked(true);
                }

                String front_packet_str=cr.getString(18);

                RadioGroup frontpacket_group=(RadioGroup)findViewById(R.id.shalwarkameez_frontpacket_rdg_id);
                RadioButton kameez_yes=(RadioButton)findViewById(R.id.kameez_front_yes_id);
                RadioButton kameez_no=(RadioButton)findViewById(R.id.kameez_front_no_id);

                if(front_packet_str.equalsIgnoreCase("Yes")){
                    kameez_yes.setChecked(true);
                }
                else if(front_packet_str.equalsIgnoreCase("NO")) {
                    kameez_no.setChecked(true);
                }

               String shalwar_pcket=cr.getString(19);
                RadioGroup shalwarpacket_group=(RadioGroup)findViewById(R.id.shalwar_packet_rdg_id);
                 RadioButton shalwar_yes=(RadioButton)findViewById(R.id.shalwar_pocket_yes_id);
                RadioButton shalwar_no=(RadioButton)findViewById(R.id.shalwar_pocket_no_id);

                if(shalwar_pcket.equalsIgnoreCase("Yes"))
                {
                    shalwar_yes.setChecked(true);
                }
                else if(shalwar_pcket.equalsIgnoreCase("no"))
                {
                    shalwar_no.setChecked(true);
                }

                String kameez_sidepacket_str=cr.getString(20);
                RadioGroup kameez_sidepacket_gruop=(RadioGroup)findViewById(R.id.kameez_side_packet_rdg_id);
                RadioButton side_x=(RadioButton)findViewById(R.id.kameez_side_packet_x_id);
                RadioButton side_xx=(RadioButton)findViewById(R.id.kameez_side_packet_xx_id);
                RadioButton side_no=(RadioButton)findViewById(R.id.kameez_side_packet_no_id);

                if(kameez_sidepacket_str.equalsIgnoreCase("x"))
                {
                    side_x.setChecked(true);
                }else if(kameez_sidepacket_str.equalsIgnoreCase("xx")){
                    side_xx.setChecked(true);
                }else if(kameez_sidepacket_str.equalsIgnoreCase("no")){
                    side_no.setChecked(true);
                }


                sk_description.setText(cr.getString(21));
            }while (cr.moveToNext());
        }
        else if(flage==2)
        { update=2;
            db=new DataBaseHelper(this);
            Cursor cr=db.spasificData(1,id); // the 1 is select the pantshirttable table
            cr.moveToFirst();
            do{tabhost.setCurrentTab(1);
                shirt_neck.setText(cr.getString(3));
                shirt_chest.setText(cr.getString(4));
                shirt_shoulder.setText(cr.getString(5));
                shirt_sleeve.setText(cr.getString(6));
                shirt_bicep.setText(cr.getString(7));
                shirt_lengthOfWrist.setText(cr.getString(8));
                shirt_jocket_waist.setText(cr.getString(9));
                shirt_hip.setText(cr.getString(10));
                shirt_length.setText(cr.getString(11));
                shirt_waist.setText(cr.getString(12));
                shirt_half_shoulder.setText(cr.getString(13));
                shirt_backFullLength.setText(cr.getString(14));
                shirt_backHalfLength.setText(cr.getString(15));
                pant_waist.setText(cr.getString(16));
                pant_length.setText(cr.getString(17));
                pant_insideLength.setText(cr.getString(18));
                pant_crotch.setText(cr.getString(19));
                pant_thigh.setText(cr.getString(20));
                pant_knee.setText(cr.getString(21));
               String collar_ben=cr.getString(22);
                RadioGroup pantshirt_collar_bin_gruop=(RadioGroup)findViewById(R.id.pantshirt_collar_bin_rd_id);
                RadioButton shirt_ben_btn=(RadioButton)findViewById(R.id.shirt_rd_bin_id);
                RadioButton shirt_collar_btn=(RadioButton)findViewById(R.id.shirt_rd_collar_id);
                if(collar_ben.equalsIgnoreCase("Ben"))
                {
                    shirt_ben_btn.setChecked(true);
                }else if(collar_ben.equalsIgnoreCase("collar")){
                    shirt_collar_btn.setChecked(true);
                }
                String shirt_packet=cr.getString(23);
                RadioGroup shirt_frontpacket_group=(RadioGroup)findViewById(R.id.shirt_frontpacket_rg_id);
                RadioButton shirt_no_btn=(RadioButton)findViewById(R.id.shirt_frontpacket_radio_no_id);
                RadioButton shirt_x_btn=(RadioButton)findViewById(R.id.shirt_frontpacket_radio_x_id);
                RadioButton shirt_xx_btn=(RadioButton)findViewById(R.id.shirt_frontpacket_radio_xx_id);
                if(shirt_packet.equalsIgnoreCase("No"))
                {
                    shirt_no_btn.setChecked(true);
                }else if(shirt_packet.equalsIgnoreCase("x")){
                    shirt_x_btn.setChecked(true);
                }else if(shirt_packet.equalsIgnoreCase("xx")){
                    shirt_xx_btn.setChecked(true);
                }
                pant_description.setText(cr.getString(24));
            }while (cr.moveToNext());

        }


            //first button is k_bin_collar_rdg this is radio button
          // second button is also radio Group k_front_packet_rdg
          // third button is also radio Gruop K_sidepacket_rdg
          //fourth button is also radio Gruop s_pocket_rdg
        // five position is sk_save_btn, its save all the data of the sahlwar kameez in database
         // six is a cacel button sk_cancel_button its cancel the measurmentofmale class and go to the home of the Maleclass
        /*then i define the tabhostAllWork() method inside the Tabhost i call the current Tabhost two method  the one is shalwarKameezImageButtonConnection();
        and the another is allEditTextConnectionOfShalwarKameez(); this two method call in current tabhost if you change the tabhost the call the another two method
        the first one is pantshirtImageButtonConnection() and the second one is allEditTextConnectionOFpantshirt();

        */




        ////////////////////////////////////////////////////////////////////////////////////////////
    k_bin_collar_rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged (RadioGroup group,int checkedId){
          switch (checkedId)
             {
                 case R.id.shalwarkameez_ben_id:
                     k_bin_collar="Ben";
                  break;
                 case R.id.shalwarkameez_collar_id:
                    k_bin_collar="Collar";
             }

        }
    });

    k_front_packet_rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged (RadioGroup group,int checkedId){
           switch (checkedId)
           {
               case R.id.kameez_front_yes_id:
                   k_front_packet="Yes";
                   break;
               case R.id.kameez_front_no_id:
                   k_front_packet="No";
                   break;
           }

        }
    });

        k_sidepacket_rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId) {
             switch (checkedId)
              {
    case R.id.kameez_side_packet_no_id:
        k_side_packet="NO";
        break;
    case R.id.kameez_side_packet_x_id:
        k_side_packet="X";
        break;
    case R.id.kameez_side_packet_xx_id:
        k_side_packet="XX";
        break;
             }

            }});

        s_pocket_rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId) {
                switch (checkedId)
                {
                    case R.id.shalwar_pocket_yes_id:
                   s_packet="Yes";
                        break;
                    case R.id.shalwar_pocket_no_id:
                        s_packet="No";

                        break;


                }

            }});

        sk_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=getIntent();

               if(update==0)
               {
                String id=intent.getStringExtra("id");
                String name=intent.getStringExtra("name");
                String phone=intent.getStringExtra("phone");

                String length=k_length.getText().toString();
                String sleeve=k_sleeve.getText().toString();
                String shoulder=k_shoulder.getText().toString();
                String chest=k_chest.getText().toString();
                String underarm=k_underarm.getText().toString();
                String neck=k_neck.getText().toString();

                String waist=k_waist.getText().toString();
                String width=k_width.getText().toString();
                String abdomen=k_abdomen.getText().toString();
                String bicep=k_bicep.getText().toString();
                String lenthofwrist=k_lengthOfWrist.getText().toString();
                String ss_length=s_length.getText().toString();
                String ss_waist=s_waist.getText().toString();
                String pancha=s_pancha.getText().toString();
                String description=sk_description.getText().toString();
    // the below code i will use for the autoincreament of the insertnewCustomerMale class i want to set by default some number
                   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MeasurmentOfMale.this);
                    count= Integer.parseInt(preferences.getString("id_autoincreament","0"));
                   SharedPreferences.Editor editor = preferences.edit();

                   count++;
                   editor.putString("id_autoincreament", String.valueOf(count));
                   editor.apply(); //insted of comit() method i used the apply() the apply method change every time the data

                boolean check;
                   if(length.equals("")){
                       Toast.makeText(MeasurmentOfMale.this, "pleas fill in blanks", Toast.LENGTH_SHORT).show();
                   }else {
                       //  check=db.insertData(id,name,phone,length,sleeve,shoulder,chest,underarm,neck,waist,width,abdomen,bicep,lenthofwrist,ss_length,ss_waist,pancha,k_bin_collar,k_front_packet,s_packet,k_side_packet,description);
                       check = db.insertData(id, name, phone, length, sleeve, shoulder, chest, underarm, neck, waist, width, abdomen, bicep, lenthofwrist, ss_length, ss_waist, pancha, k_bin_collar, k_front_packet, s_packet, k_side_packet, description);

 if(check==true)
 {
     Toast.makeText(MeasurmentOfMale.this, "Save is Success", Toast.LENGTH_SHORT).show();
    k_length.setText("");
     k_sleeve.setText("");
     k_shoulder.setText("");
     k_chest.setText("");
     k_underarm.setText("");
     k_neck.setText("");
     k_waist.setText("");
     k_abdomen.setText("");
     k_bicep.setText("");
     k_lengthOfWrist.setText("");
     s_length.setText("");
     s_waist.setText("");
     s_pancha.setText("");
     sk_description.setText("");
     finish();
 }else
 {

     Toast.makeText(MeasurmentOfMale.this, "Error", Toast.LENGTH_SHORT).show();

 }
                   }
               }else if(update==1)
               {

                   String id=intent.getStringExtra("id");
                   String name=intent.getStringExtra("name");
                   String phone=intent.getStringExtra("phone");

                   String length=k_length.getText().toString();
                   String sleeve=k_sleeve.getText().toString();
                   String shoulder=k_shoulder.getText().toString();
                   String chest=k_chest.getText().toString();
                   String underarm=k_underarm.getText().toString();
                   String neck=k_neck.getText().toString();

                   String waist=k_waist.getText().toString();
                   String width=k_width.getText().toString();
                   String abdomen=k_abdomen.getText().toString();
                   String bicep=k_bicep.getText().toString();
                   String lenthofwrist=k_lengthOfWrist.getText().toString();
                   String ss_length=s_length.getText().toString();
                   String ss_waist=s_waist.getText().toString();
                   String pancha=s_pancha.getText().toString();
                   String description=sk_description.getText().toString();
                   boolean check;

                   check=db.updataShalwarKameez(id,name,phone,length,sleeve,shoulder,chest,underarm,neck,waist,width,abdomen,bicep,lenthofwrist,ss_length,ss_waist,pancha,k_bin_collar,k_front_packet,s_packet,k_side_packet,description);

                   if(check==true)
                   {
                       Toast.makeText(MeasurmentOfMale.this, "Updating is Success", Toast.LENGTH_SHORT).show();
                       k_length.setText("");
                       k_sleeve.setText("");
                       k_shoulder.setText("");
                       k_chest.setText("");
                       k_underarm.setText("");
                       k_neck.setText("");
                       k_waist.setText("");
                       k_abdomen.setText("");
                       k_bicep.setText("");
                       k_lengthOfWrist.setText("");
                       s_length.setText("");
                       s_waist.setText("");
                       s_pancha.setText("");
                       sk_description.setText("");
                       finish();
                   }else
                   {

                       Toast.makeText(MeasurmentOfMale.this, "update is not Success", Toast.LENGTH_SHORT).show();

                   }


               }
            }
        });

        sk_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            startActivity(new Intent(MeasurmentOfMale.this,MaleClass.class));


            }
        });


        p_bin_collar_rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId) {
                switch (checkedId)
                {
                    case R.id.shirt_rd_bin_id:
                        pant_binCollar="bin";
                        break;
                    case R.id.shirt_rd_collar_id:
                        pant_binCollar="Collar";
                        break;

                }

            }});


       p_frontpacket_rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId) {
                switch (checkedId)
                {
                    case R.id.shirt_frontpacket_radio_no_id:
                        pant_frontPacket="NO";
                        break;
                    case R.id.shirt_frontpacket_radio_x_id:
                        pant_frontPacket="X";
                        break;
                    case R.id.shirt_frontpacket_radio_xx_id:
                        pant_frontPacket="XX";
                        break;
                }

            }});


        pantshirt_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=getIntent();
         if(update==0){
                String id=intent.getStringExtra("id");
                String name=intent.getStringExtra("name");
                String phone=intent.getStringExtra("phone");
                   String shirtneck=shirt_neck.getText().toString();
                String shirtchest=shirt_chest.getText().toString();
               String shirtShoulder=shirt_shoulder.getText().toString();
                String shirtSleeve=shirt_sleeve.getText().toString();
                String shirtBicep=shirt_bicep.getText().toString();
                String shirtLengthOfWrist=shirt_lengthOfWrist.getText().toString();
                String shirtJocketWaist=shirt_jocket_waist.getText().toString();
                String shirHip=shirt_hip.getText().toString();
                String shirtLength=shirt_length.getText().toString();
                String shirtwaist=shirt_waist.getText().toString();
                String shirtHalfShoulder=shirt_half_shoulder.getText().toString();
                String shirtBackFullLength=shirt_backFullLength.getText().toString();
                String shirtBackHalfLength=shirt_backHalfLength.getText().toString();
                String pantwaist=pant_waist.getText().toString();
                String pantLength=pant_length.getText().toString();
                String pantInsideLength=pant_insideLength.getText().toString();
                String pantCrotch=pant_crotch.getText().toString();
                String pantThigh=pant_thigh.getText().toString();
                String pantKnee=pant_knee.getText().toString();

                //pant_binCollar,pant_frontPacket;
                String pantDescription=pant_description.getText().toString();


             // the below code i will use for the autoincreament of the insertnewCustomerMale class i want to set by default some number
             SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MeasurmentOfMale.this);
             count= Integer.parseInt(preferences.getString("id_autoincreament","0"));// there get the saved data in sharedpreferences
             SharedPreferences.Editor editor = preferences.edit();
             count++;          // and increament
             editor.putString("id_autoincreament", String.valueOf(count));  //again put the new data
             editor.apply();

             if(shirtLength.equals("")){
                 Toast.makeText(MeasurmentOfMale.this, "pleas fill in blanks", Toast.LENGTH_SHORT).show();
             }else {
                boolean b=  db.insertDataOfPantShirt(id,name,phone,shirtneck,shirtchest,shirtShoulder,shirtSleeve,shirtBicep,shirtLengthOfWrist,shirtJocketWaist,shirHip,shirtLength,shirtwaist,shirtHalfShoulder,shirtBackFullLength,shirtBackHalfLength,pantwaist,pantLength,pantInsideLength,pantCrotch,pantThigh,pantKnee,pant_binCollar,pant_frontPacket,pantDescription);
                if(b==true){
                    Toast.makeText(MeasurmentOfMale.this, "Save is Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(MeasurmentOfMale.this, "Error", Toast.LENGTH_SHORT).show();
                }}
            }else  if(update==2){
             String id=intent.getStringExtra("id");
             String name=intent.getStringExtra("name");
             String phone=intent.getStringExtra("phone");
             String shirtneck=shirt_neck.getText().toString();
             String shirtchest=shirt_chest.getText().toString();
             String shirtShoulder=shirt_shoulder.getText().toString();
             String shirtSleeve=shirt_sleeve.getText().toString();
             String shirtBicep=shirt_bicep.getText().toString();
             String shirtLengthOfWrist=shirt_lengthOfWrist.getText().toString();
             String shirtJocketWaist=shirt_jocket_waist.getText().toString();
             String shirHip=shirt_hip.getText().toString();
             String shirtLength=shirt_length.getText().toString();
             String shirtwaist=shirt_waist.getText().toString();
             String shirtHalfShoulder=shirt_half_shoulder.getText().toString();
             String shirtBackFullLength=shirt_backFullLength.getText().toString();
             String shirtBackHalfLength=shirt_backHalfLength.getText().toString();
             String pantwaist=pant_waist.getText().toString();
             String pantLength=pant_length.getText().toString();
             String pantInsideLength=pant_insideLength.getText().toString();
             String pantCrotch=pant_crotch.getText().toString();
             String pantThigh=pant_thigh.getText().toString();
             String pantKnee=pant_knee.getText().toString();

             //pant_binCollar,pant_frontPacket;
             String pantDescription=pant_description.getText().toString();

             db.updatePantShirt(id,name,phone,shirtneck,shirtchest,shirtShoulder,shirtSleeve,shirtBicep,shirtLengthOfWrist,shirtJocketWaist,shirHip,shirtLength,shirtwaist,shirtHalfShoulder,shirtBackFullLength,shirtBackHalfLength,pantwaist,pantLength,pantInsideLength,pantCrotch,pantThigh,pantKnee,pant_binCollar,pant_frontPacket,pantDescription);

         }
            }
        });

    }
   //coding of Tabhost
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void tabhostAllWork()  ///tabhost method
    {
        tabhost=(TabHost)findViewById(tabHost);
        tabhost.setup();

        //Tab 1
        TabHost.TabSpec spec = tabhost.newTabSpec("Tab One");
        spec.setContent(R.id.ShalwareKameez_tab);
        spec.setIndicator("shalwar Kameez");
        tabhost.addTab(spec);
        // tab 2


        spec = tabhost.newTabSpec("wel");
        spec.setContent(R.id.tab2);
        spec.setIndicator("pantshirt");
        tabhost.addTab(spec);
    if(0==tabhost.getCurrentTab())
    {
        shalwarKameezImageButtonConnection();
        allEditTextConnectionOfShalwarKameez();
        Toast.makeText(this, "this is current tabhost", Toast.LENGTH_SHORT).show();
    }
      else  if(1==tabhost.getCurrentTab())
        {
            pantShirtAllImageButtonConnection();
        Toast.makeText(this, "this is tabe 0", Toast.LENGTH_SHORT).show();
    }

           }
    /////////////////////////////////////////////////////////////////////////////////////////////////




    public void capture(View v)
    {

        Log.e("ph","capture method is calling");
        Dialog dialog = new Dialog(MeasurmentOfMale.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);
        dialog.setContentView(R.layout.photoviewer);
        PhotoView photoview=(PhotoView)dialog.findViewById(R.id.photoview_id);
        Log.e("ph","every id is assign");


        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();


        photoview.setMinimumWidth(width);
        photoview.setMinimumHeight(height);
        photoview.setMaxWidth(width);
        photoview.setMaxHeight(height);
        Log.e("ph","length is set now");

       // photoview.setScaleType(ImageView.ScaleType.FIT_XY);


        switch (v.getId()) {
            case R.id.kameezlength_pic_id:
                             photoview.setImageResource(R.drawable.kameez_lenght);
                           dialog.show();
                break;
            case R.id.kameezsleeve_pic_id:
                photoview.setImageResource(R.drawable.kameez_sleeve);
                           dialog.show();
                break;
            case R.id.kameezshoulder_pic_id:
                photoview.setImageResource(R.drawable.kameez_shoulder_image);
                            dialog.show();
                break;
            case R.id.kameezchest_pic_id:
                photoview.setImageResource(R.drawable.kameez_chest_image);
                  dialog.show();
            case R.id.kameezminda_pic_id:
                photoview.setImageResource(R.drawable.kameez_elbow_imagey);
                          dialog.show();
                break;
           case R.id.kameezneck_pic_id:
                photoview.setImageResource(R.drawable.kameez_neck_image);
                         dialog.show();
                break;
            case R.id.kameezwaist_pic_id:
                photoview.setImageResource(R.drawable.kemeez_waist_image);
                             dialog.show();
                break;
            case R.id.kameezwidth_pic_id:
                photoview.setImageResource(R.drawable.kameez_width_image);
                                   dialog.show();
                break;
             case R.id.kameezabdomen_pic_id:
                photoview.setImageResource(R.drawable.kameez_abdomen_image);
                                 dialog.show();
                break;
            case R.id.kameezbicep_pic_id:
                photoview.setImageResource(R.drawable.kameez_bicep_image);
                        dialog.show();
                break;
            case R.id.kameezlengthofwristwrist_pic_id:
                photoview.setImageResource(R.drawable.kameez_lengthofwrist);
                  dialog.show();
                break;
            case R.id.shalwarlength_pic_id:
                photoview.setImageResource(R.drawable.shalwar_length_image);
                 dialog.show();
                break;
            case R.id.shalwarwaist_pic_id:
                photoview.setImageResource(R.drawable.shalwar_waist_image);
                   dialog.show();
                break;
            case R.id.shalwarpancha_pic_id:
                photoview.setImageResource(R.drawable.shalwar_pancha_width);
                Log.e("d","side packet");
                dialog.show();
                break;
           case R.id.shalwarkameezben_pic_id:
                photoview.setImageResource(R.drawable.kameez_ben_image);
                        dialog.show();
                break;
           // case R.id.shalwarkameezcollar_pic_id:
             //   photoview.setImageResource(R.drawable.kameez_collar_image);
              //            dialog.show();
              //  break;
            case R.id.kameezfrontpacket_pic_id:
                photoview.setImageResource(R.drawable.kameez_frontpcket_image);
                    dialog.show();
                 break;
            case R.id.kameezsidepacket_pic_id:
                Log.e("d","side packet");
                photoview.setImageResource(R.drawable.kameez_sidepacket_image);
                   dialog.show();
                break;



            case R.id.shirtneck_pic_id:
                photoview.setImageResource(R.drawable.shirt_neck_image);
                   dialog.show();
                Log.e("bn","length is call");
                break;
            case R.id.shirtchest_pic_id:
                Log.e("bn","chest is call");
                photoview.setImageResource(R.drawable.shirt_chest_image);
                dialog.show();

                break;
            case R.id.shirtshoulder_pic_id:
                photoview.setImageResource(R.drawable.shirt_shoulder_image);
                  dialog.show();
                break;
            case R.id.shirtslevee_pic_id:
                photoview.setImageResource(R.drawable.shirt_sleeve_image);
                dialog.show();
                break;
            case R.id.shirtbicep_pic_id:
                photoview.setImageResource(R.drawable.shirt_bicep_image);
                dialog.show();
                break;
            case R.id.shirtlenthofwrist_pic_id:
                photoview.setImageResource(R.drawable.shirt_lengthofwrist_image);
                dialog.show();
                break;
            case R.id.shirtjocketwaist_pic_id:
                photoview.setImageResource(R.drawable.shirt_wasit);
                dialog.show();
                break;
            case R.id.shirthip_pic_id:
                photoview.setImageResource(R.drawable.shirt_hip_image);
                dialog.show();
                break;
            case R.id.shirtlength_pic_id:
                photoview.setImageResource(R.drawable.shirt_length_image);
                dialog.show();
                break;
            case R.id.shirtwasit_pic_id:
                photoview.setImageResource(R.drawable.shirt_waist_image);
                dialog.show();
                break;
            case R.id.shirthalfshoulder_pic_id:
                photoview.setImageResource(R.drawable.shirt_half_shoulder_image);
                dialog.show();
                break;
            case R.id.shirtbackfulllength_pic_id:
                photoview.setImageResource(R.drawable.shirt_back_full_length_image);
                 dialog.show();
                 break;
            case R.id.shirtbackhalflength_pic_id:
                photoview.setImageResource(R.drawable.shirt_half_bakc_length_image);
                 dialog.show();
                break;
            case R.id.pantwaist_pic_id:
                photoview.setImageResource(R.drawable.pant_waist_image);
                      dialog.show();
                break;
            case R.id.pantlength_pic_id:
                photoview.setImageResource(R.drawable.pant_length_image);
                  dialog.show();
                break;
            case R.id.pantinsidelength_pic_id:
                photoview.setImageResource(R.drawable.pant_inside_length_image);
                    dialog.show();
                break;
            case R.id.pantcrotch_pic_id:
                photoview.setImageResource(R.drawable.pant_crotch_image);
                   dialog.show();
                break;
            case R.id.pantthigh_pic_id:
                photoview.setImageResource(R.drawable.pant_thigh_image);
                  dialog.show();
                break;
            case R.id.pantknee_pic_id:
                photoview.setImageResource(R.drawable.pantt_knee_image);
                 dialog.show();
                break;
            case R.id.shirt_frontpacket_rdio_pic_id:
                photoview.setImageResource(R.drawable.pant_frontpacket_image);
                dialog.show();
                break;
            case R.id.shirt_rd_bin_pic_id:
                photoview.setImageResource(R.drawable.pantshirt_bin_image);
                dialog.show();
                break;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void shalwarKameezImageButtonConnection()
    {
        // shalwar kameez image button connection
        //////////////////////////////////////////////////////////////////////////////////////////////
        klengthbtn=(ImageButton)findViewById(R.id.kameezlength_pic_id);
        ksleevebtn=(ImageButton)findViewById(R.id.kameezsleeve_pic_id);
        kshoulderbtn=(ImageButton)findViewById(R.id.kameezshoulder_pic_id);
        kchestbtn=(ImageButton)findViewById(R.id.kameezchest_pic_id);
        kmindabtn=(ImageButton)findViewById(R.id.kameezminda_pic_id);
        kneckbtn=(ImageButton)findViewById(R.id.kameezneck_pic_id);
        kwaistbtn=(ImageButton)findViewById(R.id.kameezwaist_pic_id);
        kwidthbtn=(ImageButton)findViewById(R.id.kameezwidth_pic_id);

        kabdomenbtn=(ImageButton)findViewById(R.id.kameezabdomen_pic_id);
        kbicepbtn=(ImageButton)findViewById(R.id.kameezbicep_pic_id);
        kwristbtn=(ImageButton)findViewById(R.id.kameezlengthofwristwrist_pic_id);
        Log.e("dd","kwristbtn");
        slengthbtn=(ImageButton)findViewById(R.id.shalwarlength_pic_id);
        Log.e("dd","slengthbtn");
        swaistbtn=(ImageButton)findViewById(R.id.shalwarwaist_pic_id);
        Log.e("dd","swaistbtn");
        spanchbtn=(ImageButton)findViewById(R.id.shalwarpancha_pic_id);
        Log.e("dd","spanchbtn");
        sk_binbtn=(ImageButton)findViewById(R.id.shalwarkameezben_pic_id);
        Log.e("dd","binbtn");
         //sk_collarbtn=(ImageButton)findViewById(R.id.shalwarkameezcollar_pic_id);
        Log.e("dd","collarbtn");
         kfrontpacketbtn=(ImageButton)findViewById(R.id.kameezfrontpacket_pic_id);
        Log.e("dd","kfrontpacketbtn");
         spacketbtn=(ImageButton)findViewById(R.id.shalwarpacket_pic_id);
         ksidepacketbtn=(ImageButton)findViewById(R.id.kameezsidepacket_pic_id);


    }

    public void pantShirtAllImageButtonConnection()
    {
        // pant shirt image button connection

        shirt_neck_btn=(ImageButton)findViewById(R.id.shirtneck_pic_id);
        shirt_chest_btn=(ImageButton)findViewById(R.id.shirtchest_pic_id);
        shirt_shoulder_btn=(ImageButton)findViewById(R.id.shirtshoulder_pic_id);
        shirt_sleeve_btn=(ImageButton)findViewById(R.id.shirtslevee_pic_id);
        shirt_bicep_btn=(ImageButton)findViewById(R.id.shirtbicep_pic_id);
        shirt_lengthofwrist_btn=(ImageButton)findViewById(R.id.shirtlenthofwrist_pic_id);
        shirt_jocketlength_waist_btn=(ImageButton)findViewById(R.id.shirtjocketwaist_pic_id);
        Log.e("bn","another screen five button is finished");

        shirt_hip_btn=(ImageButton)findViewById(R.id.shirthip_pic_id);
        shirt_length_btn=(ImageButton)findViewById(R.id.shirtlength_pic_id);
        shirt_waist_btn=(ImageButton)findViewById(R.id.shirtwasit_pic_id);
        shirt_halfshoulder_btn=(ImageButton)findViewById(R.id.shirthalfshoulder_pic_id);
        shirt_backfulllength_btn=(ImageButton)findViewById(R.id.shirtbackfulllength_pic_id);
        shirt_backhalflength_btn=(ImageButton)findViewById(R.id.shirtbackhalflength_pic_id);
        pant_waist_btn=(ImageButton)findViewById(R.id.pantwaist_pic_id);
        pant_length_btn=(ImageButton)findViewById(R.id.pantlength_pic_id);
        pant_insidelength_btn=(ImageButton)findViewById(R.id.pantinsidelength_pic_id);
        pant_crotch_btn=(ImageButton)findViewById(R.id.pantcrotch_pic_id);
        pant_thigh_btn=(ImageButton)findViewById(R.id.pantthigh_pic_id);
        pant_knee_btn=(ImageButton)findViewById(R.id.pantknee_pic_id);
        Log.e("bn","the another 10 button is finished");


        shirt_bin_rdbtn=(ImageButton)findViewById(R.id.shirt_rd_bin_pic_id);
        //shirt_collar_rdbtn=(ImageButton)findViewById(R.id.shirt_rd_collar_pic_id);
        shirt_frontpacket_rdbtn=(ImageButton)findViewById(R.id.shirt_frontpacket_rdio_pic_id);

    }

    public void allEditTextConnectionOfShalwarKameez()
    {

        k_length=(EditText)findViewById(R.id.kameezlength_id);
        k_sleeve=(EditText)findViewById(R.id.kameezsleeve_id);
        k_shoulder=(EditText)findViewById(R.id.kameezshoulder_id);
        k_chest=(EditText)findViewById(R.id.kameezchest_id);
        k_underarm=(EditText)findViewById(R.id.kameezunderarm_id);
        k_neck=(EditText)findViewById(R.id.kameezneck_id);
        k_waist=(EditText)findViewById(R.id.kameezwaist_id);
        k_width=(EditText)findViewById(R.id.kameezwidth_id);
        k_abdomen=(EditText)findViewById(R.id.kameezabdomen_id);
        k_bicep=(EditText)findViewById(R.id.kameezbicep_id);
        k_lengthOfWrist=(EditText)findViewById(R.id.kameezwrist_id);
        s_length=(EditText)findViewById(R.id.shalwarlength_id);
        s_waist=(EditText)findViewById(R.id.shalwarwaist_id);
        s_pancha=(EditText)findViewById(R.id.shalwarpancha_id);
        sk_description=(EditText)findViewById(R.id.shalwarkameezdescription_id);


    }



}
