package com.fedorov.weatherapp.domain.interactor

import com.fedorov.weatherapp.domain.Result
import com.fedorov.weatherapp.domain.base.UseCaseWithoutParameter
import com.fedorov.weatherapp.domain.model.WeatherLocation
import com.fedorov.weatherapp.domain.repository.RepositoryLocal
import com.fedorov.weatherapp.domain.repository.RepositoryRemote
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class UpdateWeatherUseCase @Inject constructor(
    private val repositoryRemote: RepositoryRemote,
    private val repositoryLocal: RepositoryLocal
) : UseCaseWithoutParameter<Result<List<WeatherLocation>>> {
    override suspend fun execute(): Result<List<WeatherLocation>> {
        return try {
            // Get locations for update.
            val data = repositoryLocal.getAllLocations()

            // Update weather from remote and put to local.
            for (location in data) {
                repositoryRemote.getWeather(location.woeid)
                repositoryLocal.updateCityWeather(location)
            }
            // Get locations from local.
            Result.Success(repositoryLocal.getAllLocations())
        } catch (t: Throwable) {
            when (t) {
                is SocketTimeoutException, is UnknownHostException -> Result.Success(repositoryLocal.getAllLocations())
                else -> Result.Error(message = t.localizedMessage)
            }
        }
    }
}