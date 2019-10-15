package com.example.abeshackathon;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abeshackathon.JsonBody.Medicaldata;

import java.util.List;

public class MedicalAdapter extends RecyclerView.Adapter<MedicalAdapter.ViewHolder>{

    List<Medicaldata> medicaldata;
    Context context;

    public MedicalAdapter(List<Medicaldata> medicaldata, Context context) {
        this.medicaldata = medicaldata;
        this.context = context;
    }

    @Override
    public MedicalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cardmedicalrecycle, parent, false);
        return new MedicalAdapter.ViewHolder(listItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView fromdate,nextdate,medicines,height,weight,bp,thyroid,sugar,allinone;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            fromdate=itemView.findViewById(R.id.vdate);
            nextdate=itemView.findViewById(R.id.ndate);
            height=itemView.findViewById(R.id.height);
             weight =itemView.findViewById(R.id.weight);
             bp=itemView.findViewById(R.id.bloodpressure);
            sugar=itemView.findViewById(R.id.sugar);
            medicines=itemView.findViewById(R.id.medicines);
            thyroid=itemView.findViewById(R.id.thyroid);
            allinone=itemView.findViewById(R.id.allinone);
            cardView=itemView.findViewById(R.id.cardappoint);
            allinone.setVisibility(View.GONE);


        }
    }

    @Override
    public int getItemCount() {
        if(medicaldata==null||medicaldata.size()==0)
            return 0;
        else
            return medicaldata .size();
    }

    @Override
    public void onBindViewHolder(final MedicalAdapter.ViewHolder holder, final int position) {

        if(medicaldata.get(position).getStatus().equalsIgnoreCase("insert")){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#E5E5E5"));
        }
        holder.medicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(holder.allinone.getVisibility()==View.GONE) {
                   holder.allinone.setVisibility(View.VISIBLE);
                   holder.allinone.setText(medicaldata.get(position).getMedicines());
               }
               else
                   holder.allinone.setVisibility(View.GONE);
            }
        });
        holder.height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.allinone.getVisibility()==View.GONE) {
                    holder.allinone.setVisibility(View.VISIBLE);
                    holder.allinone.setText(medicaldata.get(position).getHeight());
                }
                else
                    holder.allinone.setVisibility(View.GONE);
            }
        });
        holder.weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.allinone.getVisibility()==View.GONE) {
                    holder.allinone.setVisibility(View.VISIBLE);
                    holder.allinone.setText(medicaldata.get(position).getWeight());
                }
                else
                    holder.allinone.setVisibility(View.GONE);
            }
        });

        holder.thyroid.setText(medicaldata.get(position).getThyroid());
        holder.bp.setText(medicaldata.get(position).getBp());
        holder.nextdate.setText(medicaldata.get(position).getNext_date());
        holder.fromdate.setText(medicaldata.get(position).getToday_date());
        holder.sugar.setText(medicaldata.get(position).getSugar());

    }

}
