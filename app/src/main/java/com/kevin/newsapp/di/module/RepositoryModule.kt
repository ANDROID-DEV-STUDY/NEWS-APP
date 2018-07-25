package com.kevin.newsapp.di.module

import com.kevin.newsapp.data.database.news.NewsLocalDataSource
import com.kevin.newsapp.data.database.news.NewsLocalDataSourceImpl
import com.kevin.newsapp.data.repository.news.NewsRepository
import com.kevin.newsapp.data.repository.news.NewsRepositoryImpl
import com.kevin.newsapp.data.repository.youtube.YoutubeRepository
import com.kevin.newsapp.data.repository.youtube.YoutubeRepositoryImpl
import com.kevin.newsapp.data.webservice.news.NewsRemoteDataSource
import com.kevin.newsapp.data.webservice.news.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds @Singleton
    abstract fun provideNewsLocalDataSource(newsLocalDataSourceImpl: NewsLocalDataSourceImpl): NewsLocalDataSource

    @Binds @Singleton
    abstract fun provideNewsRemoteDataSource(newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds @Singleton
    abstract fun provideNewRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds @Singleton
    abstract fun provideYoutubeRepository(youtubeRepositoryImpl: YoutubeRepositoryImpl): YoutubeRepository
}