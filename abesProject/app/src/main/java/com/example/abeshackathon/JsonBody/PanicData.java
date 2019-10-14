package com.example.abeshackathon.JsonBody;

import com.google.gson.annotations.SerializedName;

public class PanicData {
    @SerializedName("id")
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
