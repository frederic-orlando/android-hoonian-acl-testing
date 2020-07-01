package com.example.testmvp.model.request;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class RequestLogin {
    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private String phoneNo;
}
