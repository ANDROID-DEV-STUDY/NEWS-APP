package com.kevin.newsapp.data.model.youtube

data class YoutubeSearchResponseItem (
        var kind: String,
        var etag: String,
        var id: Id,
        var snippet: Snippet
) {
    data class Id (
            var kind: String,
            var videoId: String
    )

    data class Snippet (
            var publishedAt: String,
            var channelId: String,
            var title: String,
            var description: String,
            var thumbnails: Thumbnails,
            var channelTitle: String,
            var liveBroadcastContent: String
    ) {
        data class Thumbnails (
                var default: Image,
                var medium: Image,
                var high: Image
        ) {
            data class Image (
                    var url: String,
                    var width: String,
                    var height: String
            )
        }
    }
}