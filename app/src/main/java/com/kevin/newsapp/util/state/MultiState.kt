package com.kevin.newsapp.util.state

sealed class MultiState<out T, out V>  {
    class Init : MultiState<Nothing, Nothing>()
    class Loading : MultiState<Nothing, Nothing>()
    class Success<out T, out V>(val data1 : T, val data2 : V) : MultiState<T, V>()
    class Error(val throwable: Throwable?) : MultiState<Nothing, Nothing>()

    data class MultiNetworkState<out T, out V>constructor(
            private val state1 : NetworkState<T>, private val state2 : NetworkState<V>
    ) {
        fun get() : MultiState<T, V> {
            return when {
                // error
                state1 is NetworkState.Error -> MultiState.Error(state1.throwable)
                state2 is NetworkState.Error -> MultiState.Error(state2.throwable)
                // loading
                state1 is NetworkState.Loading || state2 is NetworkState.Loading -> MultiState.Loading()
                state1 is NetworkState.Init && state2 is NetworkState.Success -> MultiState.Loading()
                state1 is NetworkState.Success && state2 is NetworkState.Init -> MultiState.Loading()
                // success
                state1 is NetworkState.Success && state2 is NetworkState.Success -> MultiState.Success(state1.data, state2.data)
                else -> MultiState.Init()
            }
        }
    }
}