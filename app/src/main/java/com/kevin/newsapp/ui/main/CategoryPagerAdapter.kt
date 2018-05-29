package com.kevin.newsapp.ui.main

import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.kevin.newsapp.R
import com.kevin.newsapp.data.model.Article
import com.kevin.newsapp.extensions.inflate
import kotlinx.android.synthetic.main.category_headline.view.*

class CategoryPagerAdapter constructor(
        private val headlines : List<Article>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any
        = container.inflate(R.layout.category_headline)
                .apply {
                    category_recycler_view.adapter = CategoryArticleAdapter(headlines)
                    category_recycler_view.layoutManager = LinearLayoutManager(context)
                }.also { container.addView(it) }

    override fun isViewFromObject(view: View, `object`: Any): Boolean
        = (view == `object`)

    override fun getCount(): Int = CATEGORY_COUNT // TODO

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.takeIf { `object` is View }?.removeView(`object` as View)
    }

    companion object {
        const val CATEGORY_COUNT = 6
    }
}