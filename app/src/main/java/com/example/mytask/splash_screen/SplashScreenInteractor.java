package com.example.mytask.splash_screen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.mytask.database.DatabaseContract;
import com.example.mytask.utility.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SplashScreenInteractor implements ISplashScreenInteractor {

    private Context mContext;
    private SplashScreenPresenter mSplashScreenPresenter;

    public SplashScreenInteractor(Context mContext, SplashScreenPresenter mSplashScreenPresenter) {
        this.mContext = mContext;
        this.mSplashScreenPresenter = mSplashScreenPresenter;
    }

    @Override
    public boolean checkPermissions() {
        return false;
    }

    @Override
    public boolean storeData(JSONObject jsonObject) {
        ContentValues values = new ContentValues();
        try {
            JSONObject info = jsonObject.getJSONObject("info");

            values.put(DatabaseContract.COLUMN_seed, info.optString("seed"));
            values.put(DatabaseContract.COLUMN_result2, info.optString("result"));
            values.put(DatabaseContract.COLUMN_page, info.optString("page"));
            values.put(DatabaseContract.COLUMN_version, info.optString("version"));
            Utility.getDatabase().insert(DatabaseContract.TABLE_NAME2, null, values);

            JSONArray CandidateJsonArray = jsonObject.getJSONArray("results");
            int callSize = CandidateJsonArray.length();
            for (int index = 0; index < callSize; index++) {
                values.clear();
                JSONObject object = CandidateJsonArray.getJSONObject(index);

                JSONObject name_object = object.optJSONObject("name");
                values.put(DatabaseContract.COLUMN_title, name_object.optString("title"));
                values.put(DatabaseContract.COLUMN_first, name_object.optString("first"));
                values.put(DatabaseContract.COLUMN_last, name_object.optString("last"));

                values.put(DatabaseContract.COLUMN_gender, object.optString("gender"));

                JSONObject location_object = object.optJSONObject("location");
                values.put(DatabaseContract.COLUMN_city, location_object.optString("city"));
                values.put(DatabaseContract.COLUMN_state, location_object.optString("state"));
                values.put(DatabaseContract.COLUMN_country, location_object.optString("country"));

                JSONObject dob_object = object.optJSONObject("dob");
                values.put(DatabaseContract.COLUMN_dob, dob_object.optString("date"));
                values.put(DatabaseContract.COLUMN_age, dob_object.optString("age"));

                JSONObject contact_object = object.optJSONObject("registered");
                values.put(DatabaseContract.COLUMN_registration_date, contact_object.optString("date"));

                values.put(DatabaseContract.COLUMN_cell, object.optString("cell"));
                values.put(DatabaseContract.COLUMN_phone, object.optString("phone"));
                values.put(DatabaseContract.COLUMN_email, object.optString("email"));

                JSONObject picture_object = object.optJSONObject("picture");
                values.put(DatabaseContract.COLUMN_pic, picture_object.optString("large"));

                Utility.getDatabase().insert(DatabaseContract.TABLE_NAME3, null, values);
            }
            return true;
        } catch (JSONException e) {
            Log.d("ERROR", e.toString());
            return false;
        }
    }

    public void insertImage(String path, String id) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), path);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            final int COMPRESSION_QUALITY = 100;
            if (bitmap == null) {
                Log.d("data", "No image found");
            } else {
                bitmap.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY, stream);
                byte image[] = stream.toByteArray();

                int result;
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseContract.COLUMN_IMAGE1, image);

                result = Utility.getDatabase().update(DatabaseContract.TABLE_NAME3,
                        contentValues,
                        DatabaseContract.COLUMN_ID3 + " =? ",
                        new String[]{id});
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Cursor fetchImageData() {
        return Utility.getDatabase().rawQuery("SELECT Id,picture_url FROM "
                + DatabaseContract.TABLE_NAME3 + " WHERE image IS NULL", null);
    }

    @Override
    public Cursor checkCount() {
        return Utility.getDatabase().rawQuery("SELECT Id FROM "
                + DatabaseContract.TABLE_NAME3, null);
    }
}
