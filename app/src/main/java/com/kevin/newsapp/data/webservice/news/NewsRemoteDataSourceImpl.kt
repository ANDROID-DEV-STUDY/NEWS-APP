package com.kevin.newsapp.data.webservice.news

import com.kevin.newsapp.data.model.news.NewsResponse
import io.reactivex.Flowable
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
        private val api : NewsApi
) : NewsRemoteDataSource {

    override fun fetchTopHeadlines() : Flowable<NewsResponse>
            = api.topHeadlines()

    override fun fetchCategoryHeadlines(category : String) : Flowable<NewsResponse>
            = api.categoryHeadlines(category = category)
}