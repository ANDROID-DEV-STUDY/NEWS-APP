package com.kevin.newsapp.data.repository.youtube

import com.kevin.newsapp.data.model.youtube.YoutubeVideo
import io.reactivex.Observable
import javax.inject.Inject

class YoutubeRepositoryImpl @Inject constructor(

): YoutubeRepository {

    override fun search(query: String): Observable<List<YoutubeVideo>> {
        // TODO api call

        // TEST
        return Observable.fromArray(listOf(YoutubeVideo("test", "test")))
    }
}