package com.example.mvvm.retrofitclass;



import com.example.mvvm.repository.ModelSpeciality;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface webApi {


    @GET("setting/getbank")
    Call<List<Bankpojo>> getBank();

    @FormUrlEncoded
    @POST("get/allSpecialities")
    Call<ModelSpeciality> MODELSPECIALITY_CALL(@FieldMap Map<String, String> fields);

}
