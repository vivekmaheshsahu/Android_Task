package com.example.mytask.splash_screen;

import android.database.Cursor;

import org.json.JSONObject;

public interface ISplashScreenInteractor {

    boolean checkPermissions();

    boolean storeData(JSONObject jsonObject);

    void insertImage(String path, String id);

    public Cursor fetchImageData();

    public Cursor checkCount();

}
