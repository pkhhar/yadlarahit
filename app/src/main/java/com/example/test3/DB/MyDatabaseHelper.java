package com.example.test3.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.test3.repostory.FurnitureModel;
import com.example.test3.repostory.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private MyDatabaseHelper databaseHelper;
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 2;

    // Table and column names
    private static final String TABLE_NAME = "my_database";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Fname = "fname_title";
    private static final String COLUMN_Lname = "lname_title";
    private static final String COLUMN_Email = "email_title";
    private static final String COLUMN_Password = "password_title";
    private static final String COLUMN_Phone = "phone_title";

    // Constructor
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Create table when the database is created
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

    // Upgrade table schema if necessary
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Add a new user to the database
    public void addUser(String fname, String lname, String email, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_Fname, fname);
        cv.put(COLUMN_Lname, lname);
        cv.put(COLUMN_Email, email);
        cv.put(COLUMN_Password, password);
        cv.put(COLUMN_Phone, phone);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add user", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "User added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Read all data from the database
    public Cursor readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Update an existing user's details in the database
    public void updateUser(String row_id, String fname, String lname, String email, String password, String phone)
    {

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

    // Delete a user from the database
    public void deleteUser(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{row_id});
        if (result > 0) {
            Toast.makeText(context, "User deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to delete user", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete all users from the database
    public void deleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        Toast.makeText(context, "All users deleted successfully", Toast.LENGTH_SHORT).show();
    }

    // Check if an email already exists in the database
    public boolean emailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT 1 FROM " + TABLE_NAME + " WHERE " + COLUMN_Email + " = ?";
            cursor = db.rawQuery(query, new String[]{email});
            boolean exists = (cursor.getCount() > 0);
            cursor.close();
            return exists;
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    // Get the row number by email
    public int getRowNumberByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + COLUMN_Email + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        int rowNumber = -1; // Initialize with a default value indicating user not found
        if (cursor != null && cursor.moveToFirst()) {
            rowNumber = cursor.getInt(0); // Assuming the ID column is the first column in your table
            cursor.close();
        }
        return rowNumber;
    }
}
