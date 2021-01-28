package com.example.is4447.fyp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Declare variables to define name of database
    public static final String DATABASE_NAME = "booking.db";
    public static final String TABLE_NAME = "booking_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "email";
    public static final String COL_3 = "Date";
    public static final String COL_4 = "Time";
    public static final String COL_5 = "Cost";



    /* Database creating tutorial on YouTube https://www.youtube.com/watch?v=kDZES1wtKUY&t=1511s */


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create a table inside the database
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, DATE TEXT, TIME TEXT, COST INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public boolean insertBookingData(String email, String date, String time, String cost ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,email);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,time);
        contentValues.put(COL_5,cost);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

}
