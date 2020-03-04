package com.example.mytask.MainPage;

import com.example.mytask.data.Candidate_details;
import com.example.mytask.utility.IMvpView;

import java.util.List;

public interface IMainActivityView extends IMvpView {

    void setAdapter(List<Candidate_details> candidate_data);

}
