package com.kevin.newsapp.data.webservice.news

import com.kevin.newsapp.data.model.news.NewsResponse
import io.reactivex.Flowable

interface NewsRemoteDataSource {

    fun fetchTopHeadlines() : Flowable<NewsResponse>

    fun fetchCategoryHeadlines(category : String) : Flowable<NewsResponse>
}