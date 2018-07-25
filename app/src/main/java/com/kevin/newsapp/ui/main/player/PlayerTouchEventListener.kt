package com.kevin.newsapp.ui.main.player

interface PlayerTouchEventListener {

    fun onScale(percent: Float)

    fun onSwipe(percent: Float)

    fun onExpand()

    fun onShrink(alphaAnimate: Boolean)

    fun onDismiss()
}