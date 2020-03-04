package com.example.mytask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.example.mytask.database.DatabaseContract.DATABASE_NAME;
import static com.example.mytask.database.DatabaseContract.DATABASE_VERSION;
import static com.example.mytask.database.DatabaseContract.DB_LOCATION;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, DB_LOCATION + File.separator + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.ImageDetails.CREATE_TABLE);
        db.execSQL(DatabaseContract.ImageDetails.CREATE_TABLE2);
        db.execSQL(DatabaseContract.ImageDetails.CREATE_TABLE3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertImage(byte[] image) {
        ContentValues values = new ContentValues();
        values.put("image", image);
    }

    public boolean readImage() {
        boolean val = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from imagedetails", null);
        int a = cur.getCount();
        if (cur != null && a != 0) {
            val = true;
        } else
            val = false;
        return val;
    }


    public void insertBitmap(Bitmap bm) {

        // Convert the image into byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, out);
        byte[] buffer = out.toByteArray();
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try {
            values = new ContentValues();
            values.put("image", buffer);
            values.put("imageId", "1");
            // Insert Row
            long i = db.insert(DatabaseContract.TABLE_NAME1, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
    }


}
