package com.kevin.newsapp.ui.main.fragment.headline.childfragment.top.adapter

import android.support.v7.widget.RecyclerView
import com.kevin.newsapp.BR
import com.kevin.newsapp.data.model.news.NewsArticle
import com.kevin.newsapp.databinding.ItemRecyclerViewTopBinding

class TopRecyclerViewHolder(
        private val binding: ItemRecyclerViewTopBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(article: NewsArticle) {
        with(binding) {
            setVariable(BR.article, article)
            executePendingBindings()
        }
    }
}