package com.example.myapplication.data.remote.retrofit

import com.example.myapplication.data.remote.response.ResponseGithubDetail
import com.example.myapplication.data.remote.response.ItemsItem
import com.example.myapplication.data.remote.response.ResponseGithub
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET( "search/users")
    fun getUser(
        @Query("q") q : String
    ) : Call<ResponseGithub>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ) : Call<ResponseGithubDetail>

    @GET("users/{username}/followers" )
    fun getFollowers(
        @Path("username") username : String
    ) : Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username  : String
    ) : Call<List<ItemsItem>>
}