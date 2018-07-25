package com.kevin.newsapp.data.webservice.news

import com.kevin.newsapp.data.model.news.NewsResponse
import io.reactivex.Flowable

interface NewsRemoteDataSource {

    fun topHeadlines() : Flowable<NewsResponse>

    fun categoryHeadlines(category : String) : Flowable<NewsResponse>
}