package com.kevin.newsapp.view

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import com.kevin.newsapp.ui.main.fragment.headline.HeadlineTouchEventListener
import com.kevin.newsapp.ui.main.player.PlayerTouchHandler

class TopNestedScrollView: NestedScrollView {

    private var mActivePointerId: Int = MotionEvent.INVALID_POINTER_ID

    private var mY: Float = 0f

    var headlineTouchEventListener: HeadlineTouchEventListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                mY = event.rawY

                mActivePointerId = event.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE -> {
                val activePointerIndex = event.findPointerIndex(mActivePointerId)
                if(activePointerIndex == -1) {
                    Log.e(PlayerTouchHandler.TAG, "Invalid pointerId= $mActivePointerId in headline onTouchEvent")
                    return false
                }

                val isExpand = (event.rawY - mY) > 0 // is expand ?

                post { headlineTouchEventListener?.onScale(isExpand) }

                mY = event.rawY
            }
        }

        return true
    }
}