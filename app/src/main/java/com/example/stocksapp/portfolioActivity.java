package com.example.stocksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class portfolioActivity extends AppCompatActivity {



//    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


    public static TextView fetchedprice;
    DatabaseHelper  mydb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        fetchedprice = (TextView) findViewById(R.id.textView2);

        String main = getIntent().getStringExtra("message");
        int poq=Integer.parseInt(main);


      Cursor price=mydb.getPriceData();

        if(poq==2)
        {
        if(price.getCount() == 0) {
            // show message
            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();

        }

int a=price.getCount();
        int abcd=0;
        while (price.moveToNext()) {
            String sym = price.getString(0);
            //Toast.makeText(this,"symbol is"+sym, Toast.LENGTH_SHORT).show();
          //  makeApiCall(sym);
        String shares = price.getString(1);
        String oldprice = price.getString(2);
        abcd++;
        boolean t=false;
        if(abcd==a)
        {
           t=true;
        }
            callApiToDisplaySelectedStockDetails(sym,oldprice,shares,t);
        }
        }

//        fetchdata process=new fetchdata(sym);
//        process.execute();
//            try
//            {
//                Thread.sleep(1000);
//            }
//            catch(InterruptedException ex)
//            {
//                Thread.currentThread().interrupt();
//            }


     //String pqred=fetcheprice.getText().toString();

//     String pqred="271.5312";
    // fetcheprice.setText(pqred);


        // Toast.makeText(this,"STOCK PRICE IS " + pqred, Toast.LENGTH_SHORT).show();




display();








//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Click action
//                Intent intent = new Intent(portfolioActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity,menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.add:
            Intent i = new Intent(portfolioActivity.this, MainActivity.class);
            startActivity(i);
            return (true);
    }
        return(super.onOptionsItemSelected(item));
    }


//    private void makeApiCall(String text)
//    {
//        ApiCall.make(this, "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=" + text
//                + "&apikey=OUHJRQ3O14C8QJ84", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                List<String> stock_name = new ArrayList<>();
//                List<String> stock_symbol = new ArrayList<>();
//                List<String> stock_price = new ArrayList<>();
//                try {
//                    JSONObject responseObject = new JSONObject(response);
//                    JSONArray array = responseObject.getJSONArray("bestMatches");
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject row = array.getJSONObject(i);
//                        stock_name.add(row.getString("2. name"));
//                        stock_symbol.add(row.getString("1. symbol"));
//                        stock_price.add(row.getString("5. price"));
//                    }
//                    Toast.makeText(portfolioActivity.this,"API CALL WORKING",Toast.LENGTH_LONG).show();
//                }
//
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//    }



    private void callApiToDisplaySelectedStockDetails(final String stock_selected, final String oldprice1,final String shares,final Boolean cnt) {
        ApiCall.make(this, "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + stock_selected +
                        "&apikey=OUHJRQ3O14C8QJ84",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//String x= oldprice1;

                try {
                    JSONObject obj = new JSONObject(response);
                    String color3 = obj.getJSONObject("Global Quote").getString("05. price");


//                    String data3 =  color3;
//                    String pqre="1025.53";
                  //  fetchedprice.setText(color3);
                    //String pqred=fetchedprice.getText().toString();
                  //  Toast.makeText(portfolioActivity.this,"Price of stock is "+color3,Toast.LENGTH_LONG).show();
                // fetcheprice.setText(obj.getJSONObject("Global Quote").getString("05. price"));
                  //  Toast.makeText(portfolioActivity.this,"DISPLAY STOCK DETAILS WORKING",Toast.LENGTH_LONG).show();
                    if(!oldprice1.equals(color3))
                    {
                        float xy = Float.parseFloat(shares);
                        float yz=Float.parseFloat(String.valueOf(color3));
                        float f=xy*yz;
                        String abc=Float.toString(f);
                        boolean isUpdate=  mydb.updatePrice( stock_selected,color3,abc);
                        if(isUpdate == true)
                            Toast.makeText(portfolioActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(portfolioActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }

                        display();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
public void display()
    {
        ArrayList<stock> a=new ArrayList<>();
        ListView userlist=(ListView) findViewById(R.id.listview);
        Cursor cursor = mydb.getAllData();
        if(cursor.getCount() == 0) {
            // show message
            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();
        }

        while (cursor.moveToNext())
        {
            stock s= new stock(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            a.add(s);
        }

        StockAdapter adapter=new StockAdapter(this,R.layout.adapter_view_layout,a);
        userlist.setAdapter(adapter);
    }


//                try {
//        JSONObject obj = new JSONObject(response);
//
//        String color3 = obj.getJSONObject("Global Quote").getString("05. price");
//
//        String data3 =  color3;
//        fetchedprice.setText(data3);
//
//    }
//    public interface CallBack {
//        void onSuccess(String p,String oldprice,String shares,String sym );
//
//        void onFail(String msg);
//    }

}
