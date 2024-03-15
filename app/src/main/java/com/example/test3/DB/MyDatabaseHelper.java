package com.example.test3.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.test3.repostory.FurnitureModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "my_database";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Fname = "fname_title";
    private static final String COLUMN_Lname = "lname_title";
    private static final String COLUMN_Email = "email_title";
    private static final String COLUMN_Password = "password_title";
    private static final String COLUMN_Phone = "phone_title";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Fname + " TEXT, " +
                COLUMN_Lname + " TEXT, " +
                COLUMN_Email + " TEXT, " +
                COLUMN_Password + " TEXT, " +
                COLUMN_Phone + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

  public   void addUser(String fname, String lname, String email, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_Fname, fname);
        cv.put(COLUMN_Lname, lname);
        cv.put(COLUMN_Email, email);
        cv.put(COLUMN_Password, password);
        cv.put(COLUMN_Phone,phone);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add user", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "User added successfully", Toast.LENGTH_SHORT).show();
        }
    }

   public Cursor readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

  public   void updateUser(String row_id, String fname, String lname, String email, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_Fname, fname);
        cv.put(COLUMN_Lname, lname);
        cv.put(COLUMN_Email, email);
        cv.put(COLUMN_Password, password);
        cv.put(COLUMN_Phone, phone);
        int result = db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{row_id});
        if (result > 0) {
            Toast.makeText(context, "User updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to update user", Toast.LENGTH_SHORT).show();
        }
    }

  public   void deleteUser(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{row_id});
        if (result > 0) {
            Toast.makeText(context, "User deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to delete user", Toast.LENGTH_SHORT).show();
        }
    }

  public   void deleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        Toast.makeText(context, "All users deleted successfully", Toast.LENGTH_SHORT).show();
    }
}
