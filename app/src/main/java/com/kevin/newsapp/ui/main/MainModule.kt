package com.kevin.newsapp.ui.main

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.scope.ActivityScope
import com.kevin.newsapp.di.scope.FragmentScope
import com.kevin.newsapp.ui.main.discover.DiscoverFragment
import com.kevin.newsapp.ui.main.discover.DiscoverModule
import com.kevin.newsapp.ui.main.headline.HeadlineFragment
import com.kevin.newsapp.ui.main.headline.HeadlineModule
import com.kevin.newsapp.ui.main.profile.ProfileFragment
import com.kevin.newsapp.ui.main.profile.ProfileModule
import com.kevin.newsapp.ui.main.video.VideoFragment
import com.kevin.newsapp.ui.main.video.VideoModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @Binds @ActivityScope
    abstract fun provideViewModelFactory(mainViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory

    @FragmentScope
    @ContributesAndroidInjector(modules = [HeadlineModule::class])
    abstract fun contributeHeadlineFragment(): HeadlineFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [VideoModule::class])
    abstract fun contributeVideoFragment(): VideoFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DiscoverModule::class])
    abstract fun contributeDiscoverFragment(): DiscoverFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ProfileModule::class])
    abstract fun contributeProfileFragment(): ProfileFragment
}