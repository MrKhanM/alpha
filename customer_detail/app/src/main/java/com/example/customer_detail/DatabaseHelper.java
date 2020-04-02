package com.example.customer_detail;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "Customer_Details";
    public static final String TABLE = "customer";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String PHONE = "phone";
    public static final String ROLE = "role";
    public static final String CONTACT = "contactP";



    public DatabaseHelper(@Nullable Context context) {
        super(context, "DATABASE", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS customer(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,address TEXT,phone TEXT,role TEXT,contactP TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public boolean insertData(String name,String address,String phone,String role,String contactP) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(ADDRESS, address);
        contentValues.put(PHONE, phone);
        contentValues.put(ROLE, role);
        contentValues.put(CONTACT, contactP);
        long result = db.insert(TABLE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+TABLE;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public boolean updateData(String id,String name,String Address,String Phone,String role,String contactP) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(ADDRESS, Address);
        contentValues.put(PHONE, Phone);
        contentValues.put(ROLE, role);
        contentValues.put(CONTACT, contactP);
        long result = db.update(TABLE, contentValues, "ID = ?", new String[]{id});

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Integer deleteData(int id) {
        SQLiteDatabase db = getWritableDatabase();

        return  db.delete(TABLE,"id = ?",new String[]{String.valueOf(id)});
    }
}



