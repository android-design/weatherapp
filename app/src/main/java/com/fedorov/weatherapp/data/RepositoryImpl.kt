package com.fedorov.weatherapp.data

import com.fedorov.weatherapp.data.converter.ModelConverterImpl
import com.fedorov.weatherapp.data.service.Api
import com.fedorov.weatherapp.domain.repository.Repository
import com.fedorov.weatherapp.domain.model.Location
import com.fedorov.weatherapp.domain.model.ParentLocation
import com.fedorov.weatherapp.data.db.DaoImpl
import com.fedorov.weatherapp.data.db.model.LocationModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {

    // TODO: To dagger
    private val modelConverter = ModelConverterImpl()
    private val dao = DaoImpl()

    override suspend fun searchLocation(locationName: String): List<ParentLocation> = api.getLocationsByQuery(locationName = locationName).map {
            modelConverter.parentLocationApiToDomain(it)
        }

    override suspend fun updateAllAndGet(): List<Location> {
        val listCities = dao.get()

        for (city in listCities) {
            updateWeather(getWeather(city.woeid))
        }

        return listCities.map { modelConverter.locationModelToDomain(it) }
    }

    override suspend fun addCity(cityId: Int) {
        dao.insert(getWeather(cityId))
    }

    private fun updateWeather(locationModel: LocationModel) {
        dao.update(locationModel)
    }

    override suspend fun deleteCity(cityId: Int) {
        dao.delete(cityId = cityId)
    }

    private suspend fun getWeather(locationId: Int): LocationModel =
        modelConverter.locationApiToModel(api.getLocationById(woeid = locationId))
}