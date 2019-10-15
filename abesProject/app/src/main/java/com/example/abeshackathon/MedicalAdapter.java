package com.example.abeshackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abeshackathon.JsonBody.Medicaldata;

import java.util.List;

public class MedicalAdapter extends RecyclerView.Adapter<MedicalAdapter>.ViewHolder {

    List<Medicaldata> medicaldata;
    Context context;

    public MedicalAdapter(List<Medicaldata> medicaldata, Context context) {
        this.medicaldata = medicaldata;
        this.context = context;
    }


}
