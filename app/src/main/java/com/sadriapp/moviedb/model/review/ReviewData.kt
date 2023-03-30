package com.sadriapp.moviedb.model.review

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ReviewData {
    @SerializedName("author")
    @Expose
    private var author: String? = null

    @SerializedName("author_details")
    @Expose
    private var authorDetails: AuthorDetails? = null

    @SerializedName("content")
    @Expose
    private var content: String? = null

    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("updated_at")
    @Expose
    private var updatedAt: String? = null

    @SerializedName("url")
    @Expose
    private var url: String? = null

    fun getAuthor(): String? {
        return author
    }

    fun setAuthor(author: String?) {
        this.author = author
    }

    fun getAuthorDetails(): AuthorDetails? {
        return authorDetails
    }

    fun setAuthorDetails(authorDetails: AuthorDetails?) {
        this.authorDetails = authorDetails
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }
}