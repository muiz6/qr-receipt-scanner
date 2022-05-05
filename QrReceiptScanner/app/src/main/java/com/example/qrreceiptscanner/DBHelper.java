package com.example.qrreceiptscanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {
    public static final String databaseName = "QR";
    public static final String tableName = "QRData";
    public static final String col1 = "ID";
    public static final String col2 = "data";

    public DBHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + tableName +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,data TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,data);
        long result = db.insert(tableName,null ,contentValues);
        if(result==-1){
            return false;
        }
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName, null);
        return res;
    }

    public List<Map<String, Object>> readAllData() {
        List<Map<String, Object>> data = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", cursor.getInt(cursor.getColumnIndex(col1)));
            map.put("data", cursor.getString(cursor.getColumnIndex(col2)));
            data.add(map);
        }
        return data;
    }
}
