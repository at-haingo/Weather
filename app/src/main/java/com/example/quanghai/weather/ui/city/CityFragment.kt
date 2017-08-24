package com.example.quanghai.weather.ui.city

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quanghai.weather.BR
import com.example.quanghai.weather.R
import com.example.quanghai.weather.api.ApiUtils
import com.example.quanghai.weather.databinding.FragmentCityBinding
import com.example.quanghai.weather.model.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_city.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * Created by quanghai on 16/08/2017.
 */
class CityFragment : Fragment() {
    private val mBaseUrl = "http://api.openweathermap.org/data/2.5/"
    private val mBaseIconUrl = "http://openweathermap.org/img/w/"
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_city, container, false)
        getWeather()
        return view
    }

    private fun getWeather() {
        val service = ApiUtils.getSOService(mBaseUrl)
        service.getWeather("DaNang, VN", resources.getString(R.string.appid))
                .enqueue(object : Callback<Data> {
                    override fun onResponse(call: Call<Data>?, response: Response<Data>?) {
                        val data: Data = response!!.body()
                        val iconId = data.weathers[0].icon
                        val iconUrl = mBaseIconUrl.plus(iconId).plus(".png")
                        Log.d("xxx", iconUrl)
                        Picasso.with(context).load(iconUrl).into(imgIcon)
                        val binding: FragmentCityBinding = DataBindingUtil.setContentView(activity, R.layout.fragment_city)
                        binding.setVariable(BR.data, data)
                        binding.executePendingBindings()
                    }

                    override fun onFailure(call: Call<Data>?, t: Throwable?) {
                        Log.i("xxx", t?.localizedMessage)
                    }
                })
    }
}