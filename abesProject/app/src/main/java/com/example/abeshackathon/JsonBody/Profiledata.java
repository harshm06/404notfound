package com.example.abeshackathon.JsonBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profiledata {
    @SerializedName("link1__name")
    @Expose
    String name;

    @SerializedName("link1__age")
    @Expose
    String age;

    @SerializedName("link__mobile_no")
    @Expose
    String mobile_no;

    @SerializedName("link1__blood_group")
    @Expose
    String blood_group;

    @SerializedName("link1__email")
    @Expose
    String email;

    @SerializedName("link1__gender")
    @Expose
    String gender;

    @SerializedName("link1__address")
    @Expose
    String address;

    @SerializedName("link1__pre_Health")
    @Expose
    String prehealth;

}
