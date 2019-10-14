package com.example.abeshackathon.Receiveddata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loginresponse {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("id")
    @Expose
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
