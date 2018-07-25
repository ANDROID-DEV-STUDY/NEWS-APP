package com.kevin.newsapp.ui.main.activity

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.scope.ActivityScope
import com.kevin.newsapp.di.scope.FragmentScope
import com.kevin.newsapp.ui.main.fragment.discover.DiscoverFragment
import com.kevin.newsapp.ui.main.fragment.discover.DiscoverModule
import com.kevin.newsapp.ui.main.fragment.headline.HeadlineFragment
import com.kevin.newsapp.ui.main.fragment.headline.HeadlineModule
import com.kevin.newsapp.ui.main.fragment.profile.ProfileFragment
import com.kevin.newsapp.ui.main.fragment.profile.ProfileModule
import com.kevin.newsapp.ui.main.fragment.video.VideoFragment
import com.kevin.newsapp.ui.main.fragment.video.VideoModule
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