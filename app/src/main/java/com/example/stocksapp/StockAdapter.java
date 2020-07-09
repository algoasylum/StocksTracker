package com.example.stocksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends ArrayAdapter<stock> {
    private Context mcontext;
    int mresource;


    public StockAdapter(@NonNull Context context, int resource, @NonNull ArrayList<stock> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        String id = getItem(position).getId();
        String compn = getItem(position).getCompn();
        String symbol = getItem(position).getSymbol();
        String no=getItem(position).getNoofshares();
        String price=getItem(position).getPrice();
        String tot=getItem(position).getTot_price();
        //Create the person object with the information
        stock stock = new stock(compn,symbol,no,price,tot);

        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView=inflater.inflate(mresource,parent,false);


//        TextView tvid=(TextView) convertView.findViewById(R.id.textView1);
        TextView tvcomp=(TextView) convertView.findViewById(R.id.textView2);
        TextView tvsymbol=(TextView) convertView.findViewById(R.id.textView3);
        TextView tvshares=(TextView) convertView.findViewById(R.id.textView4);
        TextView tvprice=(TextView) convertView.findViewById(R.id.textView5) ;
        TextView tvtot=(TextView) convertView.findViewById(R.id.textView6);
//
        tvcomp.setText(compn);
        tvsymbol.setText(symbol);
        tvshares.setText(no);
        tvprice.setText(price);
        tvtot.setText(tot);

        return convertView;

    }
}
