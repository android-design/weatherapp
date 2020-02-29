package com.fedorov.weatherapp.domain.interactor

import com.fedorov.weatherapp.domain.repository.Repository
import javax.inject.Inject

class DeleteLocationUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(cityId: Int) {
        return repository.deleteCity(cityId = cityId)
    }
}