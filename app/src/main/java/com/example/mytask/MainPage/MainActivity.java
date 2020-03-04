package com.example.mytask.MainPage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytask.R;
import com.example.mytask.data.Candidate_details;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    private RecyclerView mRecyclerView;
    private MainActivityPresenter mainActivityPresenter;
    private Candidate_Adapter candidate_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pending Invitations");
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);
        mainActivityPresenter = new MainActivityPresenter();
        mainActivityPresenter.attachView(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setAdapter(List<Candidate_details> candidate_data) {
        if (candidate_data == null || candidate_data.size() < 1) {
            Log.d("EMPTY", "DATA BLANL");
            return;
        }

        if (candidate_adapter == null) {
            candidate_adapter = new Candidate_Adapter(MainActivity.this, candidate_data, new Candidate_Adapter.OnItemClickListener() {
                @Override
                public void onItemClick(String uniqueId, ImageView profileImage) {
                    Toast.makeText(MainActivity.this, "Id", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setAdapter(candidate_adapter);
        } else {
            candidate_adapter.swapDataList(candidate_data);
            candidate_adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityPresenter.fetchCandidateData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityPresenter.detachView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
