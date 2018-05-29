package com.kevin.newsapp.data.model

data class Article(
        val source : Source,
        val author : String?,
        val title : String,
        val description : String,
        val url : String,
        val urlToImage : String,
        val publishedAt : String
) {
    data class Source(val id : String?,
                      val name : String?)
}

/*
{"status":"ok",
    "totalResults":20,
    "articles":[
        {"source":
            {"id":null,
                "name":"Donga.com"}
            ,"author":null,
            "title":"'캡틴' 손흥민 “승리에 취해선 안돼…월드컵까지 더 노력해야”",
            "description":"손흥민(26·토트넘)이 더욱 노력해야 2018 국제축구연맹(FIFA) 러시아 월드컵에서 만족스러운 결과를 낼 것이라고 목소리를 높였다.   손흥민은 28일 대구스타디움에서 열린 …",
            "url":"http://news.donga.com/Top/3/05/20180528/90293410/1",
            "urlToImage":"http://dimg.donga.com/a/600/0/90/5/wps/NEWS/IMAGE/2018/05/28/90293388.2.jpg",
            "publishedAt":"2018-05-28T14:24:00Z"},

            ...
    ]
}
*/