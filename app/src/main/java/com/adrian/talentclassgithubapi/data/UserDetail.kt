package com.adrian.talentclassgithubapi.data

import com.google.gson.annotations.SerializedName

data class UserDetail(
    val login: String?,
    val id: Long?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    val name: String?,
    val company: String?,
    val location: String?,
    val bio: String?,
    val followers: Int?,
    val following: Int?,
    @SerializedName("public_repos")
    val publicRepos: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)
