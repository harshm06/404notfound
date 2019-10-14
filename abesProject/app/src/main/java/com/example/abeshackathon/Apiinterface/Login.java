package com.example.abeshackathon.Apiinterface;

import com.example.abeshackathon.JsonBody.Logindata;
import com.example.abeshackathon.Receiveddata.Loginresponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Login {

    @POST("health/login/")
    Call<Loginresponse> requestresponse(@Body Logindata logindata);
}
