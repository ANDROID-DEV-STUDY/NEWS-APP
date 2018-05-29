package com.kevin.newsapp.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.kevin.newsapp.R
import com.kevin.newsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        animation_view.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                Handler().postDelayed({
                    MainActivity.start(this@SplashActivity)
                    finish()
                }, 1000)
            }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        })
    }
}
