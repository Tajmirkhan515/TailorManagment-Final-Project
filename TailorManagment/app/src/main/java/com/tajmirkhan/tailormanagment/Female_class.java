package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Female_class extends AppCompatActivity {
    Toolbar toolbar;
    Button insertbtn,viewallcustomer_btn,updatedesign_btn,newOrder_btn,viewOrder_btn,exist_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.female_class_activity);
        toolbar=(Toolbar)findViewById(R.id.toolbar_female_id);                    //ToolBar All Coding

        insertbtn=(Button)findViewById(R.id.insertnewcustomer_female_id);
        viewallcustomer_btn=(Button)findViewById(R.id.viewallcustomer_female_id);
        updatedesign_btn=(Button)findViewById(R.id.updatedesign_female_id);
        viewOrder_btn=(Button)findViewById(R.id.vieworder_female_id);
        newOrder_btn=(Button)findViewById(R.id.neworder_female_id);
        exist_btn=(Button)findViewById(R.id.exist_female_id);


        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        getSupportActionBar().setTitle("Tailor Management");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Female_class.this,InsertNewCustomerFemale.class));
            }
        });
        viewallcustomer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Female_class.this,Female_ViewAllCustomer.class));
            }
        });
        updatedesign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Female_class.this,FemaleUpdateDesign.class));
            }
        });
        newOrder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Female_class.this,FemaleNewOrder.class));
            }
        });
        viewOrder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Female_class.this,Female_ViewOrder.class));
            }
        });
    }
}
