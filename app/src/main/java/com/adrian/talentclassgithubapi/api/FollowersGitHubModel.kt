package com.adrian.talentclassgithubapi.api

import com.google.gson.annotations.SerializedName

class FollowersGitHubModel (
    @SerializedName("login") val userName : String? = "",
    @SerializedName("avatar_url") val avatarURL : String? = "",
    val name : String? = "",
)