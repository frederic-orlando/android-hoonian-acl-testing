package com.example.testmvp.model.content.login;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class MenuNavbar {

    @Expose
    private String mobileMenuDesc;
    @Expose
    private int mobileMenuId;
    @Expose
    private long sequenceNo;

    public MenuNavbar(String mobileMenuDesc, int mobileMenuId, long sequenceNo) {
        this.mobileMenuDesc = mobileMenuDesc;
        this.mobileMenuId = mobileMenuId;
        this.sequenceNo = sequenceNo;
    }
}
