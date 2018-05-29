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
import com.kevin.newsapp.ui.base.BaseActivity
import com.kevin.newsapp.util.state.WebServiceState
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

        viewModel.topHeadlines.observe(this, Observer {
            when(it) {
                is WebServiceState.Init -> { }
                is WebServiceState.Loading -> { }
                is WebServiceState.Success -> { headlineViewPager.adapter = TopPagerAdapter(it.data.articles) } // TODO diff ?
                is WebServiceState.Error -> {  }
            }
        })

        viewModel.categoryHeadlines.observe(this, Observer {
            when(it) {
                is WebServiceState.Init -> {  }
                is WebServiceState.Loading -> {  }
                is WebServiceState.Success -> { mainViewPager.adapter = CategoryPagerAdapter(it.data.articles) } // TODO diff ?
                is WebServiceState.Error -> {  }
            }
        })

        viewModel.fetchTopHeadlines()
        viewModel.fetchCategoryHeadlines("business")

        mainViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) { mainViewPager.currentItem = tab?.position!! }
        })

        // nav view right to left
        rtlMenu.setOnClickListener {
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
            R.id.nav_camera -> { }
            R.id.nav_gallery -> { }
            R.id.nav_slideshow -> { }
            R.id.nav_manage -> { }
            R.id.nav_share -> { }
            R.id.nav_send -> { }
        }

        drawer_layout.closeDrawer(GravityCompat.END)
        return true
    }

    companion object {
        fun start(context : Activity) = context.startActivity(Intent(context, MainActivity::class.java))
    }
}
