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

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo các bảng trong cơ sở dữ liệu
        String createSanphamTable = "CREATE TABLE IF NOT EXISTS sanpham(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TenSP TEXT, " +
                "Gia TEXT, " +
                "HinhAnh INTEGER);";
        db.execSQL(createSanphamTable);

        String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "dob TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(createUsersTable);

        String createCategoriesTable = "CREATE TABLE IF NOT EXISTS Categories (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "description TEXT);";
        db.execSQL(createCategoriesTable);

        String createProductTable = "CREATE TABLE IF NOT EXISTS Product (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "brand TEXT NOT NULL, " +
                "price REAL NOT NULL, " +
                "stock INTEGER NOT NULL, " +
                "description TEXT, " +
                "image_url TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "category_id INTEGER, " +
                "FOREIGN KEY (category_id) REFERENCES Categories(id));";
        db.execSQL(createProductTable);

        String createCartTable = "CREATE TABLE IF NOT EXISTS Cart (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "product_id INTEGER, " +
                "quantity INTEGER NOT NULL, " +
                "FOREIGN KEY(user_id) REFERENCES Users(id), " +
                "FOREIGN KEY(product_id) REFERENCES Product(id));";
        db.execSQL(createCartTable);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sanpham");
        onCreate(db);
        // Xử lý khi nâng cấp cơ sở dữ liệu (nếu cần)
    }

    // Truy vấn không trả kết quả (CREATE, INSERT, UPDATE, DELETE...)
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    // Truy vấn trả kết quả (SELECT...)
    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
}
