package com.example.stocksapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class NewsActivity extends AppCompatActivity implements Serializable {
Button shownews;

    public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news);
        Intent intent = getIntent();

          data = (TextView)findViewById(R.id.sname);
        shownews=(Button) findViewById(R.id.button);
        final String message = intent.getStringExtra("message");



        shownews.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                validate(data.getText().toString());
            }
        });
    }

    void validate(String x)
    {
        Intent intent=new Intent(NewsActivity.this, ShowNews.class);
        intent.putExtra("message",x);
        startActivity(intent);
    }
}

