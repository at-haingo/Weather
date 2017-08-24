package com.example.quanghai.weather.api

import com.example.quanghai.weather.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by quanghai on 16/08/2017.
 */
interface SOService {
    @GET("weather")
    fun getWeather(@Query("q") q: String, @Query("appid") appid: String): Call<Data>
}