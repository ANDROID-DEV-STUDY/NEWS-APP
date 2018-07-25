package com.kevin.newsapp.di.module

import android.app.Application
import com.kevin.newsapp.data.webservice.news.NewsApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides @Singleton
    fun provideInterceptor() : Interceptor
            = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides @Singleton
    fun provideCache(app : Application) : Cache
            = Cache(app.cacheDir, 10 * 10 * 1024L)

    @Provides @Singleton
    fun provideOkhttpClient(interceptor: Interceptor, cache : Cache) : OkHttpClient
        = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15L, TimeUnit.SECONDS)
            .writeTimeout(15L, TimeUnit.SECONDS)
            .readTimeout(15L, TimeUnit.SECONDS)
            .cache(cache)
            .build()

    @Provides @Singleton
    fun provideGsonConverterFactory() : GsonConverterFactory
        = GsonConverterFactory.create()

    @Provides @Singleton
    fun provideRxJava2CallAdapterFactory() : RxJava2CallAdapterFactory
            = RxJava2CallAdapterFactory.create()

    @Provides @Singleton @Named("news_retrofit")
    fun provideNewsRetrofit(okHttpClient: OkHttpClient,
                        gsonConverterFactory: GsonConverterFactory,
                        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory) : Retrofit
        = Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .baseUrl(NEWS_BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides @Singleton @Named("youtube_retrofit")
    fun provideYoutubeRetrofit(okHttpClient: OkHttpClient,
                            gsonConverterFactory: GsonConverterFactory,
                            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory) : Retrofit
            = Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .baseUrl(YOUTUBE_BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides @Singleton
    fun provideNewsApi(@Named("news_retrofit") retrofit: Retrofit) : NewsApi
        = retrofit.create(NewsApi::class.java)

    companion object {
        const val NEWS_BASE_URL = "https://newsapi.org/v2/"
        const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"
    }
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
    ]}
*/