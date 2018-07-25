package com.kevin.newsapp.ui.main.fragment.video

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.qualifier.FragmentLevel
import com.kevin.newsapp.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class VideoModule {

    @Binds @FragmentScope @FragmentLevel
    abstract fun provideViewModelFactory(videoViewModelFactory: VideoViewModelFactory): ViewModelProvider.Factory
}