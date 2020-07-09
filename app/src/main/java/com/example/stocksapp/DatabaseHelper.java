package com.example.stocksapp;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_name= "Stock.db";
    public static final String Table_name= "Info";
    //    public static final String col_1= "ID";
    public static final String col_2= "COMPANY_NAME";
    public static final String col_3= "SYMBOL";
    public static final String col_4= "SHARES";

    public static final String col_1= "PRICE";
    public static final String col_5= "TOTAL_PRICE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("create table " + Table_name +" (COMPANY_NAME TEXT,SYMBOL TEXT,SHARES TEXT,PRICE TEXT,TOTAL_PRICE TEXT)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(sqLiteDatabase);

    }

    public boolean insert_data(String name, String smb , String stock, String price,String q){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col_2,name);
        cv.put(col_3,smb);
        cv.put(col_4,stock);
        cv.put(col_1,price);
        cv.put(col_5,q);
        long result=db.insert(Table_name,null,cv);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_name,null);
        return res;
    }
    public boolean updateData(String name,String smb,String stock,String price,String q) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,smb);
        contentValues.put(col_4,stock);
        contentValues.put(col_1,price);
        contentValues.put(col_5,q);
        db.update(Table_name, contentValues, "COMPANY_NAME = ?",new String[] { name});
        return true;
    }
    public boolean updatePrice(String smb,String price,String q)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,price);
        contentValues.put(col_5,q);
        db.update(Table_name, contentValues, "SYMBOL = ?",new String[] { smb});
        return true;
    }

    public Cursor getPriceData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select SYMBOL,SHARES,PRICE,TOTAL_PRICE from "+Table_name,null);
        return res;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name, "COMPANY_NAME = ?",new String[] {id});
    }

}
