package com.example.abeshackathon.Apiinterface;

import com.example.abeshackathon.Receiveddata.Hospitaldataresponse;
import com.example.abeshackathon.Receiveddata.Medicaldatarersponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Hospitalrequest {

    @GET("health/hospital/")
    Call<List<Hospitaldataresponse>> requestresponse();
}
