package com.example.mytask.MainPage;

import android.database.Cursor;

public interface IMainActivityInteractor {

    Cursor fetchCandidateDate();

    public long UpdateStatus(int id, String text);

    public byte[] readImage(String id);


}
