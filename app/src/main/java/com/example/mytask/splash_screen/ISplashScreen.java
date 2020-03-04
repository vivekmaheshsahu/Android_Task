package com.example.mytask.splash_screen;

import com.example.mytask.utility.IMvpView;

public interface ISplashScreen extends IMvpView {

    void DownloadImage();

    void ChangeActivity();

    void showProgressBar();

    void hideProgressBar();

}
