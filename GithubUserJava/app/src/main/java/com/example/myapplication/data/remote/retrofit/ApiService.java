package com.example.myapplication.data.remote.retrofit;

import com.example.myapplication.data.remote.response.ResponseGithubDetail;
import com.example.myapplication.data.remote.response.ItemsItem;
import com.example.myapplication.data.remote.response.ResponseGithub;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    Call<ResponseGithub> getUser(
            @Query("q") String q
    );

    @GET("users/{username}")
    Call<ResponseGithubDetail> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    Call<List<ItemsItem>> getFollowers(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    Call<List<ItemsItem>> getFollowing(
            @Path("username") String username
    );
}
