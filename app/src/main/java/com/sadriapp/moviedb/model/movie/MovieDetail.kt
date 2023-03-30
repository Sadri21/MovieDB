package com.sadriapp.moviedb.model.movie

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.sadriapp.moviedb.model.genre.GenreData


class MovieDetail {

    @SerializedName("genres")
    @Expose
    private var genres: List<GenreData>? = null

    fun getGenres(): List<GenreData>? {
        return genres
    }

    fun setGenres(genres: List<GenreData>?) {
        this.genres = genres
    }

}