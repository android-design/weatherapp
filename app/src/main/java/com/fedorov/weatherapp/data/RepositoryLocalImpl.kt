package com.fedorov.weatherapp.data

import com.fedorov.weatherapp.data.converter.toDomain
import com.fedorov.weatherapp.data.converter.toModel
import com.fedorov.weatherapp.data.db.DAO
import com.fedorov.weatherapp.domain.model.WeatherLocation
import com.fedorov.weatherapp.domain.repository.RepositoryLocal
import javax.inject.Inject

class RepositoryLocalImpl @Inject constructor(private val dao: DAO) : RepositoryLocal {

    override suspend fun getAllLocations(): List<WeatherLocation> = dao.getAll().map { it.toDomain() }

    override suspend fun addCity(weatherLocation: WeatherLocation) {
        dao.create(weatherLocation.toModel())
    }

    override suspend fun deleteCity(cityId: Int) {
        dao.delete(cityId = cityId)
    }

    override suspend fun updateCityWeather(weatherLocation: WeatherLocation) {
        dao.update(weatherLocation.toModel())
    }
}