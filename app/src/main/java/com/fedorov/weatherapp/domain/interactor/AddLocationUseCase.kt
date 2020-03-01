package com.fedorov.weatherapp.domain.interactor

import com.fedorov.weatherapp.domain.base.UseCase
import com.fedorov.weatherapp.domain.repository.RepositoryLocal
import com.fedorov.weatherapp.domain.repository.RepositoryRemote
import javax.inject.Inject

class AddLocationUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal,
    private val repositoryRemote: RepositoryRemote
) : UseCase<Int, Unit> {
    override suspend fun execute(parameter: Int) {
        // Get weather for location by id.
        val weatherLocation = repositoryRemote.getWeather(locationId = parameter)
        // Put to local storage.
        repositoryLocal.addCity(weatherLocation)
    }
}