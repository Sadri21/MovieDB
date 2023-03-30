package com.sadriapp.moviedb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.sadriapp.moviedb.R
import com.sadriapp.moviedb.config.GlobalConfig.showToast
import com.sadriapp.moviedb.databinding.ActivityMainBinding
import com.sadriapp.moviedb.view.adapter.GenreAdapter
import com.sadriapp.moviedb.viewModel.RequestViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.rvGenre.layoutManager = GridLayoutManager(this, 2)
        initViewModel()
    }

    private fun initViewModel() {
        val viewModel = RequestViewModel()
        viewModel.errorMessage.observe(this) {
            showToast(it)
        }
        viewModel.genres.observe(this) {
            val adapter = GenreAdapter(it, this, "dashboard")
            binding.rvGenre.adapter = adapter
        }
        viewModel.loadGenre()
    }
}