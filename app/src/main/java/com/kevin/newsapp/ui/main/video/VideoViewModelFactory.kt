package com.kevin.newsapp.ui.main.video

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.data.repository.youtube.YoutubeRepository
import javax.inject.Inject

class VideoViewModelFactory @Inject constructor(
        private val repository: YoutubeRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoViewModel(repository) as T
    }
}