package com.kevin.newsapp.ui.main.headline.children.top

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.data.repository.news.NewsRepository
import javax.inject.Inject

class TopViewModelFactory @Inject constructor(
        private val newsRepository: NewsRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopViewModel(newsRepository) as T
    }
}