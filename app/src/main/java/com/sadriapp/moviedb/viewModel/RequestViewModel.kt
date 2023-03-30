package com.sadriapp.moviedb.viewModel

import androidx.lifecycle.MutableLiveData
import com.sadriapp.moviedb.config.RetrofitConfig
import com.sadriapp.moviedb.model.genre.GenreData
import com.sadriapp.moviedb.model.genre.GenreModel
import com.sadriapp.moviedb.model.movie.MovieData
import com.sadriapp.moviedb.model.movie.MovieDetail
import com.sadriapp.moviedb.model.movie.MovieModel
import com.sadriapp.moviedb.model.review.ReviewData
import com.sadriapp.moviedb.model.review.ReviewModel
import com.sadriapp.moviedb.model.trailer.TrailerData
import com.sadriapp.moviedb.model.trailer.TrailerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestViewModel: GlobalViewModel() {
    val genres = MutableLiveData<List<GenreData>>()
    val movies = MutableLiveData<List<MovieData>>()
    val reviews = MutableLiveData<List<ReviewData>>()
    val trailers = MutableLiveData<List<TrailerData>>()

    fun loadGenre() {
        isLoading.postValue(true)
        RetrofitConfig().getService().getGenres().enqueue(object:
            Callback<GenreModel> {
            override fun onResponse(
                call: Call<GenreModel>,
                response: Response<GenreModel>
            ) {
                if (response.isSuccessful) {
                    try {
                        val listGenre = response.body()!!.getGenres()!!
                        genres.postValue(listGenre)
                    } catch (e : Exception) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                } else {
                    errorMessage.postValue("Error get data, Error code : " + response.code())
                }
                isLoading.postValue(false)
            }

            override fun onFailure(call: Call<GenreModel>, t: Throwable) {
                errorMessage.postValue(t.localizedMessage)
                isLoading.postValue(false)
            }

        })
    }

    fun loadListMovie(page: Int, genres: Int) {
        isLoading.postValue(true)
        RetrofitConfig().getService().getMovieList(page = page, genres = genres).enqueue(object:
            Callback<MovieModel> {
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                if (response.isSuccessful) {
                    try {
                        val listMovie = response.body()!!.getResults()!!
                        movies.postValue(listMovie)
                    } catch (e : Exception) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                } else {
                    errorMessage.postValue("Error get data, Error code : " + response.code())
                }
                isLoading.postValue(false)
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                errorMessage.postValue(t.localizedMessage)
                isLoading.postValue(false)
            }

        })
    }

    fun loadReviewList(page: Int, movieID: Int) {
        isLoading.postValue(true)
        RetrofitConfig().getService().getReviewList(movieID = movieID, page = page).enqueue(object:
            Callback<ReviewModel> {
            override fun onResponse(
                call: Call<ReviewModel>,
                response: Response<ReviewModel>
            ) {
                if (response.isSuccessful) {
                    try {
                        val listReview = response.body()!!.getResults()!!
                        reviews.postValue(listReview)
                    } catch (e : Exception) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                } else {
                    errorMessage.postValue("Error get data, Error code : " + response.code())
                }
                isLoading.postValue(false)
            }

            override fun onFailure(call: Call<ReviewModel>, t: Throwable) {
                errorMessage.postValue(t.localizedMessage)
                isLoading.postValue(false)
            }
        })
    }

    fun loadMovieDetail(movieID: Int) {
        isLoading.postValue(true)
        RetrofitConfig().getService().getMovieDetail(movieID = movieID).enqueue(object:
            Callback<MovieDetail> {
            override fun onResponse(
                call: Call<MovieDetail>,
                response: Response<MovieDetail>
            ) {
                if (response.isSuccessful) {
                    try {
                        val listGenre = response.body()!!.getGenres()!!
                        genres.postValue(listGenre)
                    } catch (e : Exception) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                } else {
                    errorMessage.postValue("Error get data, Error code : " + response.code())
                }
                isLoading.postValue(false)
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                errorMessage.postValue(t.localizedMessage)
                isLoading.postValue(false)
            }
        })
    }

    fun loadTrailer(movieID: Int) {
        isLoading.postValue(true)
        RetrofitConfig().getService().getVideosList(movieID = movieID).enqueue(object:
            Callback<TrailerModel> {
            override fun onResponse(
                call: Call<TrailerModel>,
                response: Response<TrailerModel>
            ) {
                if (response.isSuccessful) {
                    try {
                        val listTrailer = response.body()!!.getResults()!!
                        trailers.postValue(listTrailer)
                    } catch (e : Exception) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                } else {
                    errorMessage.postValue("Error get data, Error code : " + response.code())
                }
                isLoading.postValue(false)
            }

            override fun onFailure(call: Call<TrailerModel>, t: Throwable) {
                errorMessage.postValue(t.localizedMessage)
                isLoading.postValue(false)
            }
        })
    }
}