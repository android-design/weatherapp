package com.fedorov.weatherapp.domain.interactor

import com.fedorov.weatherapp.domain.repository.Repository
import com.fedorov.weatherapp.domain.model.ParentLocation
import javax.inject.Inject

class GetSearchedLocationsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(query: String): List<ParentLocation> {
        return repository.searchLocation(locationName = query)
    }
}