package com.kevin.newsapp.util.state

// STUDY
sealed class MultiState<out T, out V>  {
    class Init : MultiState<Nothing, Nothing>()
    class Loading : MultiState<Nothing, Nothing>()
    class Success<out T, out V>(val data1 : T, val data2 : V) : MultiState<T, V>()
    class Error(val throwable: Throwable?) : MultiState<Nothing, Nothing>()

    data class MultiNetworkState<out T, out V>constructor(
            private val state1 : CommonState<T>, private val state2 : CommonState<V>
    ) {
        fun get() : MultiState<T, V> {
            return when {
                // error
                state1 is CommonState.Error -> MultiState.Error(state1.throwable)
                state2 is CommonState.Error -> MultiState.Error(state2.throwable)
                // loading
                state1 is CommonState.Loading || state2 is CommonState.Loading -> MultiState.Loading()
                state1 is CommonState.Init && state2 is CommonState.Success -> MultiState.Loading()
                state1 is CommonState.Success && state2 is CommonState.Init -> MultiState.Loading()
                // success
                state1 is CommonState.Success && state2 is CommonState.Success -> MultiState.Success(state1.data, state2.data)
                else -> MultiState.Init()
            }
        }
    }
}