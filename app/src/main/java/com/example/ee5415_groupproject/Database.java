package com.example.ee5415_groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Receiver.db";
    private static final String TABLE_NAME = "receiver_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PROVINCE";
    private static final String COL_5 = "CITY";
    private static final String COL_6 = "HOUR";
    private static final String COL_7 = "MINUTE";
    private static final String COL_8 = "WEATHER";
    private static final String COL_9 = "NEWS";
    private static final String COL_10 = "COVID";
    private static final int DATABASED_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASED_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, EMAIL TEXT, PROVINCE TEXT, CITY TEXT, HOUR TEXT, MINUTE TEXT, " +
                "WEATHER TEXT, NEWS TEXT, COVID TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert a record to the database
    public boolean insertData(String name, String email, String province,
                              String city, String hour, String minute,
                              boolean weather, boolean news, boolean covid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, province);
        contentValues.put(COL_5, city);
        contentValues.put(COL_6, hour);
        contentValues.put(COL_7, minute);
        if (weather) {
            contentValues.put(COL_8, "1");
        } else {
            contentValues.put(COL_8, "0");
        }
        if (news) {
            contentValues.put(COL_9, "1");
        } else {
            contentValues.put(COL_9, "0");
        }
        if (covid) {
            contentValues.put(COL_10, "1");
        } else {
            contentValues.put(COL_10, "0");
        }
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String name, String email, String province,
                              String city, String hour, String minute,
                              boolean weather, boolean news, boolean covid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, province);
        contentValues.put(COL_5, city);
        contentValues.put(COL_6, hour);
        contentValues.put(COL_7, minute);
        if (weather) {
            contentValues.put(COL_8, "1");
        } else {
            contentValues.put(COL_8, "0");
        }
        if (news) {
            contentValues.put(COL_9, "1");
        } else {
            contentValues.put(COL_9, "0");
        }
        if (covid) {
            contentValues.put(COL_10, "1");
        } else {
            contentValues.put(COL_10, "0");
        }
        db.update(TABLE_NAME, contentValues, "NAME = ?", new String[]{name});
        return true;
    }

    public void deleteData (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "NAME = ?", new String[] {name});
    }

    public Cursor queryAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }

}
