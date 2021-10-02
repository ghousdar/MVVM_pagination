package com.example.mvvm.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.retrofitclass.Bankpojo;
import com.example.mvvm.retrofitclass.RetrofitInstance;
import com.example.mvvm.retrofitclass.webApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ArrayList<Bankpojo> users = new ArrayList<>();
    private MutableLiveData<List<Bankpojo>> mutableLiveData = new MutableLiveData<>();
    private Application application;
    public UserRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<Bankpojo>> getMutableLiveData() {
        webApi apiService = RetrofitInstance.getClient(application.getApplicationContext()).create(webApi.class);
        Call<List<Bankpojo>> getbank = apiService.getBank();
        getbank.enqueue(new Callback<List<Bankpojo>>() {
            @Override
            public void onResponse(Call<List<Bankpojo>> call, Response<List<Bankpojo>> response) {

                List<Bankpojo> bankpojoList = response.body();
                if (bankpojoList != null) {
                    users = (ArrayList<Bankpojo>) bankpojoList;
                    mutableLiveData.setValue(users);
                }
            }
            @Override
            public void onFailure(Call<List<Bankpojo>> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
