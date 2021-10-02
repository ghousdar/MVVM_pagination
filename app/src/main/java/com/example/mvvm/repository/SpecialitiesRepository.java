package com.example.mvvm.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.paginationactivity.Speciality;
import com.example.mvvm.retrofitclass.RetrofitInstance;
import com.example.mvvm.retrofitclass.webApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialitiesRepository {

    private ArrayList<Speciality> users = new ArrayList<>();
    private MutableLiveData<ModelSpeciality> mutableLiveData = new MutableLiveData<ModelSpeciality>();
    private Application application;


    public SpecialitiesRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<ModelSpeciality> getMutableLiveDataSpeciality(int currentPage) {
        webApi apiService = RetrofitInstance.getClient(application.getApplicationContext()).create(webApi.class);

        Map<String, String> field = new HashMap<>();
        field.put("pageno", String.valueOf(currentPage));

        Call<ModelSpeciality> getbank = apiService.MODELSPECIALITY_CALL(field);
        getbank.enqueue(new Callback<ModelSpeciality>() {
            @Override
            public void onResponse(Call<ModelSpeciality> call, Response<ModelSpeciality> response) {


                ModelSpeciality modelSpeciality = response.body();

                mutableLiveData.setValue(response.body());


                //  Toast.makeText(application.getApplicationContext(), ""+response.body().getSpecialities().toString(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<ModelSpeciality> call, Throwable t) {

                Log.d("ListSize", " - > Error    " + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
