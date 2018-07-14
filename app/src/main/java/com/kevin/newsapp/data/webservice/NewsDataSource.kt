package com.kevin.newsapp.data.webservice

import com.kevin.newsapp.data.model.Response
import io.reactivex.Flowable

interface NewsDataSource {

    fun topHeadlines(apiKey : String) : Flowable<Response>

    fun categoryHeadlines(category : String, apiKey : String) : Flowable<Response>
}