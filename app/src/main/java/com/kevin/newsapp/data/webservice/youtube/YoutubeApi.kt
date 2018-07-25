package com.kevin.newsapp.data.webservice.youtube

import com.kevin.newsapp.BuildConfig
import com.kevin.newsapp.data.model.youtube.YoutubeActivitiesResponse
import com.kevin.newsapp.data.model.youtube.YoutubeSearchResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("activities")
    fun activities(@Query("part") part: String = "snippet,contentDetails",
                   @Query("channelId") channelId: String,
                   @Query("maxResults") maxResults: String = "20",
                   @Query("pageToken") pageToken: String,
                   @Query("key") apiKey: String = BuildConfig.YOUTUBE_API_KEY): Flowable<YoutubeActivitiesResponse>

    @GET("search")
    fun search(@Query("part") part: String,
               @Query("maxResults") maxResults: String,
               @Query("pageToken") pageToken: String,
               @Query("q") query: String,
               @Query("type") type: String,
               @Query("key") apiKey: String = BuildConfig.YOUTUBE_API_KEY): Flowable<YoutubeSearchResponse>
}