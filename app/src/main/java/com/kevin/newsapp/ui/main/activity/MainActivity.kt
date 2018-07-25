package com.kevin.newsapp.ui.main.activity

import android.os.Bundle
import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.ActivityMainBinding
import com.kevin.newsapp.ui.base.BaseActivity
import com.kevin.newsapp.ui.main.fragment.discover.DiscoverFragment
import com.kevin.newsapp.ui.main.fragment.headline.HeadlineFragment
import com.kevin.newsapp.ui.main.fragment.profile.ProfileFragment
import com.kevin.newsapp.ui.main.fragment.video.VideoFragment
import com.kevin.newsapp.ui.main.player.PlayerTouchEventListener
import com.kevin.newsapp.ui.main.player.PlayerTouchEventListenerImpl
import com.kevin.newsapp.ui.main.player.PlayerTouchHandler
import com.kevin.newsapp.util.extensions.replaceFragment
import com.kevin.newsapp.view.disableShiftMode

class MainActivity:
        BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResID: Int
        get() = R.layout.activity_main

    override val modelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    private lateinit var playerTouchHandler: PlayerTouchHandler

    private lateinit var playerTouchEventListener: PlayerTouchEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.viewModel = mViewModel

        init()
    }

    private fun init() {
        playerTouchEventListener = PlayerTouchEventListenerImpl(mBinding)

        playerTouchHandler = PlayerTouchHandler(playerTouchEventListener)

        with(mBinding) {
            navigator.apply {
                disableShiftMode()

                setOnNavigationItemSelectedListener { item ->
                    return@setOnNavigationItemSelectedListener when(item.itemId) {
                        R.id.action_menu_headline -> {
                            replaceFragment(R.id.fragmentContainer, HeadlineFragment.newInstance(), HeadlineFragment.TAG)
                            true
                        }
                        R.id.action_menu_video -> {
                            replaceFragment(R.id.fragmentContainer, VideoFragment.newInstance(), VideoFragment.TAG)
                            true
                        }
                        R.id.action_menu_discover -> {
                            replaceFragment(R.id.fragmentContainer, DiscoverFragment.newInstance(), DiscoverFragment.TAG)
                            true
                        }
                        R.id.action_menu_profile -> {
                            replaceFragment(R.id.fragmentContainer, ProfileFragment.newInstance(), ProfileFragment.TAG)
                            true
                        }
                        else -> throw IllegalArgumentException("Unexpected error")
                    }
                }

                selectedItemId = R.id.action_menu_headline
            }

            videoContainer.setOnTouchListener(playerTouchHandler)

            // testButton.onClick { expand() }
        }
    }

    private fun expand() {
        playerTouchEventListener.onExpand()
        playerTouchHandler.isExpand = true
    }

    private fun shrink() {
        playerTouchEventListener.onShrink(true)
        playerTouchHandler.isExpand = false
    }

    override fun onBackPressed() {
        if(playerTouchHandler.isExpand) { shrink() }
        else super.onBackPressed()
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
}