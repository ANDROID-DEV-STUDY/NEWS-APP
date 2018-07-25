package com.kevin.newsapp.ui.main.fragment.headline

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.data.repository.news.NewsRepository
import javax.inject.Inject

class HeadlineViewModelFactory @Inject constructor(
        private val newsRepository: NewsRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HeadlineViewModel(newsRepository) as T
    }
}