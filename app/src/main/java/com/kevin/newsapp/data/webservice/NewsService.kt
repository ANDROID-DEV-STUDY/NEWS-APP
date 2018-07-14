package com.kevin.newsapp.data.webservice

import com.kevin.newsapp.data.model.Response
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun topHeadlines(@Query("country") country : String = "kr",
                    @Query("apiKey") key : String) : Flowable<Response>

    @GET("top-headlines")
    fun categoryHeadlines(@Query("country") country : String = "kr",
                 @Query("category") category : String,
                 @Query("apiKey") key : String) : Flowable<Response>

    // 국가별, 신문사별 ... 확장 가능성
}

// business, entertainment, health, science, sports, technology
// e5816c79bdff45858154a5ea91aedbf6