package com.example.ui.city

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter

/**
 * Created by Hai on 9/7/2017.
 */
class ViewPagerAdapter(fm: FragmentManager, private val mFragments: MutableList<CityFragment>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int) = mFragments[position]

    override fun getCount() = mFragments.size

    override fun getItemPosition(`object`: Any?): Int {
        return POSITION_NONE
    }
}
