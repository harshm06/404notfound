package com.example.abeshackathon.Receiveddata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hospitalbuttonresponse {


    @SerializedName("status")
    @Expose
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
