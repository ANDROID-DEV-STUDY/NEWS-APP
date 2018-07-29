package com.kevin.newsapp.ui.main.headline.adapter

import android.view.View

interface HeadlineRecyclerViewItemClickListener {

    fun onClick(view: View, url: String)
}