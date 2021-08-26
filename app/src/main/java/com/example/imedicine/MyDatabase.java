package com.example.imedicine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper{

    public static String DATABASE_NAME = "imedicine.db";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE iMedicine (id INTEGER PRIMARY KEY AUTOINCREMENT, mName TEXT, mDate TEXT, mTime TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Medicines> getAllMeds() {
        ArrayList<Medicines> myList = new ArrayList<>();
        String query = "SELECT * FROM iMedicine";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()) {
            int allMedId = cursor.getInt(0);
            String allMedNames = cursor.getString(1);
            String allMedDates = cursor.getString(2);
            String allMedTimes = cursor.getString(3);
            Medicines medicines = new Medicines(allMedId, allMedNames, allMedDates, allMedTimes);
            myList.add(medicines);
        }
        cursor.close();
        return myList;
    }
}
