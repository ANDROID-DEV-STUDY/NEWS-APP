package com.kevin.newsapp.ui.main.headline.adapter

import android.support.v7.widget.RecyclerView
import com.kevin.newsapp.BR
import com.kevin.newsapp.data.model.news.NewsArticle
import com.kevin.newsapp.databinding.ItemRecyclerViewHeadlineBinding

class HeadlineRecyclerViewHolder(
        private val binding: ItemRecyclerViewHeadlineBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(article: NewsArticle) {
        with(binding) {
            setVariable(BR.article, article)
            executePendingBindings()
        }
    }

    fun clearAnimation() {
        binding.root.clearAnimation()
    }
}