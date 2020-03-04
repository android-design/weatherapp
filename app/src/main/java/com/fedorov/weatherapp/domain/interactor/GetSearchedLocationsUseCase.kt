package com.fedorov.weatherapp.domain.interactor

import com.fedorov.weatherapp.domain.Result
import com.fedorov.weatherapp.domain.base.UseCase
import com.fedorov.weatherapp.domain.model.ParentLocation
import com.fedorov.weatherapp.domain.model.WeatherLocation
import com.fedorov.weatherapp.domain.repository.RepositoryRemote
import com.fedorov.weatherapp.ui.model.CityWeather
import javax.inject.Inject

class GetSearchedLocationsUseCase @Inject constructor(
    private val repositoryRemote: RepositoryRemote
) : UseCase<String, Result<List<WeatherLocation>>> {
    override suspend fun execute(parameter: String): Result<List<WeatherLocation>> = try {
        val data = repositoryRemote.searchLocation(locationName = parameter)
        val newData = data.map {
            repositoryRemote.getWeather(it.woeid)
        }
        // Get result from remote by query.
        Result.Success(newData)
    } catch (t: Throwable) {
        Result.Error(message = t.localizedMessage)
    }
}