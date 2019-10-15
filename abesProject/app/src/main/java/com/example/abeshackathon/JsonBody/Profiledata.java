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

    @SerializedName("link1__family_doctor")
    @Expose
    String familydoctor;

    @SerializedName("link1__doc_no")
    @Expose
    String doctorno;

    @SerializedName("link1__doc_name")
    @Expose
    String doctorname;

    @SerializedName("link1__doc_email")
    @Expose
    String docemail;

    public String getFamilydoctor() {
        return familydoctor;
    }

    public void setFamilydoctor(String familydoctor) {
        this.familydoctor = familydoctor;
    }

    public String getDoctorno() {
        return doctorno;
    }

    public void setDoctorno(String doctorno) {
        this.doctorno = doctorno;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDocemail() {
        return docemail;
    }

    public void setDocemail(String docemail) {
        this.docemail = docemail;
    }

    public String getEmergencycontact() {
        return emergencycontact;
    }

    public void setEmergencycontact(String emergencycontact) {
        this.emergencycontact = emergencycontact;
    }

    @SerializedName("link1__emergency_contact")
    @Expose
    String emergencycontact;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrehealth() {
        return prehealth;
    }

    public void setPrehealth(String prehealth) {
        this.prehealth = prehealth;
    }
}
