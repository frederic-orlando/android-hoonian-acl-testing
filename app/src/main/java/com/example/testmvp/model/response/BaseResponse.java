package com.example.testmvp.model.response;

import com.example.testmvp.model.content.ModelStatus;

import lombok.Data;


@Data
public class BaseResponse<T> extends connection.rxconnection.model.BaseResponse<T> {
    private ModelStatus status;
}
