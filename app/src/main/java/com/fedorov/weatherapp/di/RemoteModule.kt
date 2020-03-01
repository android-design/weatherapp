package com.fedorov.weatherapp.di

import com.fedorov.weatherapp.data.service.ApiSearchLocations
import com.fedorov.weatherapp.data.service.ApiWeatherLocations
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
class RemoteModule {
    @Provides
    fun getOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder() // Increase the timeouts for slow connections(ex. EDGE)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val baseUrl = "https://www.metaweather.com/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getApiSearchLocations(retrofit: Retrofit): ApiSearchLocations =
        retrofit.create(ApiSearchLocations::class.java)

    @Provides
    fun getApiWeatherLocations(retrofit: Retrofit): ApiWeatherLocations =
        retrofit.create(ApiWeatherLocations::class.java)
}