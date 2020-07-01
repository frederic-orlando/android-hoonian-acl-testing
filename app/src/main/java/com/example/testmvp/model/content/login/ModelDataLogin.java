package com.example.testmvp.model.content.login;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ModelDataLogin {
    @Expose
    private ArrayList<MenuNavbar> menuNavbars;

    @Expose
    private ArrayList<UserAccess> userAccess;

    @Expose
    private String token;
}
