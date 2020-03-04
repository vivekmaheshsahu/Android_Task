package com.example.mytask.splash_screen;

import com.example.mytask.data.Candidate_details;
import com.example.mytask.utility.IBasePresenter;

import java.util.List;

public interface ISplashScreenPresenter<v> extends IBasePresenter<v> {

    void getPermissions(List<String> listPermissionsNeeded);

    void initializeDBHelper();

    void networkCall();

    void insertImage(String path, String id);

    boolean checkPermissions();

    public List<Candidate_details> ImageData();

}
