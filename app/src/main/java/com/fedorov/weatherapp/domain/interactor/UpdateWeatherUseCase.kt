package com.fedorov.weatherapp.domain.interactor

import com.fedorov.weatherapp.domain.repository.Repository
import com.fedorov.weatherapp.domain.model.Location
import javax.inject.Inject

class UpdateWeatherUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(): List<Location> {
        return repository.updateAllAndGet()
    }
}