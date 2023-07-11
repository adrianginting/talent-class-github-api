package com.adrian.talentclassgithubapi.api

import com.adrian.talentclassgithubapi.data.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TalentHubGitHubUserApiService {

    @GET("users")
    fun getListUsers() : Call<List<UserGitHubModel>>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<UserDetail>

    @GET("users/{username}/followers")
    fun getUserFollowers(@Path("username") username: String): Call<UserDetail>
}