package com.example.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by quanghai on 16/08/2017.
 */
class RetrofitClient {
    companion object {
        private var retrofit: Retrofit? = null
        private val builder = OkHttpClient.Builder()
        private val interceptor = HttpLoggingInterceptor()
        fun getClient(baseUrl: String): Retrofit {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build())
                    .build()
            return retrofit!!
        }
    }
}