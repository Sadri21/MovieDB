package com.sadriapp.moviedb.config

import com.sadriapp.moviedb.config.GlobalConfig.API_KEY
import com.sadriapp.moviedb.config.GlobalConfig.BASE_URL
import com.sadriapp.moviedb.model.genre.GenreModel
import com.sadriapp.moviedb.model.movie.MovieDetail
import com.sadriapp.moviedb.model.movie.MovieModel
import com.sadriapp.moviedb.model.review.ReviewModel
import com.sadriapp.moviedb.model.trailer.TrailerModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class RetrofitConfig {

    private fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): RequestInterface = getRetrofit().create(RequestInterface::class.java)


}

interface RequestInterface {
    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") api_key: String = API_KEY
    ): Call<GenreModel>

    @GET("discover/movie")
    fun getMovieList(
        @Query("api_key") api_key: String = API_KEY,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("with_genres") genres: Int
    ): Call<MovieModel>

    @GET("movie/{movie_id}/reviews")
    fun getReviewList(
        @Path("movie_id") movieID: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int
    ): Call<ReviewModel>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieID: Int,
        @Query("api_key") api_key: String = API_KEY
    ): Call<MovieDetail>

    @GET("movie/{movie_id}/videos")
    fun getVideosList(
        @Path("movie_id") movieID: Int,
        @Query("api_key") api_key: String = API_KEY
    ): Call<TrailerModel>
}