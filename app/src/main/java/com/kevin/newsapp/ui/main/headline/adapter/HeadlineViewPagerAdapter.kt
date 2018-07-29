package com.kevin.newsapp.ui.main.headline.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import com.kevin.newsapp.ui.main.headline.children.business.BusinessFragment
import com.kevin.newsapp.ui.main.headline.children.entertainment.EntertainmentFragment
import com.kevin.newsapp.ui.main.headline.children.health.HealthFragment
import com.kevin.newsapp.ui.main.headline.children.science.ScienceFragment
import com.kevin.newsapp.ui.main.headline.children.sports.SportsFragment
import com.kevin.newsapp.ui.main.headline.children.technology.TechnologyFragment
import com.kevin.newsapp.ui.main.headline.children.top.TopFragment

/**
 * FragmentStatePagerAdapter vs FragmentPagerAdapter
 * memory 관리 vs 관리 X
 */
class HeadlineViewPagerAdapter constructor(manager: FragmentManager): FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> TopFragment.newInstance()
            1 -> BusinessFragment.newInstance()
            2 -> EntertainmentFragment.newInstance()
            3 -> SportsFragment.newInstance()
            4 -> TechnologyFragment.newInstance()
            5 -> HealthFragment.newInstance()
            6 -> ScienceFragment.newInstance()
            else -> throw IllegalArgumentException("Unexpected error")
        }
    }

    override fun getCount(): Int = 7

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) { /* NOTHING */ }
}