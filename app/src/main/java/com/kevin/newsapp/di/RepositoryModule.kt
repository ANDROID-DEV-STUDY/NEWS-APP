package com.kevin.newsapp.di

import com.kevin.newsapp.data.repository.HeadlineRepository
import com.kevin.newsapp.data.repository.HeadlineRepositoryImpl
import com.kevin.newsapp.data.webservice.NewsDataSource
import com.kevin.newsapp.data.webservice.NewsDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideNewsDataSource(newsDataSourceImpl: NewsDataSourceImpl) : NewsDataSource

    @Binds
    abstract fun provideHeadlineRepository(headlineRepositoryImpl: HeadlineRepositoryImpl) : HeadlineRepository
}