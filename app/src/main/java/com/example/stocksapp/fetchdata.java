package com.example.stocksapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchdata extends AsyncTask<Void,Void,Void> {
    String s;
    String  data="";
    String  dataParsed="";
    String  singleparsed="";
    public fetchdata(String s) {

        this.s = s;

    }

    @Override
    protected Void doInBackground(Void... voids) {


        try {
            URL url = new URL("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="+s+"&apikey=OUHJRQ3O14C8QJ84");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream inputStream = http.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line1 = "";
            while (line1 != null) {
                line1 = br.readLine();
                data = data +line1;
            }
            JSONObject j=new JSONObject(data);
          //  JSONArray JA;
          //  JA = j.getJSONArray("Global Quote");
//            for(int i=0;i<1;i++)
//            {
               // JSONObject JO =(JSONObject) JA.get(i);
               // singleparsed=JO.get("05. price")+"";

                //dataParsed=dataParsed+singleparsed;

            dataParsed = j.getJSONObject("Global Quote").getString("05. price");

            //}

        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        portfolioActivity.fetchedprice.setText(this.dataParsed);
    }
}


