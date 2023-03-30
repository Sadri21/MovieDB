package com.sadriapp.moviedb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sadriapp.moviedb.R
import com.sadriapp.moviedb.config.GlobalConfig.scrollListener
import com.sadriapp.moviedb.config.GlobalConfig.showToast
import com.sadriapp.moviedb.databinding.ActivityMovieListBinding
import com.sadriapp.moviedb.view.adapter.GenreAdapter
import com.sadriapp.moviedb.view.adapter.MovieListAdapter
import com.sadriapp.moviedb.viewModel.RequestViewModel

class MovieListActivity : AppCompatActivity() {

    lateinit var binding : ActivityMovieListBinding
    var page: Int = 1
    var genre: Int = 0
    private val viewModel = RequestViewModel()
    lateinit var adapter: MovieListAdapter
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        genre = intent.getIntExtra("genre_id", 0)
        val title = intent.getStringExtra("genre_name")
        binding.lblTitle.text = title
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        adapter = MovieListAdapter(this)
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.rvMovies.scrollListener(false) {
            if(!isLoading) {
                page += 1
                viewModel.loadListMovie(page, genre)
            }
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
        viewModel.movies.observe(this) {
            if (page == 1) {
                binding.rvMovies.adapter = adapter
            }
            for (movie in it) {
                adapter.addItem(movie)
            }
        }
        viewModel.loadListMovie(page, genre)
    }

}
