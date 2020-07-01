package com.example.testmvp.model.content.users;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class User {
    @Expose
    private String name;

    @Expose
    private String email;
}
