package com.fedorov.weatherapp.data.service

import com.fedorov.weatherapp.data.service.model.LocationApi
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiWeatherLocations {
    @GET("/api/location/{woeid}/")
    suspend fun getLocationById(@Path("woeid") woeid: Int): LocationApi
}