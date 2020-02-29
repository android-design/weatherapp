package com.fedorov.weatherapp.di

import com.fedorov.weatherapp.data.service.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Dagger module for connection instances.
 */
@Module
class NetModule {
    @Provides
    fun getOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder() // Increase the timeouts for slow connections(ex. EDGE)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    @Provides
    fun getRetrofitService(okHttpClient: OkHttpClient): Api {
        val baseUrl = "https://www.metaweather.com/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}