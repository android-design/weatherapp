package com.fedorov.weatherapp.domain.interactor

import com.fedorov.weatherapp.domain.repository.Repository
import com.fedorov.weatherapp.domain.model.ParentLocation
import javax.inject.Inject

class AddLocationUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(cityId: Int) {
        return repository.addCity(cityId = cityId)
    }
}