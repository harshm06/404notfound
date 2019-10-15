package com.example.abeshackathon.Apiinterface;

import com.example.abeshackathon.JsonBody.Medicaldata;
import com.example.abeshackathon.JsonBody.Profiledata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Profiledatarequest {


    @GET("health/profile/")
    Call<List<Profiledata>> requestresponse(@Query("id") String id);
}
