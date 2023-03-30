package com.sadriapp.moviedb.model.review

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class AuthorDetails {
    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("username")
    @Expose
    private var username: String? = null

    @SerializedName("avatar_path")
    @Expose
    private var avatarPath: Any? = null

    @SerializedName("rating")
    @Expose
    private var rating: Double? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getAvatarPath(): Any? {
        return avatarPath
    }

    fun setAvatarPath(avatarPath: Any?) {
        this.avatarPath = avatarPath
    }

    fun getRating(): Double? {
        return rating
    }

    fun setRating(rating: Double?) {
        this.rating = rating
    }

}