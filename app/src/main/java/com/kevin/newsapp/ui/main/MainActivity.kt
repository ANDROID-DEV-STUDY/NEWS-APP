package com.kevin.newsapp.ui.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.kevin.newsapp.R
import com.kevin.newsapp.extensions.gone
import com.kevin.newsapp.extensions.visible
import com.kevin.newsapp.ui.base.BaseActivity
import com.kevin.newsapp.util.state.NetworkState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel : MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        lifecycle.addObserver(viewModel)

        viewModel.fetchTopHeadlines()
        viewModel.fetchCategoryHeadlines("business")

        viewModel.topHeadlines.observe(this, Observer {
            when(it) {
                is NetworkState.Init -> { loading.apply { pauseAnimation() }.gone() }
                is NetworkState.Loading -> { loading.apply { playAnimation() }.visible() }
                is NetworkState.Success -> { headline_view_pager.adapter = TopPagerAdapter(it.data.articles) } // TODO diff ?
                is NetworkState.Error -> {  }
            }
        })

        viewModel.categoryHeadlines.observe(this, Observer {
            when(it) {
                is NetworkState.Init -> {  }
                is NetworkState.Loading -> {  }
                is NetworkState.Success -> { main_view_pager.adapter = CategoryPagerAdapter(it.data.articles) } // TODO diff ?
                is NetworkState.Error -> {  }
            }
        })

        main_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) { main_view_pager.currentItem = tab?.position!! }
        })

        // nav view right to left
        rtl_menu.setOnClickListener {
            if(drawer_layout.isDrawerOpen(GravityCompat.END))
                drawer_layout.closeDrawer(GravityCompat.END)
            else
                drawer_layout.openDrawer(GravityCompat.END)
        }

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.END)) {
            drawer_layout.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            // TODO
            R.id.nav_business -> { main_view_pager.currentItem = BUSINESS }
            R.id.nav_entertainment -> { main_view_pager.currentItem = ENTERTAINMENT }
            R.id.nav_health -> { main_view_pager.currentItem = HEALTH }
            R.id.nav_science -> { main_view_pager.currentItem = SCIENCE }
            R.id.nav_sports -> { main_view_pager.currentItem = SPORTS }
            R.id.nav_technology -> { main_view_pager.currentItem = TECHNOLOGY }
            R.id.nav_share -> { }
            R.id.nav_send -> { }
        }

        drawer_layout.closeDrawer(GravityCompat.END)
        return true
    }

    companion object {
        fun start(context : Activity) = context.startActivity(Intent(context, MainActivity::class.java))

        const val BUSINESS = 0; const val ENTERTAINMENT = 1; const val HEALTH = 2
        const val SCIENCE = 3; const val SPORTS = 4; const val TECHNOLOGY = 5
    }
}
