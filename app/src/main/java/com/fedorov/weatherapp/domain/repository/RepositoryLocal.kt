package com.fedorov.weatherapp.domain.repository

import com.fedorov.weatherapp.domain.model.WeatherLocation

interface RepositoryLocal {
    suspend fun getAllLocations(): List<WeatherLocation>
    suspend fun updateCityWeather(weatherLocation: WeatherLocation)
    suspend fun addCity(weatherLocation: WeatherLocation)
    suspend fun deleteCity(cityId: Int)
}