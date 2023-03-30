package com.sadriapp.moviedb.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sadriapp.moviedb.R
import com.sadriapp.moviedb.config.GlobalConfig.IMAGE_URL
import com.sadriapp.moviedb.config.GlobalConfig.picassoBuilder
import com.sadriapp.moviedb.databinding.ItemVideoBinding
import com.sadriapp.moviedb.model.movie.MovieData
import com.sadriapp.moviedb.view.MovieDetailActivity

class MovieListAdapter(var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listMovie = arrayListOf<MovieData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                holder.bind(listMovie[position])
            }
        }
    }


    fun addItem(movieData: MovieData) {
        listMovie.add(movieData)
        notifyItemInserted(listMovie.size - 1)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemVideoBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieData) {
            val imgURL = "${IMAGE_URL}w500/${movie.getPosterPath()}"
            context.picassoBuilder().load(imgURL).placeholder(R.drawable.movie_placeholder).fit()
                .centerCrop().into(binding.imgMovie)
            binding.tvMovieTitle.text = movie.getTitle()
            binding.tvMovieDate.text = movie.getReleaseDate()
            binding.tvMovieRate.text = "${movie.getVoteAverage()} (${movie.getVoteCount()})"
            itemView.setOnClickListener {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra("img_banner", movie.getBackdropPath())
                intent.putExtra("img_movie", movie.getPosterPath())
                intent.putExtra("title", movie.getOriginalTitle())
                intent.putExtra("date", movie.getReleaseDate())
                intent.putExtra("rating", "${movie.getVoteAverage()} (${movie.getVoteCount()})")
                intent.putExtra("popularity", movie.getPopularity())
                intent.putExtra("lang", movie.getOriginalLanguage())
                intent.putExtra("discover", movie.getOverview())
                intent.putExtra("movie_id", movie.getId())
                context.startActivity(intent)
            }
        }
    }
}