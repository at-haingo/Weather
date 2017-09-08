package com.example.ui.city

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.example.api.ApiUtils
import com.example.model.Data
import com.example.model.ForeCast
import com.example.model.ItemForeCast
import com.example.quanghai.weather.R
import com.example.quanghai.weather.databinding.FragmentCityBinding
import com.example.ui.search.NewCityFragment
import kotlinx.android.synthetic.main.fragment_city.view.*
import kotlinx.android.synthetic.main.item_forecast.*
import kotlinx.android.synthetic.main.item_sun_moon.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by quanghai on 16/08/2017.
 */
class CityFragment : Fragment() {
    companion object {
        val mBaseUrl = "http://api.openweathermap.org/data/2.5/"
        val mImageBaseUrl = "http://openweathermap.org/img/w/"

        fun newInstance(cityName: String): CityFragment {
            val cityFragment = CityFragment()
            val arguments = Bundle()
            arguments.putString("city", cityName)
            cityFragment.arguments = arguments
            return cityFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_city, container, false)
        initToolbar(view!!)
        if (arguments != null) {
            val cityName = arguments.getString("city")
            getWeather(view, cityName)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("xxx", "destroyview")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("xxx", "destroy")
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.addCity -> {
                activity.supportFragmentManager.popBackStack()
                addFragment(activity, NewCityFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getWeather(view: View, cityName: String) {
        var data: Data?
        val service = ApiUtils.getSOService(mBaseUrl)
        service.getWeather(cityName, resources.getString(R.string.appid))
                .enqueue(object : Callback<Data> {
                    override fun onResponse(call: Call<Data>?, response: Response<Data>?) {
                        data = response?.body()
                        val binding: FragmentCityBinding = DataBindingUtil.bind(view)
                        binding.data = data
                        binding.executePendingBindings()
                        view.viewSunMoon.setSunRiseAndSunSet(data!!.system.sunrise, data!!.system.sunset)
                    }

                    override fun onFailure(call: Call<Data>?, t: Throwable?) {
                        Log.i("xxx", t?.localizedMessage)
                    }
                })

        service.getForeCast(cityName, resources.getString(R.string.appid))
                .enqueue(object : Callback<ForeCast> {
                    override fun onResponse(call: Call<ForeCast>?, response: Response<ForeCast>?) {
                        val foreCasts = response?.body()!!.list
                        recyclerViewForeCastHour.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        recyclerViewForeCastHour.adapter = ForeCastHourAdapter(getForeCastHour(foreCasts))
                        recyclerViewForeCastDay.layoutManager = LinearLayoutManager(context)
                        recyclerViewForeCastDay.adapter = ForeCastDayAdapter(getForeCastDay(foreCasts))
                    }

                    override fun onFailure(call: Call<ForeCast>?, t: Throwable?) {
                        Log.i("xxx", t?.localizedMessage)
                    }
                })
    }

    private fun getForeCastHour(foreCasts: List<ItemForeCast>): List<ItemForeCast> {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateTime = sdf.format(Date())
        val list: MutableList<ItemForeCast> = foreCasts
                .filter { it.time.substring(0, 10) == dateTime }
                .toMutableList()
        return list
    }

    private fun getForeCastDay(foreCasts: List<ItemForeCast>): List<ItemForeCast> {
        val list: MutableList<ItemForeCast> = foreCasts
                .filter { it.time.substring(11, 16) == "09:00" }
                .toMutableList()
        return list
    }

    private fun addFragment(activity: FragmentActivity, fragment: Fragment) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frContent, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun initToolbar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(view.toolbar)
        setHasOptionsMenu(true)
    }
}
