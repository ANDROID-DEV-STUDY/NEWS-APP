package com.kevin.newsapp.data.webservice.news

import com.kevin.newsapp.BuildConfig
import com.kevin.newsapp.data.model.news.NewsResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun topHeadlines(@Query("country") country : String = "kr",
                     @Query("apiKey") apiKey : String = BuildConfig.NEWS_API_KEY) : Flowable<NewsResponse>

    @GET("top-headlines")
    fun categoryHeadlines(@Query("country") country : String = "kr",
                          @Query("category") category : String,
                          @Query("apiKey") key : String = BuildConfig.NEWS_API_KEY) : Flowable<NewsResponse>
}