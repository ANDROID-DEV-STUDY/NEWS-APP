package com.kevin.newsapp.ui.main.fragment.headline.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import android.view.ViewGroup
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.business.BusinessFragment
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.entertainment.EntertainmentFragment
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.health.HealthFragment
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.science.ScienceFragment
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.sports.SportsFragment
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.technology.TechnologyFragment
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.top.TopFragment

/**
 * FragmentStatePagerAdapter vs FragmentPagerAdapter
 * memory 관리 vs 관리 X
 */
class HeadlineViewPagerAdapter constructor(manager: FragmentManager): FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> TopFragment()
            1 -> BusinessFragment()
            2 -> EntertainmentFragment()
            3 -> SportsFragment()
            4 -> TechnologyFragment()
            5 -> HealthFragment()
            6 -> ScienceFragment()
            else -> throw IllegalArgumentException("Unexpected error")
        }
    }

    override fun getCount(): Int = 7

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any)
            = container.removeView(`object` as View)
}