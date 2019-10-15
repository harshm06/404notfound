package com.example.abeshackathon.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.abeshackathon.Apiinterface.Medicalrequest;
import com.example.abeshackathon.Apiinterface.Profiledatarequest;
import com.example.abeshackathon.JsonBody.Medicaldata;
import com.example.abeshackathon.JsonBody.Profiledata;
import com.example.abeshackathon.R;
import com.example.abeshackathon.Receiveddata.Loginresponse;
import com.example.abeshackathon.Retro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Profile extends Fragment {
    TextView name,age,bg,mobileno,email,Address,fdoctor,demail,dmobileno,emergencycontact;


    Gson gson=new Gson();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_profile, container, false);

        name=parentView.findViewById(R.id.name);
        age=parentView.findViewById(R.id.age);
        bg=parentView.findViewById(R.id.bg);
        mobileno=parentView.findViewById(R.id.mobileno);
        email=parentView.findViewById(R.id.email);
        Address=parentView.findViewById(R.id.address);
        fdoctor=parentView.findViewById(R.id.dname);
        demail=parentView.findViewById(R.id.demail);
        dmobileno=parentView.findViewById(R.id.dmobile);
        emergencycontact=parentView.findViewById(R.id.emergencycontacts);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        String string=sharedPreferences.getString("logindata","");
        Log.e("datacheck",string);
        Type type=new TypeToken<Loginresponse>() {
        }.getType();
        Loginresponse loginresponse=gson.fromJson(string,type);
        Profiledatarequest profiledatarequest= Retro.createService(Profiledatarequest.class);
        Call<List<Profiledata>> call=profiledatarequest.requestresponse(loginresponse.getId());
        call.enqueue(new Callback<List<Profiledata>>() {
            @Override
            public void onResponse(Call<List<Profiledata>> call, Response<List<Profiledata>> response) {
                List<Profiledata> profiledata=response.body();
                Log.e("profiledata",gson.toJson(profiledata));
                        name.setText(profiledata.get(0).getName());
                age.setText(profiledata.get(0).getAge());
                bg.setText(profiledata.get(0).getBlood_group());
                mobileno.setText(profiledata.get(0).getMobile_no());
                email.setText(profiledata.get(0).getEmail());
                Address.setText(profiledata.get(0).getAddress());
                fdoctor.setText(profiledata.get(0).getDoctorname());
                demail.setText(profiledata.get(0).getDocemail());
                dmobileno.setText(profiledata.get(0).getDoctorno());
                emergencycontact.setText(profiledata.get(0).getEmergencycontact());
            }

            @Override
            public void onFailure(Call<List<Profiledata>> call, Throwable t) {

            }
        });






        return parentView;
    }
}
