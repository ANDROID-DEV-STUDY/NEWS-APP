package com.kevin.newsapp.data.webservice.youtube

import android.arch.lifecycle.LiveData
import com.kevin.newsapp.data.model.youtube.YoutubeActivitiesResponseItem
import com.kevin.newsapp.data.model.youtube.YoutubeVideo
import io.reactivex.Flowable

interface YoutubeRemoteDataSource {

    fun getActivities(): LiveData<List<YoutubeActivitiesResponseItem>>

    fun search(query: String): Flowable<List<YoutubeVideo>>
}