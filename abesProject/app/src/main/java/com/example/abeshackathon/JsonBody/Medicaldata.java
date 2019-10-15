package com.example.abeshackathon.JsonBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medicaldata {

    @SerializedName("final_remark")
    @Expose
    String remark;

    @SerializedName("blood_pressure")
    @Expose
    String bp;

    @SerializedName("sugar")
    @Expose
    String sugar;

    @SerializedName("thyroid")
    @Expose
    String thyroid;

    @SerializedName("medicines")
    @Expose
    String medicines;

    @SerializedName("today_date")
    @Expose
    String today_date;

    @SerializedName("next_date")
    @Expose
    String next_date;

    @SerializedName("height")
    @Expose
    String height;

    @SerializedName("weight")
    @Expose
    String weight;


    @SerializedName("heart_disease")
    @Expose
    String heart_disease;



}
