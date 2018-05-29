package com.kevin.newsapp.data.repository

import com.kevin.newsapp.data.model.Response
import com.kevin.newsapp.data.webservice.NewsDataSource
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeadlineRepositoryImpl @Inject constructor(
        private val dataSource : NewsDataSource
) : HeadlineRepository {

    override fun topHeadlines(country: String, apiKey: String): Flowable<Response>
        = dataSource.topHeadlines(country, apiKey)

    override fun categoryHeadlines(country: String, category: String, apiKey: String): Flowable<Response>
        = dataSource.categoryHeadlines(country, category, apiKey)
}