package com.example.api

import com.example.model.Data
import com.example.model.ForeCast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by quanghai on 16/08/2017.
 */
interface SOService {
    @GET("weather")
    fun getWeather(@Query("q") q: String, @Query("appid") appid: String): Call<Data>

    @GET("forecast")
    fun getForeCast(@Query("q") q: String, @Query("appid") appid: String): Call<ForeCast>
}