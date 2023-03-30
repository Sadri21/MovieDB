package com.sadriapp.moviedb.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sadriapp.moviedb.R
import com.sadriapp.moviedb.databinding.ItemCategoryBinding
import com.sadriapp.moviedb.databinding.ItemGenreTokenBinding
import com.sadriapp.moviedb.model.genre.GenreData
import com.sadriapp.moviedb.view.MovieListActivity

class GenreAdapter(var listGenre: List<GenreData>, var context: Context, var from: String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (from == "dashboard") {
            GenreViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
            )
        } else {
            GenreTokenHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_genre_token, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return listGenre.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GenreViewHolder -> {
                holder.bind(listGenre[position])
            }
            is GenreTokenHolder -> {
                holder.bind(listGenre[position])
            }
        }
    }

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCategoryBinding.bind(itemView)


        fun bind(genre: GenreData) {
            binding.tvGenre.text = genre.getName()
            itemView.setOnClickListener {
                val intent = Intent(context, MovieListActivity::class.java)
                intent.putExtra("genre_id", genre.getId())
                intent.putExtra("genre_name", genre.getName())
                context.startActivity(intent)
            }
        }
    }

    inner class GenreTokenHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingToken = ItemGenreTokenBinding.bind(itemView)

        fun bind(genre: GenreData) {
            bindingToken.tvGenreToken.text = genre.getName()
        }
    }

}