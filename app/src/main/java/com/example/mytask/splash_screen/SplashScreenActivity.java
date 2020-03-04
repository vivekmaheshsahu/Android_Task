package com.example.mytask.splash_screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytask.MainPage.MainActivity;
import com.example.mytask.R;
import com.example.mytask.data.Candidate_details;
import com.example.mytask.database.DbHelper;
import com.example.mytask.utility.DownloadFileService;
import com.example.mytask.utility.Utility;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity implements ISplashScreen {

    DbHelper db;
    String id;
    IntentFilter filter = new IntentFilter("com.download.file");
    View progressOverlay;
    private SplashScreenPresenter presenter;
    BroadcastReceiver fileReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            String TAG = "receivered";
            Log.d(TAG, "onReceive: ");
            if (intent != null && intent.getAction().equals("com.download.file")) {
                String path = intent.getStringExtra("filePath");
                presenter.insertImage(path, id);
            }
        }
    };
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        progressOverlay = findViewById(R.id.progress_overlay);
        presenter = new SplashScreenPresenter();
        db = new DbHelper(this);
        presenter = new SplashScreenPresenter();
        presenter.attachView(this);
        presenter.initializeDBHelper();
        presenter.networkCall();
        DownloadImage();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void DownloadImage() {
        final List<Candidate_details> data = presenter.ImageData();
        int count = data.size();
        if (count != 0) {

//        for(int i=0;i<count; i++) {

            final int finalI = 0;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    String imageLink = data.get(finalI).getImage_url();

                    String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/NEWIMAGE/";
                    String fileName = "image.jpg";
                    id = data.get(finalI).getId();
                    startService(DownloadFileService.getDownloadService(getContext(), imageLink, destination, fileName));
                }
            }, 3000);
            //}

        }
        hideProgressBar();
        ChangeActivity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(fileReceiver, filter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(fileReceiver);
    }

    @Override
    public void ChangeActivity() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    @Override
    public void showProgressBar() {
        Utility.animateView(progressOverlay, View.VISIBLE, 0.4f, 200);
    }

    @Override
    public void hideProgressBar() {
        Utility.animateView(progressOverlay, View.GONE, 0.4f, 200);
    }
}
