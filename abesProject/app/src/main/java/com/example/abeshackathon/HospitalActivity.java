package com.example.abeshackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.abeshackathon.Apiinterface.Hospitalrequest;
import com.example.abeshackathon.Apiinterface.Medicalrequest;
import com.example.abeshackathon.Receiveddata.Hospitaldataresponse;
import com.example.abeshackathon.Receiveddata.Medicaldatarersponse;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalActivity extends AppCompatActivity {

    Gson gson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        Hospitalrequest hospitalrequest= Retro.createService(Hospitalrequest.class);

        Call<List<Hospitaldataresponse>> call=hospitalrequest.requestresponse();
        call.enqueue(new Callback<List<Hospitaldataresponse>>() {
            @Override
            public void onResponse(Call<List<Hospitaldataresponse>> call, Response<List<Hospitaldataresponse>> response) {
                List<Hospitaldataresponse> hospitaldataresponses=response.body();
                Log.e("medicaldta",gson.toJson(hospitaldataresponses));
            }

            @Override
            public void onFailure(Call<List<Hospitaldataresponse>> call, Throwable t) {

            }
        });
    }
}
