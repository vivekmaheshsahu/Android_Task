package com.example.mytask.MainPage;

import android.database.Cursor;

import com.example.mytask.data.Candidate_details;
import com.example.mytask.database.DatabaseContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter implements IMainActivityPresenter<MainActivity> {

    public IMainActivityView iMainActivityView;
    MainActivityInteractor mainActivityInteractor;
    Candidate_details candidate_details;

    @Override
    public void attachView(MainActivity view) {
        iMainActivityView = view;
        mainActivityInteractor = new MainActivityInteractor();
    }

    @Override
    public void detachView() {

    }

    @Override
    public void fetchCandidateData() {
        candidate_details = new Candidate_details();
        List<Candidate_details> DataList = new ArrayList<>();
        Cursor cursor = mainActivityInteractor.fetchCandidateDate();

        while (cursor.moveToNext()) {
            Candidate_details model = new Candidate_details();
            String id = (cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_ID3)));
            byte[] image = mainActivityInteractor.readImage(id);
            if (image != null) {
                model.setImage(image);
            }
            model.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_title)));
            model.setFirst_name(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_first)));
            model.setLast_name(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_last)));
            model.setGender(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_gender)));
            model.setCity(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_city)));
            model.setState(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_state)));
            model.setCountry(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_country)));
            model.setDob(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_dob)));
            model.setAge(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_age)));
            model.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_phone)));
            model.setCell(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_cell)));
            model.setRegistration_date(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_registration_date)));
            model.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_email)));
            model.setId(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_ID3)));
            String data = cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_status));
            if (data != null) {
                model.setStatus(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_status)));
            } else {
                model.setStatus("NA");
            }

            DataList.add(model);
        }
        iMainActivityView.setAdapter(DataList);
    }


}
