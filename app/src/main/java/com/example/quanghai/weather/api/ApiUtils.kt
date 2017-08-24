package com.example.quanghai.weather.api

/**
 * Created by quanghai on 16/08/2017.
 */
class ApiUtils {
    companion object {
        fun getSOService(url: String): SOService = RetrofitClient.getClient(url).create(SOService::class.java)
    }
}