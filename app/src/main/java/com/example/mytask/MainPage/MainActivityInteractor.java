package com.example.mytask.MainPage;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.mytask.database.DatabaseContract;
import com.example.mytask.utility.Utility;

public class MainActivityInteractor implements IMainActivityInteractor {

    @Override
    public Cursor fetchCandidateDate() {
        return Utility.getDatabase().rawQuery("SELECT * FROM Candidate_details", null);
    }

    @Override
    public long UpdateStatus(int id, String text) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.COLUMN_status, text);
        long a = Utility.getDatabase().update(DatabaseContract.TABLE_NAME3
                , values
                , DatabaseContract.COLUMN_ID3 + " = ? "
                , new String[]{String.valueOf(id)});

        return a;
    }

    @Override
    public byte[] readImage(String id) {

        Cursor cur = Utility.getDatabase().rawQuery("SELECT image FROM "
                        + DatabaseContract.TABLE_NAME3
                        + " WHERE "
                        + DatabaseContract.COLUMN_ID3 + " = ? "
                , new String[]{String.valueOf(id)});
        int a = cur.getCount();
        byte[] image = new byte[0];
        cur.moveToFirst();
        if (cur != null && a != 0) {
            image = cur.getBlob(cur.getColumnIndex(DatabaseContract.COLUMN_IMAGE1));
        }
        return image;
    }


}
