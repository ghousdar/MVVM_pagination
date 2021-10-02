package com.example.mvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.mvvm.ViewModel.MainViewModel;
import com.example.mvvm.retrofitclass.Bankpojo;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView userbankrecycle;
    private MainViewModel mainViewModel;
    Bankrecycleadapter bankrecycleadapter;

    SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userbankrecycle = findViewById(R.id.userbankrecycle);
        swipeRefresh = findViewById(R.id.swiperefresh);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getUserList();

        swipeRefresh.setOnRefreshListener(() -> {
            getUserList();
        });
    }

    private void getUserList() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllUsers().observe(this, new Observer<List<Bankpojo>>() {
            @Override
            public void onChanged(@Nullable List<Bankpojo> userList) {
                swipeRefresh.setRefreshing(false);
                setRecyclerView(userList);
            }
        });
    }

    private void setRecyclerView(List<Bankpojo> userList) {

        bankrecycleadapter = new Bankrecycleadapter(getApplicationContext(),userList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            userbankrecycle.setLayoutManager(new LinearLayoutManager(this));
        } else {
            userbankrecycle.setLayoutManager(new GridLayoutManager(this, 4));
        }
        userbankrecycle.setItemAnimator(new DefaultItemAnimator());
        userbankrecycle.setAdapter(bankrecycleadapter);
        bankrecycleadapter.notifyDataSetChanged();
    }
}