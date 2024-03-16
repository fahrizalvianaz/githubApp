package com.example.myapplication.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class ResponseGithubDetail {

    @SerializedName("login")
    private String login;

    @SerializedName("followers")
    private Integer followers;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("following")
    private Integer following;

    @SerializedName("name")
    private String name;

    public String getLogin() {
        return login;
    }

    public Integer getFollowers() {
        return followers;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Integer getFollowing() {
        return following;
    }

    public String getName() {
        return name;
    }
}
