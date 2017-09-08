package com.example

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import com.example.quanghai.weather.R
import com.example.ui.city.CityViewPagerFragment
import com.example.ui.drawer.DrawerAdapter
import com.example.ui.search.NewCityFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

/**
 *
 * Created by Hai on 8/24/2017.
 */
class MainActivity : AppCompatActivity(), NewCityFragment.AddNewCityListener, DrawerAdapter.OnCityClickListener {
    private var mAdapter: DrawerAdapter? = null
    private val mCityList: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDrawerRecyclerView()
        getLastKnowLocation()
        if (mCityList.size == 0) {
            mCityList.add("DaNang")
            replaceFragment(this, CityViewPagerFragment.getInstance(mCityList as ArrayList<String>))
        }
    }

    override fun onAddNewCity(city: String) {
        Log.d("xxx", city)
        mCityList.add(city)
        mAdapter?.notifyDataSetChanged()
        replaceFragment(this, CityViewPagerFragment.getInstance(mCityList as ArrayList<String>))
//        CityViewPagerFragment.getInstance(mCityList as ArrayList<String>)
    }

    override fun onMyLocationClick() {
        Log.d("xxx", "location")
        drawerLayout.closeDrawer(Gravity.START)
    }

    override fun onCityClick(position: Int) {
        Log.d("xxx", "$position")
//        replaceFragment(this, CityFragment.newInstance(mCityList[position]))
        drawerLayout.closeDrawer(Gravity.START)
    }

    override fun onPause() {
        super.onPause()
        saveCity()
    }

    override fun onResume() {
        super.onResume()
        getCityFromPreference()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(activity: FragmentActivity, fragment: Fragment) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frContent, fragment)
        fragmentTransaction.commit()
    }

    private fun initDrawerRecyclerView() {
        mAdapter = DrawerAdapter(this, mCityList)
        recyclerViewDrawer.layoutManager = LinearLayoutManager(this)
        recyclerViewDrawer.adapter = mAdapter
    }

    private fun getLastKnowLocation(){
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val lastLocation: Location? = locationManager.getLastKnownLocation(locationManager.getBestProvider(Criteria(), false))
        Log.d("xxx", "" + lastLocation?.latitude)
        Log.d("xxx", "" + lastLocation?.longitude)
    }

    private fun getCityFromPreference() {
        val pre = getSharedPreferences("city", MODE_PRIVATE)
        val set = pre.getStringSet("cityList", null)
        if (set != null) {
            mCityList.clear()
            mCityList.addAll(set)
            mAdapter?.notifyDataSetChanged()
            replaceFragment(this, CityViewPagerFragment.getInstance(mCityList as ArrayList<String>))
        }
    }

    private fun saveCity() {
        val pre: SharedPreferences = getSharedPreferences("city", MODE_PRIVATE)
        val set = HashSet<String>()
        set.addAll(mCityList)
        val editor = pre.edit()
        editor.clear()
        editor.putStringSet("cityList", set)
        editor.apply()
    }

    private fun checkPermission(){
        val permissionAccessLocation = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionCoarseLocation = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        if (permissionAccessLocation != PackageManager.PERMISSION_GRANTED
                && permissionCoarseLocation != PackageManager.PERMISSION_GRANTED)
    }
}
