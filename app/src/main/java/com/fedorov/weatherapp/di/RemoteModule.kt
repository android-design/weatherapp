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
    fun getRetrofit(): Retrofit {
        val baseUrl = "https://www.metaweather.com/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
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