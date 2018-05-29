package com.kevin.newsapp.data.model

import com.kevin.newsapp.data.model.Article

data class Response (
        val status : String,
        val totalResults : Int,
        val articles : List<Article>
)