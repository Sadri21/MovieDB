package com.sadriapp.moviedb.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.RecyclerView
import com.sadriapp.moviedb.R
import com.sadriapp.moviedb.config.GlobalConfig.IMAGE_URL
import com.sadriapp.moviedb.config.GlobalConfig.picassoBuilder
import com.sadriapp.moviedb.databinding.ItemReviewerBinding
import com.sadriapp.moviedb.model.review.ReviewData
import java.text.SimpleDateFormat

class ReviewAdapter(val context: Context, val isLimited: Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listReview = arrayListOf<ReviewData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_reviewer, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (isLimited) {
            if (listReview.size < 3) {
                listReview.size
            } else {
                3
            }
        } else {
            listReview.size
        }
    }

    fun addItem(reviewData: ReviewData) {
        listReview.add(reviewData)
        notifyItemInserted(listReview.size - 1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ReviewViewHolder -> {
                holder.bind(listReview[position])
            }
        }
    }

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemReviewerBinding.bind(itemView)

        @SuppressLint("SimpleDateFormat")
        fun bind(review: ReviewData) {
            val profileImageURL = "${IMAGE_URL}w200/${review.getAuthorDetails()?.getAvatarPath()}"
            context.picassoBuilder().load(profileImageURL).placeholder(R.drawable.people_placeholder).into(binding.imgProfileReview)
            binding.tvProfileName.text = review.getAuthor()
            binding.tvProfileCount.text = "${review.getAuthorDetails()?.getRating() ?: 0}"
            val simpleDateFormatServer = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val date = review.getUpdatedAt()?.let { simpleDateFormatServer.parse(it) }
            val simpleDateFormatUser = SimpleDateFormat("yyyy-MM-dd, HH:mm:ss")
            val dateString = review.getUpdatedAt()?.let { date?.let { t -> simpleDateFormatUser.format(t) } }
            binding.tvReviewDate.text = dateString
            binding.tvReviewContent.text = review.getContent()?.parseAsHtml()

        }
    }
}