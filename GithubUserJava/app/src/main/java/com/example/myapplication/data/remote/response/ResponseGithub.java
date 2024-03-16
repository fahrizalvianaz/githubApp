package com.example.myapplication.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGithub {

    @SerializedName("total_count")
    private Integer totalCount;

    @SerializedName("incomplete_results")
    private Boolean incompleteResults;

    @SerializedName("items")
    private List<ItemsItem> items;

    public Integer getTotalCount() {
        return totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public List<ItemsItem> getItems() {
        return items;
    }
}

