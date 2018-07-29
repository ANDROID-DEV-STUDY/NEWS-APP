package com.kevin.newsapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kevin.newsapp.R
import com.kevin.newsapp.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /*
        animation_view.apply {
            addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    animation_view.visibility = View.GONE
                    text.visibility = View.VISIBLE

                    Handler().postDelayed({
                        MainActivity.start(this@SplashActivity)
                        finish()
                    }, 1000)
                }
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            })
        }
        */
}
