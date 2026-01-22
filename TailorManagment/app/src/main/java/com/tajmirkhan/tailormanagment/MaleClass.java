package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MaleClass extends AppCompatActivity {
  Toolbar toolbar;
   Button insertbtn,viewallcustomer_btn,updatedesign_btn,newOrder_btn,viewOrder_btn,exist_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.male_class_activity);

        toolbar=(Toolbar)findViewById(R.id.toolbar_id);                    //ToolBar All Coding

        insertbtn=(Button)findViewById(R.id.insertnewcustomer_id);
        viewallcustomer_btn=(Button)findViewById(R.id.viewallcustomer_id);
        updatedesign_btn=(Button)findViewById(R.id.updatedesign_id);
        viewOrder_btn=(Button)findViewById(R.id.vieworder_id);
        newOrder_btn=(Button)findViewById(R.id.neworder_id);
exist_btn=(Button)findViewById(R.id.exist_id);








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
                startActivity(new Intent(MaleClass.this,InsertNewCustomerMale.class));
            }
        });
      viewallcustomer_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(MaleClass.this,ViewAllCustomer.class));
          }
      });

        updatedesign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MaleClass.this,UpdateDesign.class));
            }
        });

viewOrder_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MaleClass.this,View_Order.class));
        }
      });
        newOrder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(MaleClass.this,NewOrder.class));
            }
        });
 exist_btn.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         System.exit(1);

     }
    });

    }
}
