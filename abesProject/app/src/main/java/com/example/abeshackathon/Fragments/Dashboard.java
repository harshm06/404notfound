package com.example.abeshackathon.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.abeshackathon.R;
import com.example.abeshackathon.Receiveddata.Loginresponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static android.content.Context.MODE_PRIVATE;

public class Dashboard extends Fragment {
    Gson gson=new Gson();

    public Dashboard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View parentView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TextView username=parentView.findViewById(R.id.user);
       SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mydata", MODE_PRIVATE);
       String string=sharedPreferences.getString("data","");
       Type type=new TypeToken<Loginresponse>() {
       }.getType();
       Loginresponse loginresponse=gson.fromJson(string,type);
       username.setText("Hello ");

       return parentView;

    }

}
