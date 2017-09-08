package com.example.model

import com.google.gson.annotations.SerializedName

/**
 * Created by quanghai on 16/08/2017.
 */
data class Main(@SerializedName("temp") val temperature: Float, val pressure: Float, val humidity: Float,
                @SerializedName("temp_min") val tempMin: Float, @SerializedName("temp_max") val tempMax: Float)
