package com.kevin.newsapp.data.model.youtube

data class YoutubeActivitiesResponseItem(
        var kind: String,
        var etag: String,
        var id: String,
        var snippet: Snippet,
        var contentDetails: ContentDetails
) {
    data class Snippet(
            var publishedAt: String,
            var channelId: String,
            var title: String,
            var description: String,
            var thumbnails: Thumbnails,
            var channelTitle: String,
            var type: String
    ) {
        data class Thumbnails(
                var default: Image,
                var medium: Image,
                var high: Image
        ) {
            data class Image(
                    var url: String,
                    var width: String,
                    var height: String
            )
        }
    }

    data class ContentDetails(
            var upload: Upload?
    ) {
        data class Upload(var videoId: String)
    }
}