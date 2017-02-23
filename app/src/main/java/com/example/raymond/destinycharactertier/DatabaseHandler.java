package com.example.raymond.destinycharactertier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Tier.db";

    public static final String TABLE_NAME = "tier_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "Class_Name";
    public static final String COL3 = "Total_Intellect";
    public static final String COL4 = "Total_Discipline";
    public static final String COL5 = "Total_Strength";
    public static final String COL6 = "Tier";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    } // END DatabaseHandler class

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Class_Name TEXT, " +
                "Total_Intellect INTEGER, " +
                "Total_Discipline INTEGER, " +
                "Total_Strength INTEGER, " +
                "Tier INTEGER)") ;
    } // END onCreate class

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    } // END onUpgrade class

    public boolean insertData(String className, String totalIntellect, String totalDiscipline, String totalStrength, String tier) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, className);
        contentValues.put(COL3, totalIntellect);
        contentValues.put(COL4, totalDiscipline);
        contentValues.put(COL5, totalStrength);
        contentValues.put(COL6, tier);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    } // END insertData class

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    } // END  Cursor class
} // END DatabaseHandler superclass