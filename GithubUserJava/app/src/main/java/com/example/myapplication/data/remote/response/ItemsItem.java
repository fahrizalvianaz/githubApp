package com.example.myapplication.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class ItemsItem {

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;


    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public ItemsItem(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
}
