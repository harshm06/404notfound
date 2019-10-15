package com.example.abeshackathon.Apiinterface;

import com.example.abeshackathon.Receiveddata.Hospitalbuttonresponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Warnresetrequest {


    @GET("health/warn_reset/")
    Call<Hospitalbuttonresponse> requestresponse(@Query("id") String id);


}
