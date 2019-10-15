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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    String status;



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getThyroid() {
        return thyroid;
    }

    public void setThyroid(String thyroid) {
        this.thyroid = thyroid;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getToday_date() {
        return today_date;
    }

    public void setToday_date(String today_date) {
        this.today_date = today_date;
    }

    public String getNext_date() {
        return next_date;
    }

    public void setNext_date(String next_date) {
        this.next_date = next_date;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeart_disease() {
        return heart_disease;
    }

    public void setHeart_disease(String heart_disease) {
        this.heart_disease = heart_disease;
    }
}
