package com.tajmirkhan.tailormanagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spash_screen);

        Thread thread=new Thread(){

            public void run(){
                try{
                    Thread.sleep(1000);
                    Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }catch (Exception ex){

                }

            }
        };thread.start();


    }
}
