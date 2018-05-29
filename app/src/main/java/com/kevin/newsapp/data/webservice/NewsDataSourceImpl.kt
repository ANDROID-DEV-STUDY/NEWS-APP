package com.kevin.newsapp.data.webservice

import com.kevin.newsapp.data.model.Response
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataSourceImpl @Inject constructor(
        private val api : NewsService
) : NewsDataSource {

    override fun topHeadlines(country : String, apiKey : String) : Flowable<Response>
            = api.topHeadlines(country, apiKey)

    override fun categoryHeadlines(country : String, category : String, apiKey : String) : Flowable<Response>
            = api.categoryHeadlines(country, category, apiKey)
}