package com.example.stocksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Screen extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        tv=(TextView)findViewById(R.id.tv);
        iv=(ImageView)findViewById(R.id.iv);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(myanim);
        iv.startAnimation(myanim);
Intent intent=getIntent();
        final  String fgh="2";
        final String message = intent.getStringExtra("message");

        final Intent i =new Intent(this,portfolioActivity.class);
        i.putExtra("message",fgh);


        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();

    }

}
