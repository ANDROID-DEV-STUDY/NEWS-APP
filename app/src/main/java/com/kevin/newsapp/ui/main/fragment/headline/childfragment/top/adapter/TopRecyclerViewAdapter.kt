package com.kevin.newsapp.ui.main.fragment.headline.childfragment.top.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kevin.newsapp.R
import com.kevin.newsapp.data.model.news.NewsResponse
import com.kevin.newsapp.databinding.ItemRecyclerViewTopBinding

class TopRecyclerViewAdapter(
        private val context: Context
): RecyclerView.Adapter<TopRecyclerViewHolder>() {

    private var mData: NewsResponse? = null

    fun setData(data: NewsResponse) {
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if(mData?.articles?.isNotEmpty() != false) EMPTY_DATA
        else VALID_DATA
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRecyclerViewHolder {
        // TODO 분기 처리
        val binding = inflate(parent, R.layout.item_recycler_view_top)
        return TopRecyclerViewHolder(binding)
    }

    private fun inflate(parent: ViewGroup, layoutId: Int): ItemRecyclerViewTopBinding {
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

    override fun onBindViewHolder(holder: TopRecyclerViewHolder, position: Int) {
        mData?.articles
                ?.takeIf { it.isNotEmpty() }
                ?.let { holder.bind(it[position]) }
    }

    companion object {
        const val EMPTY_DATA = 0
        const val VALID_DATA = 1
    }
}