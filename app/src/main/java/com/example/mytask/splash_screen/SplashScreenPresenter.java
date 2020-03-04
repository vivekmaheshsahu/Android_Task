package com.example.mytask.splash_screen;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mytask.data.Candidate_details;
import com.example.mytask.database.DatabaseContract;
import com.example.mytask.database.DatabaseManager;
import com.example.mytask.database.DbHelper;
import com.example.mytask.network_call.AppController;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SplashScreenPresenter implements ISplashScreenPresenter<ISplashScreen> {

    public ISplashScreen viewSplashScreen;
    public SplashScreenInteractor splashScreenInteractor;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE};

    @Override
    public void initializeDBHelper() {
        DbHelper dbHelper = new DbHelper(viewSplashScreen.getContext().getApplicationContext());
        DatabaseManager.initializeInstance(dbHelper);
        DatabaseManager.getInstance().openDatabase();
    }

    @Override
    public boolean checkPermissions() {
        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(viewSplashScreen.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            getPermissions(listPermissionsNeeded);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void attachView(ISplashScreen view) {
        view.showProgressBar();
        viewSplashScreen = view;
        splashScreenInteractor = new SplashScreenInteractor(viewSplashScreen.getContext(), this);
        if (checkPermissions()) {
            initializeDBHelper();
        }
    }

    @Override
    public void detachView() {
        viewSplashScreen = null;
    }

    @Override
    public void getPermissions(List<String> listPermissionsNeeded) {
        ActivityCompat.requestPermissions((Activity) viewSplashScreen.getContext(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
    }

    @Override
    public void networkCall() {

        Cursor cursor = splashScreenInteractor.checkCount();

        if (cursor.getCount() == 0) {
            // Tag used to cancel the request
            String tag_json_obj = "json_obj_req";

            String url = "https://randomuser.me/api/?results=10";

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, response.toString());
                            splashScreenInteractor.storeData(response);
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    // hide the progress dialog
                }
            });
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        } else {
            Log.d("Data", "Data already present");
        }

    }

    @Override
    public void insertImage(String path, String id) {
        splashScreenInteractor.insertImage(path, id);
    }


    public List<Candidate_details> ImageData() {
        Candidate_details candidate_details;
        candidate_details = new Candidate_details();
        List<Candidate_details> DataList = new ArrayList<>();
        Cursor cursor = splashScreenInteractor.fetchImageData();

        while (cursor.moveToNext()) {
            Candidate_details model = new Candidate_details();
            model.setId(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_ID3)));
            model.setImage_url(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_pic)));
            DataList.add(model);
        }
        return DataList;
    }

}
