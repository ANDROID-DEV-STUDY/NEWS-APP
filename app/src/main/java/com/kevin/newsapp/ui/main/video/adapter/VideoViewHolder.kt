package com.kevin.newsapp.ui.main.video.adapter

import android.support.v7.widget.RecyclerView
import com.kevin.newsapp.data.model.youtube.YoutubeVideo
import com.kevin.newsapp.databinding.ItemRecyclerViewVideoBinding

class VideoViewHolder constructor(
        private val binding: ItemRecyclerViewVideoBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: YoutubeVideo) {

        /*
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
        */
    }
}