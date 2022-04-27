package com.example.databaseq12_practical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String tableName = "appForm";
    public static final String rollCol = "rollno";
    public static final String nameCol = "name";
    public static final String mailCol = "email";
    public static final String phoneCol = "phone";
    public static final String sqlCreateCommand = "CREATE TABLE " + tableName + "(" + rollCol + " TEXT PRIMARY KEY, " + nameCol + " TEXT, " + mailCol + " TEXT, " + phoneCol + " TEXT)";
    public static final String sqlDropCommand = "DROP TABLE IF EXISTS " + tableName;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(sqlDropCommand);
    }

    public boolean insertUserData(String roll, String name, String email, String phone){
        ContentValues cv = new ContentValues();
        cv.put(rollCol,roll);
        cv.put(nameCol,name);
        cv.put(mailCol,email);
        cv.put(phoneCol,phone);
        long result = getWritableDatabase().insert(tableName,null,cv);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean updateUserData(String roll, String name, String email, String phone){
        ContentValues cv = new ContentValues();
        cv.put(nameCol,name);
        cv.put(mailCol,email);
        cv.put(phoneCol,phone);
        String whereClause = rollCol + "=?";
        String rQuery = "SELECT * FROM " + tableName + " WHERE " + rollCol + "=?";
        Cursor cursor = getReadableDatabase().rawQuery(rQuery, new String[]{roll});
        if (cursor.getCount()>0){
            long result = getWritableDatabase().update(tableName, cv, whereClause, new String[]{roll});
            if (result==-1){
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public boolean deleteUserData(String roll){
        String whereClause = rollCol + "=?";
        String rQuery = "SELECT * FROM " + tableName + " WHERE " + rollCol + "=?";
        Cursor cursor = getReadableDatabase().rawQuery(rQuery, new String[]{roll});
        if (cursor.getCount()>0){
            long result = getWritableDatabase().delete(tableName,whereClause, new String[]{roll});
            if (result==-1){
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getData(){
        String rQuery = "SELECT * FROM " + tableName;
        Cursor cursor = getWritableDatabase().rawQuery(rQuery,null);
        return cursor;
    }
}
