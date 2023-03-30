package com.sadriapp.moviedb.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadriapp.moviedb.R
import com.sadriapp.moviedb.config.GlobalConfig
import com.sadriapp.moviedb.config.GlobalConfig.picassoBuilder
import com.sadriapp.moviedb.config.GlobalConfig.showToast
import com.sadriapp.moviedb.databinding.ActivityMovieDetailBinding
import com.sadriapp.moviedb.view.adapter.GenreAdapter
import com.sadriapp.moviedb.view.adapter.ReviewAdapter
import com.sadriapp.moviedb.view.adapter.TrailerAdapter
import com.sadriapp.moviedb.viewModel.RequestViewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailBinding
    lateinit var adapter : ReviewAdapter
    var movieID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        movieID = intent.getIntExtra("movie_id", 0)
        val imgBanner = intent.getStringExtra("img_banner")
        val imgMovie = intent.getStringExtra("img_movie")
        val titleMovie = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val rating = intent.getStringExtra("rating")
        val popularity = intent.getDoubleExtra("popularity", 0.0)
        val lang = intent.getStringExtra("lang")
        val overview = intent.getStringExtra("discover")
        val imgURL = "${GlobalConfig.IMAGE_URL}w500/${imgMovie}"
        val bannerURL = "${GlobalConfig.IMAGE_URL}original/${imgBanner}"
        picassoBuilder().load(bannerURL).placeholder(R.drawable.movie_placeholder).into(binding.imgBanner)
        picassoBuilder().load(imgURL).placeholder(R.drawable.movie_placeholder).into(binding.imgMovie)
        binding.tvMovieTitle.text = titleMovie
        binding.tvMovieDate.text = date
        binding.tvMovieRate.text = rating
        binding.tvMoviePopularity.text = "$popularity"
        binding.tvMovieLang.text = lang
        binding.tvMovieOverview.text = overview
        binding.rvReview.layoutManager = LinearLayoutManager(this)
        binding.rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTrailer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = ReviewAdapter(this, true)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.tvSeeAll.setOnClickListener {
            val intent = Intent(this, ReviewListActivity::class.java)
            intent.putExtra("movie_id", movieID)
            startActivity(intent)
        }

        initViewModel()
    }

    private fun initViewModel() {
        val viewModel = RequestViewModel()
        viewModel.errorMessage.observe(this) {
            showToast(it)
        }
        viewModel.reviews.observe(this) {
            binding.rvReview.adapter = adapter
            for (review in it) {
                adapter.addItem(review)
            }
        }
        viewModel.genres.observe(this) {
            val adapter = GenreAdapter(it, this, "detail")
            binding.rvGenre.adapter = adapter
        }
        viewModel.trailers.observe(this) { data ->
            val adapter = TrailerAdapter(data.filter { it.getSite() == "YouTube" && (it.getType() == "Teaser" || it.getType() == "Trailer") }, this)
            binding.rvTrailer.adapter = adapter
        }

        viewModel.loadReviewList(1, movieID)
        viewModel.loadMovieDetail(movieID)
        viewModel.loadTrailer(movieID)
    }
}