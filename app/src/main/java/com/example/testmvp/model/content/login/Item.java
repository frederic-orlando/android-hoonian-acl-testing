package com.example.testmvp.model.content.login;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class Item {
    @Expose
    private String mobileMenuDesc;
    @Expose
    private int mobileMenuId;

    //Dummy
    public Item(String mobileMenuDesc, int mobileMenuId) {
        this.mobileMenuDesc = mobileMenuDesc;
        this.mobileMenuId = mobileMenuId;
    }
}
