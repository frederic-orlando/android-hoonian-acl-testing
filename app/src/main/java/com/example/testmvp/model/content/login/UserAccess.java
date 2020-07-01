package com.example.testmvp.model.content.login;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

import lombok.Data;

@Data
public class UserAccess {
    @Expose
    private ArrayList<Item> item;
    @Expose
    private String name;

    //Dummy
    public UserAccess(ArrayList<Item> item, String name) {
        this.item = item;
        this.name = name;
    }
}
