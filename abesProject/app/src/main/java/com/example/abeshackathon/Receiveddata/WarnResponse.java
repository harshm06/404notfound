package com.example.abeshackathon.Receiveddata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarnResponse {

    @SerializedName("warn")
    @Expose
    private Integer warn;

    public Integer getWarn() {
        return warn;
    }

    public void setWarn(Integer warn) {
        this.warn = warn;
    }
}
