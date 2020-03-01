package com.fedorov.weatherapp.domain.repository

import com.fedorov.weatherapp.domain.model.WeatherLocation
import com.fedorov.weatherapp.domain.model.ParentLocation

interface RepositoryRemote {
    suspend fun searchLocation(locationName: String): List<ParentLocation>
    suspend fun getWeather(locationId: Int): WeatherLocation
}