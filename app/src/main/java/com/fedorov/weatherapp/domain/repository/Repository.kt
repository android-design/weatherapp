package com.fedorov.weatherapp.domain.repository

import com.fedorov.weatherapp.domain.model.Location
import com.fedorov.weatherapp.domain.model.ParentLocation

interface Repository {
    suspend fun searchLocation(locationName: String): List<ParentLocation>
    suspend fun updateAllAndGet(): List<Location>
    suspend fun addCity(cityId: Int)
    suspend fun deleteCity(cityId: Int)
}