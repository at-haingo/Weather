package com.example.ui.city

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quanghai.weather.R
import kotlinx.android.synthetic.main.fragment_city_viewpager.view.*

/**
 *
 * Created by Hai on 9/7/2017.
 */
class CityViewPagerFragment : Fragment() {
    companion object {
        fun getInstance(cityList: ArrayList<String>): CityViewPagerFragment {
            val cityViewPagerFragment = CityViewPagerFragment()
            val arguments = Bundle()
            arguments.putStringArrayList("city", cityList)
            cityViewPagerFragment.arguments = arguments
            return cityViewPagerFragment
        }
    }

    private var mView: View? = null
    private var mAdapter: ViewPagerAdapter? = null
    private val mFragments: MutableList<CityFragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("xxx", "create")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_city_viewpager, container, false)
        if (arguments != null) {
            val cityList = arguments.getStringArrayList("city")
            Log.d("xxx", "size: $cityList.size")
            cityList.forEach {
                mFragments.add(CityFragment.newInstance(it))
            }
            initViewPager()
        }
        return mView
    }

    override fun onResume() {
        super.onResume()
        Log.d("xxx", "Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("xxx", "Pause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("xxx", "destroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("xxx", "destroy")
    }

    fun addCity(city: String) {
        mFragments.add(CityFragment())
        Log.d("xxx", "size: " + mFragments.size)
        mAdapter?.notifyDataSetChanged()
    }

    private fun initViewPager() {
        mAdapter = ViewPagerAdapter(activity.supportFragmentManager, mFragments)
        mView?.viewPager?.adapter = mAdapter
    }
}
