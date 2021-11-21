package com.example.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Tienda.dB";

    public DBHelper(Context context) {
        super(context, "Tienda.dB", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (username TEXT primary key, password TEXT )");
        MyDB.execSQL("create Table post (postTitle TEXT primary key, username TEXT, postDescription TEXT, inputPrecio TEXT, imagen BLOB)");
    }



    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users",null,contentValues);

        if (result == -1) return false;
        else {
            return true;
        }
    }

    public boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[] {username});

        if (cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?",new String[] {username,password});

        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}
