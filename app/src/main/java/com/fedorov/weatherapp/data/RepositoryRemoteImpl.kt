package com.fedorov.weatherapp.data

import com.fedorov.weatherapp.data.converter.toDomain
import com.fedorov.weatherapp.data.service.ApiSearchLocations
import com.fedorov.weatherapp.data.service.ApiWeatherLocations
import com.fedorov.weatherapp.domain.model.ParentLocation
import com.fedorov.weatherapp.domain.model.WeatherLocation
import com.fedorov.weatherapp.domain.repository.RepositoryRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryRemoteImpl @Inject constructor(
    private val apiSearchLocations: ApiSearchLocations,
    private val apiWeatherLocations: ApiWeatherLocations
) : RepositoryRemote {

    override suspend fun searchLocation(locationName: String): List<ParentLocation> =
        apiSearchLocations.getLocationsByQuery(locationName = locationName).map {
            it.toDomain()
        }

    override suspend fun getWeather(locationId: Int): WeatherLocation =
        apiWeatherLocations.getLocationById(locationId).toDomain()
}