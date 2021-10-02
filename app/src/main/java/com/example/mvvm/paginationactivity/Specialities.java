package com.example.mvvm.paginationactivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvvm.R;
import com.example.mvvm.ViewModel.MainViewModel;
import com.example.mvvm.repository.ModelSpeciality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Specialities extends AppCompatActivity {
    private MainViewModel mainViewModel;
    SwipeRefreshLayout swiperefreshspec;
    SpecialitylistAdapter specialitylistAdapter;
    RecyclerView specialityrecycle;


    int visibleItemCount, totalItemCount = 1;
    int firstVisiblesItems = 0;
    int pageNo = 0;
    int currentPage = 1;
    boolean canLoadMoreData = false;
    List<Speciality>   specialityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialities);

        specialityrecycle = findViewById(R.id.specialityrecycle);
        swiperefreshspec = findViewById(R.id.swiperefreshspec);
        specialityList = new ArrayList<>();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        getSpecialityList();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(Specialities.this, 2, GridLayoutManager.VERTICAL, false);
        specialityrecycle.setLayoutManager(gridLayoutManager);
        specialityrecycle.setHasFixedSize(true);
        specialityrecycle.setAdapter(specialitylistAdapter);


        specialityrecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {

                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    firstVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
                    if (canLoadMoreData) {

                        if ((visibleItemCount + firstVisiblesItems) >= (totalItemCount)) {

                            if (pageNo == currentPage) {

                                 Toast.makeText(Specialities.this, "No more data to load", Toast.LENGTH_SHORT).show();
                                canLoadMoreData = false;
                            } else {
                                currentPage += 1;
                                canLoadMoreData = false;

                            //  getSpecialityList();


                             mainViewModel.getAllSpecialities(currentPage);




                            }
                        }
                    }
                }
            }
        });



        swiperefreshspec.setOnRefreshListener(() -> {
         //  currentPage = 1;
         //  specialityList.clear();

            swiperefreshspec.setRefreshing(false);
         //   getSpecialityList();
        });


    }

    private void getSpecialityList() {
        swiperefreshspec.setRefreshing(true);
        mainViewModel.getAllSpecialities(currentPage).observe(this, (Observer<? super ModelSpeciality>) new Observer<ModelSpeciality>() {
            @Override
            public void onChanged(ModelSpeciality modelSpeciality) {

                pageNo = modelSpeciality.getTotal_pages();
                if (modelSpeciality != null) {


                    if (currentPage == 1) {

                          specialityList = new ArrayList<>(Arrays.asList(modelSpeciality.getSpecialities()));
                     //   setRecyclerView(specialityList);
                        specialitylistAdapter = new SpecialitylistAdapter(getApplicationContext(), specialityList);
                        specialityrecycle.setAdapter(specialitylistAdapter);
                        specialitylistAdapter.notifyDataSetChanged();
                        swiperefreshspec.setRefreshing(false);



                    }
                    else
                        {


                        specialitylistAdapter.addAll(Arrays.asList(modelSpeciality.getSpecialities()));
                        swiperefreshspec.setRefreshing(false);
                    }
                    canLoadMoreData = true;

                }
            }



        });


    }

    private void setRecyclerView(List<Speciality> userList) {

        specialitylistAdapter = new SpecialitylistAdapter(getApplicationContext(), userList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager  gridLayoutManager = new GridLayoutManager(Specialities.this, 2, GridLayoutManager.VERTICAL, false);
            specialityrecycle.setLayoutManager(gridLayoutManager);


        } else {
            specialityrecycle.setLayoutManager(new GridLayoutManager(this, 4));
        }
        specialityrecycle.setItemAnimator(new DefaultItemAnimator());
        specialityrecycle.setAdapter(specialitylistAdapter);
        specialitylistAdapter.notifyDataSetChanged();
    }
}