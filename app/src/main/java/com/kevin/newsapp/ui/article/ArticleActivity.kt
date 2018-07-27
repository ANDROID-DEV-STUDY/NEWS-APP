package com.kevin.newsapp.ui.article

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kevin.newsapp.R

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val url = intent.getStringExtra(ARTICLE_URL)
        Log.d("JUWONLEE", "article url: $url")
        // WEB VIEW
    }

    companion object {
        const val ARTICLE_URL = "article url"
    }
}
