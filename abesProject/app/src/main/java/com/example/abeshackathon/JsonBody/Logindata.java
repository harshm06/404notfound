package com.example.abeshackathon.JsonBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logindata {

    @SerializedName("username")
    @Expose
    String username;

    @SerializedName("password")
    @Expose
    String password;


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
