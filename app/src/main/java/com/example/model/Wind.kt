package com.example.model

import com.google.gson.annotations.SerializedName

/**
 * Created by quanghai on 16/08/2017.
 */
data class Wind(val speed: Float, @SerializedName("deg") val degrees: Float)
