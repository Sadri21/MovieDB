package com.sadriapp.moviedb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadriapp.moviedb.config.GlobalConfig.scrollListener
import com.sadriapp.moviedb.config.GlobalConfig.showToast
import com.sadriapp.moviedb.databinding.ActivityReviewListBinding
import com.sadriapp.moviedb.view.adapter.ReviewAdapter
import com.sadriapp.moviedb.viewModel.RequestViewModel

class ReviewListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityReviewListBinding
    private val viewModel = RequestViewModel()
    var isLoading = false
    lateinit var adapter : ReviewAdapter
    private var movieID : Int = 0
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        movieID = intent.getIntExtra("movie_id", 0)
        binding.rvReview.layoutManager = LinearLayoutManager(this)
        adapter = ReviewAdapter(this, false)
        binding.rvReview.scrollListener(true) {
            if(!isLoading) {
                page += 1
                viewModel.loadReviewList(page, movieID)
            }
        }
        binding.backButton.setOnClickListener {
            finish()
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.errorMessage.observe(this) {
            showToast(it)
        }
        viewModel.isLoading.observe(this) {
            isLoading = it
        }
        viewModel.reviews.observe(this) {
            if (page == 1) {
                binding.rvReview.adapter = adapter
            }
            for (review in it) {
                adapter.addItem(review)
            }
        }
        viewModel.loadReviewList(page, movieID)
    }
}