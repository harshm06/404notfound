package com.example.abeshackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.abeshackathon.Apiinterface.Hospitalbuttonrequest;
import com.example.abeshackathon.Apiinterface.Hospitalrequest;
import com.example.abeshackathon.Apiinterface.Medicalrequest;
import com.example.abeshackathon.Apiinterface.Warnresetrequest;
import com.example.abeshackathon.Receiveddata.Hospitalbuttonresponse;
import com.example.abeshackathon.Receiveddata.Hospitaldataresponse;
import com.example.abeshackathon.Receiveddata.Medicaldatarersponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalActivity extends AppCompatActivity {

    Gson gson=new Gson();
    Button reset,add;
    Spinner spinner;


//    String[] spinnerContent = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        Hospitalrequest hospitalrequest= Retro.createService(Hospitalrequest.class);
        spinner=findViewById(R.id.spinner);



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

        add=findViewById(R.id.addwarn);
        reset=findViewById(R.id.reset);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Hospitalbuttonrequest hospitalbuttonrequest= Retro.createService(Hospitalbuttonrequest.class);

                Call<Hospitalbuttonresponse> call=hospitalbuttonrequest.requestresponse("19");
                call.enqueue(new Callback<Hospitalbuttonresponse>() {
                    @Override
                    public void onResponse(Call<Hospitalbuttonresponse> call, Response<Hospitalbuttonresponse> response) {
                      Hospitalbuttonresponse hospitaldataresponses=response.body();
                      Log.e("response",gson.toJson(hospitaldataresponses));
                    }

                    @Override
                    public void onFailure(Call<Hospitalbuttonresponse> call, Throwable t) {

                    }
                });



            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Warnresetrequest hospitalbuttonrequest= Retro.createService(Warnresetrequest.class);
                Call<Hospitalbuttonresponse> call=hospitalbuttonrequest.requestresponse("19");
                call.enqueue(new Callback<Hospitalbuttonresponse>() {
                    @Override
                    public void onResponse(Call<Hospitalbuttonresponse> call, Response<Hospitalbuttonresponse> response) {
                        Hospitalbuttonresponse hospitaldataresponses=response.body();
                        Log.e("response",gson.toJson(hospitaldataresponses));

                    }

                    @Override
                    public void onFailure(Call<Hospitalbuttonresponse> call, Throwable t) {

                    }
                });

            }
        });
    }
}
