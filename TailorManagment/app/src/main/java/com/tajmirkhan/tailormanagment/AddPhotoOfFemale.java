package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddPhotoOfFemale extends AppCompatActivity {
    ImageView imageview;
    EditText edt;
    Button btn_save;
    RadioGroup redg_select_design;
    String checkdesign="";  // if the desing is 0 its means this is the shalware kameez image and if the 1 its means this is the pantshirt imge

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_photo_of_female_activity);

        imageview=(ImageView)findViewById(R.id.imagview_id);
        edt=(EditText)findViewById(R.id.edittext_id);
        btn_save=(Button)findViewById(R.id.savebtn_id);
        redg_select_design=(RadioGroup)findViewById(R.id.pant_shalwar_rdg_id);  // there is select the design information the design is a panshirt image or shalwar kameez image

        Intent intent=getIntent();
        final byte[] imagedata=intent.getByteArrayExtra("imagedata");

        Bitmap bitmap = BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
        imageview.setImageBitmap(bitmap);

        redg_select_design.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){

                    case R.id.shalwar_rdbtn_id:
                        checkdesign="0";
                        break;
                    case R.id.shirt_rdbtn_id:
                        checkdesign="1";
                        break;
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edt.getText().toString().equals("")){
                    Toast.makeText(AddPhotoOfFemale.this, "pleas Enter Name", Toast.LENGTH_SHORT).show();
                }else
                {
                    if(checkdesign.equals("")){
                        Toast.makeText(AddPhotoOfFemale.this, "pleas select the choice", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        DatabaseFemale db= new DatabaseFemale(getApplicationContext());
                        db.insertIntoImageTable(edt.getText().toString(),imagedata,checkdesign);
                        edt.setText("");
                        Toast.makeText(AddPhotoOfFemale.this, "data is saved", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });


    }

}
