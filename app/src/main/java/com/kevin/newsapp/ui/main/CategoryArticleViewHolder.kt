package com.kevin.newsapp.ui.main

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kevin.newsapp.R
import com.kevin.newsapp.data.model.Article
import com.kevin.newsapp.extensions.inflate
import com.kevin.newsapp.extensions.loadImage
import com.kevin.newsapp.extensions.snackbar
import kotlinx.android.synthetic.main.item_article.view.*

class CategoryArticleViewHolder constructor(
        parent : ViewGroup
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_article)) {

    private val source = itemView.item_source
    private val title = itemView.item_title
    private val datetime = itemView.item_datetime
    private val image = itemView.item_image

    fun bind(article : Article) {
        source.text = article.source.name
        title.text = article.title
        datetime.text = article.publishedAt // TODO Custom date time
        image.loadImage(article.urlToImage)
        itemView.setOnClickListener { it.snackbar(article.url) }
    }
}