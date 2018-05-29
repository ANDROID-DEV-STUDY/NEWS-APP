package com.kevin.newsapp.ui.main

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kevin.newsapp.data.model.Article

class CategoryArticleAdapter constructor(
        private val headlines : List<Article>
) : RecyclerView.Adapter<CategoryArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryArticleViewHolder
        = CategoryArticleViewHolder(parent)

    override fun getItemCount(): Int = headlines.size

    override fun onBindViewHolder(holder: CategoryArticleViewHolder, position: Int)
        = headlines[position].let { holder.bind(it) }
}