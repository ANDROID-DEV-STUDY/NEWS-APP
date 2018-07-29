package com.kevin.newsapp.ui.main.video.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.ItemViewPagerVideoBinding

class VideoViewPagerAdapter constructor(
        private val context: Context
): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = inflate(context, container, R.layout.item_view_pager_video)

        binding.positionTextViewForTest.text = "$position"
        /*
        with(binding.videoPageRecyclerView) {
            adapter = VideoRecyclerViewAdapter()
            layoutManager = LinearLayoutManager(context)
        }
        */

        container.addView(binding.root)

        return binding.root
    }

    private fun inflate(context: Context, container: ViewGroup, layoutId: Int): ItemViewPagerVideoBinding
            = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, container, false)

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun getCount(): Int = 8

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any)
            = container.removeView(`object` as View)
}