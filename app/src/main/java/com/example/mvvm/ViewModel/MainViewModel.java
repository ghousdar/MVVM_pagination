package com.example.mvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.repository.ModelSpeciality;
import com.example.mvvm.repository.SpecialitiesRepository;
import com.example.mvvm.repository.UserRepository;
import com.example.mvvm.retrofitclass.Bankpojo;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private  SpecialitiesRepository specialitiesRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        specialitiesRepository = new SpecialitiesRepository(application);

    }
    public LiveData<List<Bankpojo>> getAllUsers() {
        return userRepository.getMutableLiveData();
    }



    public MutableLiveData<ModelSpeciality> getAllSpecialities(int currentPage){
        return specialitiesRepository.getMutableLiveDataSpeciality(currentPage);
    }
}
