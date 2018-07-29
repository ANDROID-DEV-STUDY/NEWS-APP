package com.kevin.newsapp.ui.main.headline.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.android.databinding.library.baseAdapters.BR
import com.kevin.newsapp.R
import com.kevin.newsapp.data.model.news.NewsResponse
import com.kevin.newsapp.databinding.ItemRecyclerViewHeadlineBinding

class HeadlineRecyclerViewAdapter(
        private val context: Context,
        private val listener: HeadlineRecyclerViewItemClickListener
): RecyclerView.Adapter<HeadlineRecyclerViewHolder>() {

    private var mData: NewsResponse? = null

    private var mLastPosition = -1

    fun updateData(data: NewsResponse) {
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if(mData?.articles?.isNotEmpty() != false) EMPTY_DATA
        else VALID_DATA
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineRecyclerViewHolder {
        val binding = inflate(parent, R.layout.item_recycler_view_headline)

        binding.setVariable(BR.itemClickListener, listener)

        return HeadlineRecyclerViewHolder(binding)
    }

    private fun inflate(parent: ViewGroup, layoutId: Int): ItemRecyclerViewHeadlineBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false)
    }

    override fun getItemCount(): Int {
        return mData
                ?.let {
                    if(it.articles?.isEmpty() != false) 0
                    else it.articles.size
                }
                ?:0
    }

    override fun onBindViewHolder(holder: HeadlineRecyclerViewHolder, position: Int) {
        mData?.articles
                ?.takeIf { it.isNotEmpty() }
                ?.let {
                    holder.bind(it[position])
                    setAnimation(holder.itemView, position)
                }
    }

    private fun setAnimation(view: View, position: Int) {
        if(position > mLastPosition) {
            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.item_slide_from_bottom))
            mLastPosition = position
        }
    }

    override fun onViewDetachedFromWindow(holder: HeadlineRecyclerViewHolder) {
        holder.clearAnimation()
    }

    companion object {
        const val EMPTY_DATA = 0
        const val VALID_DATA = 1
    }
}