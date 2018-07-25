package com.kevin.newsapp.data.repository.youtube

import com.kevin.newsapp.data.model.youtube.YoutubeVideo
import io.reactivex.Observable

interface YoutubeRepository {

    fun search(query: String): Observable<List<YoutubeVideo>>
}