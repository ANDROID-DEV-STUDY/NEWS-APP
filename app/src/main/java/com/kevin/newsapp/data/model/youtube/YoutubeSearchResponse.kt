package com.kevin.newsapp.data.model.youtube

data class YoutubeSearchResponse (
        var kind: String,
        var etag: String,
        var nextPageToken: String,
        var prevPageToken: String,
        var regionCode: String,
        var pageInfo: PageInfo,
        var items: List<YoutubeSearchResponseItem>
) {
    data class PageInfo(
            var totalResults: Int,
            var resultsPerPage: Int
    )
}

/** JSON */
/*
"kind": "youtube#searchListResponse",
 "etag": "\"XI7nbFXulYBIpL0ayR_gDh3eu1k/HjCyztwJHnIRNkOr93T3Y5ZsidQ\"",
 "nextPageToken": "CAoQAA",
 "regionCode": "KR",
 "pageInfo": {
  "totalResults": 1000000,
  "resultsPerPage": 10
 },
 "items": [
 {
   "kind": "youtube#searchResult",
   "etag": "\"XI7nbFXulYBIpL0ayR_gDh3eu1k/qr9EBA2hpVTrjN8FUftIMpQzkfk\"",
   "id": {
    "kind": "youtube#channel",
    "channelId": "UChlgI3UHCOnwUGzWzbJ3H5w"
   },
   "snippet": {
    "publishedAt": "2013-05-23T07:07:42.000Z",
    "channelId": "UChlgI3UHCOnwUGzWzbJ3H5w",
    "title": "YTN NEWS",
    "description": "Korea's No.1 news channel YTN YTN 24시간 생방송 한국 뉴스. YTN LIVE NEWS 유튜브 공식 채널. 지금 바로 구독하세요. 구독하기 : http://goo.gl/Ytb5SZ □ YTN ...",
    "thumbnails": {
     "default": {
      "url": "https://yt3.ggpht.com/-4Gb7JgteaHk/AAAAAAAAAAI/AAAAAAAAAAA/fRo3qjpM2sk/s88-c-k-no-mo-rj-c0xffffff/photo.jpg"
     },
     "medium": {
      "url": "https://yt3.ggpht.com/-4Gb7JgteaHk/AAAAAAAAAAI/AAAAAAAAAAA/fRo3qjpM2sk/s240-c-k-no-mo-rj-c0xffffff/photo.jpg"
     },
     "high": {
      "url": "https://yt3.ggpht.com/-4Gb7JgteaHk/AAAAAAAAAAI/AAAAAAAAAAA/fRo3qjpM2sk/s800-c-k-no-mo-rj-c0xffffff/photo.jpg"
     }
    },
    "channelTitle": "YTN NEWS",
    "liveBroadcastContent": "none"
   }
  },
  ...
  ]
 */