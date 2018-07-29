package com.kevin.newsapp.data.repository.news

import com.kevin.newsapp.data.database.news.NewsLocalDataSource
import com.kevin.newsapp.data.model.news.NewsResponse
import com.kevin.newsapp.data.webservice.news.NewsRemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
        private val remoteDataSource : NewsRemoteDataSource,
        private val localDataSource: NewsLocalDataSource
) : NewsRepository {

    override fun fetchTopHeadlines(): Flowable<NewsResponse>
        = remoteDataSource.fetchTopHeadlines()

    override fun fetchCategoryHeadlines(category: String): Flowable<NewsResponse>
        = remoteDataSource.fetchCategoryHeadlines(category)
}