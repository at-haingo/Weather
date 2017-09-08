package com.example.model

import android.databinding.BaseObservable
import com.example.ui.city.CityFragment

/**
 * Created by quanghai on 16/08/2017.
 */
class Weather : BaseObservable() {
    val id: Int = 0
    val main: String = ""
    val description: String = ""
    val icon: String = ""
        get() = CityFragment.mImageBaseUrl.plus(field).plus(".png")
}
