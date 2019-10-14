package com.example.abeshackathon.Receiveddata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PanicResponse {

    @SerializedName("link__age")
    @Expose
    private Integer linkAge;
    @SerializedName("link__bloud_group")
    @Expose
    private String linkBloudGroup;
    @SerializedName("link__address")
    @Expose
    private String linkAddress;
    @SerializedName("link__doc_no")
    @Expose
    private String linkDocNo;
    @SerializedName("link__doc_name")
    @Expose
    private String linkDocName;
    @SerializedName("link__emergency_contact")
    @Expose
    private String linkEmergencyContact;

    public Integer getLinkAge() {
        return linkAge;
    }

    public void setLinkAge(Integer linkAge) {
        this.linkAge = linkAge;
    }

    public String getLinkBloudGroup() {
        return linkBloudGroup;
    }

    public void setLinkBloudGroup(String linkBloudGroup) {
        this.linkBloudGroup = linkBloudGroup;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getLinkDocNo() {
        return linkDocNo;
    }

    public void setLinkDocNo(String linkDocNo) {
        this.linkDocNo = linkDocNo;
    }

    public String getLinkDocName() {
        return linkDocName;
    }

    public void setLinkDocName(String linkDocName) {
        this.linkDocName = linkDocName;
    }

    public String getLinkEmergencyContact() {
        return linkEmergencyContact;
    }

    public void setLinkEmergencyContact(String linkEmergencyContact) {
        this.linkEmergencyContact = linkEmergencyContact;
    }
}
