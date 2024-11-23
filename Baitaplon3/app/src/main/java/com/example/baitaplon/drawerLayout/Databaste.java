package com.example.baitaplon.drawerLayout;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databaste extends SQLiteOpenHelper {

    public Databaste(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
// truy vấn không trả kết quả :CREATE,INSERT,UPDATE,DELETE....
    public void QueryData(String sql){
       SQLiteDatabase database=getWritableDatabase();
       database.execSQL(sql);
    }
    // truy vấn trả kết quả
    public Cursor GetData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}