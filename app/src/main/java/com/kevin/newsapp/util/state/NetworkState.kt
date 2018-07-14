package com.kevin.newsapp.util.state

sealed class NetworkState<out T> {
    class Init : NetworkState<Nothing>()
    class Loading : NetworkState<Nothing>()
    class Success<out T>(val data: T) : NetworkState<T>()
    class Error(val throwable: Throwable) : NetworkState<Nothing>()
}