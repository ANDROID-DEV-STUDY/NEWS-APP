package com.kevin.newsapp.ui.main

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.kevin.newsapp.R
import com.kevin.newsapp.data.model.Article
import com.kevin.newsapp.extensions.inflate
import com.kevin.newsapp.extensions.loadImage
import com.kevin.newsapp.extensions.snackbar
import kotlinx.android.synthetic.main.top_headline.view.*

class TopPagerAdapter constructor(
        private val headlines : List<Article>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any
        = container.inflate(R.layout.top_headline)
                .apply { // view
                    with(headlines[position]) {// data
                        headline_image.loadImage(urlToImage) // load image by glide default : CenterCrop
                        headline_title.text = title
                        this@apply.setOnClickListener { snackbar(url) }
                    } // end bind data on view
                }.also { container.addView(it) }
    /*
    val view = container.inflate(R.layout.top_headline)
    val article = headlines[position]

    view.setOnClickListener { it.snackbar(article.url) }

    container.addView(view)

    return view
    */

    override fun isViewFromObject(view: View, `object`: Any): Boolean
        = (view == `object`)

    override fun getCount(): Int = headlines.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.takeIf { `object` is View }?.removeView(`object` as View)
    }

}