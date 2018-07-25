package com.kevin.newsapp.data.model.youtube

data class YoutubeActivitiesResponse(
        var kind: String,
        var etag: String,
        var nextPageToken: String,
        var prevPageToken: String,
        var pageInfo: PageInfo,
        var items: List<YoutubeActivitiesResponseItem>
) {
    data class PageInfo(
            var totalResults: Int,
            var resultsPerPage: Int
    )
}