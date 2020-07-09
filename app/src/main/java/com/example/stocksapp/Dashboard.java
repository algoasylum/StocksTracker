package com.example.stocksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.l1);
        //LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.l2);
        //LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.l3);

        linearLayout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = new Intent(Dashboard.this,portfolioActivity.class);
                startActivity(myintent2);

            }
        });

    }
}
