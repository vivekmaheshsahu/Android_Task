package com.example.mytask.utility;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadFileService extends IntentService {
    public static final String ACTION_DOWNLOAD = "com.example.dara.myapplication.action.DOWNLOAD";
    private static final String DOWNLOAD_PATH = "com.spartons.androiddownloadmanager_DownloadSongService_Download_path";
    private static final String DESTINATION_PATH = "com.spartons.androiddownloadmanager_DownloadSongService_Destination_path";
    private static final String FILENAME = "com.spartons.androiddownloadmanager_DownloadSongService_Destination_path";
    private String TAG = "SERVICE";
    private String filePath;

    public DownloadFileService() {
        super("DownloadSongService");
    }

    public static Intent getDownloadService(final @NonNull Context callingClassContext, final @NonNull String downloadPath, final @NonNull String destinationPath, final String fileName) {
        return new Intent(callingClassContext, DownloadFileService.class)
                .putExtra(DOWNLOAD_PATH, downloadPath)
                .putExtra(DESTINATION_PATH, destinationPath)
                .putExtra(FILENAME, fileName);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String downloadPath = intent.getStringExtra(DOWNLOAD_PATH);
        String destinationPath = intent.getStringExtra(DESTINATION_PATH);
        String fileName = intent.getStringExtra(FILENAME);
        startDownload(downloadPath, destinationPath);
        // downloadStart(downloadPath,destinationPath);
    }

    private void startDownload(String downloadPath, String destinationPath) {
        Uri uri = Uri.parse(downloadPath); // Path where you want to download file.
        DownloadManager manager = ((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE));
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);  // Tell on which network you want to download file.
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);  // This will show notification on top when downloading the file.
//        request.setTitle("Downloading a file "); // Title for notification.
//        request.setVisibleInDownloadsUi(true);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        filePath = storageDir.getAbsolutePath() + "/" + imageFileName;
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, imageFileName);

        Log.e(TAG, "Last Path segment = " + uri.getLastPathSegment());
        //request.setDestinationInExternalPublicDir(destinationPath, uri.getLastPathSegment());  // Storage directory path
        long id = manager.enqueue(request); // This will start downloading

        //to get the download progress
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor c = manager.query(query);
        int bytesDownloaded, bytesTotal, status = 0, downloadProgress = 0;
        if (c != null && c.moveToFirst()) {
            bytesDownloaded = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            bytesTotal = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
        }
        Log.e(TAG, "Download Started id " + id);
        Intent backIntent = new Intent();
        backIntent.putExtra("downloadId", id);
        backIntent.setAction("com.download.file");
        backIntent.putExtra("filePath", imageFileName);
        sendBroadcast(backIntent);
    }

}