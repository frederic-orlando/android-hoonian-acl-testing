package com.example.testmvp.model.content;

import androidx.fragment.app.Fragment;

import lombok.Data;

@Data
public class ModelTab {
    public ModelTab setTitle(String title) {
        this.title = title;
        return this;
    }

    public ModelTab setImageId(int imageId) {
        this.imageId = imageId;
        return this;
    }

    public ModelTab setFragment(Fragment fragment) {
        this.fragment = fragment;
        return this;
    }

    private String title;
    private int imageId;
    private Fragment fragment;
}
