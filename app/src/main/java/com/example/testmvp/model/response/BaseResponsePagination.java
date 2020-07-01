package com.example.testmvp.model.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BaseResponsePagination<T> extends BaseResponse<T> {
    //deleted soon
    @SerializedName("current_page")
    private int currentPage;
    @SerializedName("first_page_url")
    private String firstPageUrl;
    private int from;
    @SerializedName("last_page")
    private int lastPage;
    @SerializedName("last_page_url")
    private String lastPageUrl;
    @SerializedName("next_page_url")
    private String nextPageUrl;
    private String path;
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("prev_page_url")
    private String prevPageUrl;
    private int to;
    private int total;

}
