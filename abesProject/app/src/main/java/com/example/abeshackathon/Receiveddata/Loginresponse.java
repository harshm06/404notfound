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

    @SerializedName("from_date")
    @Expose
    String fromdate;


    @SerializedName("next_date")
    @Expose
    String nextdate;

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getNextdate() {
        return nextdate;
    }

    public void setNextdate(String nextdate) {
        this.nextdate = nextdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remarks) {
        this.remark = remarks;
    }

    @SerializedName("remark")
    @Expose
    String remark;

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
