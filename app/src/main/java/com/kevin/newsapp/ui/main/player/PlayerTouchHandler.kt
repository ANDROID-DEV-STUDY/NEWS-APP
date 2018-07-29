package com.kevin.newsapp.ui.main.player

import android.content.res.Resources
import android.util.Log
import android.view.MotionEvent
import android.view.View

class PlayerTouchHandler constructor(
        private val listener: PlayerTouchEventListener
): View.OnTouchListener {

    private val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
    private val deviceHeight = Resources.getSystem().displayMetrics.heightPixels

    private var mActivePointerId: Int = MotionEvent.INVALID_POINTER_ID

    private var mX: Float = 0f
    private var mY: Float = 0f

    var isExpand: Boolean = false

    private var isScaling: Boolean = false
    private var isMovingHorizontal: Boolean = false

    private var mDirection: Direction = Direction.None()

    private var dVertical: Float = 0f
    private var dHorizontal: Float = 0f

    override fun onTouch(view: View, event: MotionEvent): Boolean {

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                mX = event.rawX
                mY = event.rawY

                mActivePointerId = event.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE -> {
                val activePointerIndex = event.findPointerIndex(mActivePointerId)
                if(activePointerIndex == -1) {
                    Log.e(TAG, "Invalid pointerId= $mActivePointerId in onTouchEvent")
                    return true
                }

                val dX = event.rawX - mX
                val dY = event.rawY - mY

                mDirection = calculateDirection(dX, dY)

                when(mDirection) {
                    is Direction.FromTopToBottom, is Direction.FromBottomToTop -> {
                        if(!isMovingHorizontal) {
                            isScaling = true

                            val percent = dY / deviceHeight

                            dVertical += percent
                            if(dVertical > 1f) dVertical = 1f else if(dVertical < 0) dVertical = 0f

                            listener.onScale(percent)
                        }
                    }
                    is Direction.FromLeftToRight, is Direction.FromRightToLeft -> {
                        if(!isScaling && !isExpand) {
                            isMovingHorizontal = true

                            val percent = dX / deviceWidth

                            dHorizontal += percent

                            listener.onSwipe(percent)
                        }
                    }
                }

                mX = event.rawX
                mY = event.rawY
            }
            MotionEvent.ACTION_UP -> {
                if(dVertical > 0 && isScaling) shrinkOrExpand()
                else {
                    if(isMovingHorizontal && (dHorizontal > .25 || dHorizontal < -.25))
                        dismissInternally()
                    else
                        shrinkInternally(false)
                }

                isScaling = false
                isMovingHorizontal = false
            }
            MotionEvent.ACTION_CANCEL -> {
                if(dVertical > 0 && isScaling) shrinkOrExpand()
                else {
                    if(isMovingHorizontal && (dHorizontal > .25 || dHorizontal < -.25))
                        dismissInternally()
                    else
                        shrinkInternally(false)
                }

                isScaling = false
                isMovingHorizontal = false
            }
        }

        return true
    }

    private fun dismissInternally() {
        listener.onDismiss()
        dHorizontal = 0f
    }

    private fun shrinkOrExpand() {
        when(mDirection) {
            is Direction.FromTopToBottom -> shrinkInternally(true)
            is Direction.FromBottomToTop -> expandInternally()
            else -> {
                if(dVertical > .5) shrinkInternally(true)
                else expandInternally()
            }
        }
    }

    private fun expandInternally() {
        listener.onExpand()
        isExpand = true
        dVertical = 0f
    }

    private fun shrinkInternally(alphaAnimate: Boolean) {
        listener.onShrink(alphaAnimate)
        isExpand = false
        dVertical = 1f
    }

    private fun calculateDirection(dX: Float, dY: Float): Direction {
        return if(Math.abs(dX) > Math.abs(dY)) {
            if(dX > 0) Direction.FromLeftToRight()
            else Direction.FromRightToLeft()
        } else {
            if(dY > 0) Direction.FromTopToBottom()
            else Direction.FromBottomToTop()
        }
    }

    private sealed class Direction {
        class FromTopToBottom: Direction()
        class FromBottomToTop: Direction()
        class FromLeftToRight: Direction()
        class FromRightToLeft: Direction()
        class None: Direction()
    }

    companion object {
        val TAG = PlayerTouchHandler::class.java.simpleName
    }
}