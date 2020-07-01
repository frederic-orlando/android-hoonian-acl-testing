package com.example.testmvp.model.content.users;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ModelDataUsers {
    @Expose
    private ArrayList<User> users;
}
