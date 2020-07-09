package com.example.stocksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;

public class ShowNews extends AppCompatActivity implements Serializable {
    public static TextView data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        Intent intent = getIntent();

        data = (TextView) findViewById(R.id.data);
        data.setMovementMethod(new ScrollingMovementMethod());

        final String message = intent.getStringExtra("message");

        fetchdata process=new fetchdata(message);
        process.execute();

    }
}
