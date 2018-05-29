package com.kevin.newsapp.data.webservice

import com.kevin.newsapp.data.model.Response
import io.reactivex.Flowable

interface NewsDataSource {

    fun topHeadlines(country : String, apiKey : String) : Flowable<Response>

    fun categoryHeadlines(country : String, category : String, apiKey : String) : Flowable<Response>
}