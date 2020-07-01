package com.example.testmvp.model.content;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ModelMore {
    private String title;
    private ArrayList<ModelMenu> child;
}
