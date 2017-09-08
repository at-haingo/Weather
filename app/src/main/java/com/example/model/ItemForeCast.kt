package com.example.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hai on 8/25/2017.
 */
data class ItemForeCast(val main: Main, @SerializedName("weather") val weathers: List<Weather>, @SerializedName("dt_txt") var time: String)
