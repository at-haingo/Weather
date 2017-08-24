package com.example.quanghai.weather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by quanghai on 16/08/2017.
 */
data class Data(@SerializedName("coord") val coordinate: Coordinates, @SerializedName("weather") val weathers: List<Weather>,
                val base: String, val main: Main, val visibility: Float, val wind: Wind, val clouds: Cloud,
                val dt: Long, @SerializedName("sys") val system: System, val id: Long, val name: String,
                @SerializedName("cod") val code: Int)