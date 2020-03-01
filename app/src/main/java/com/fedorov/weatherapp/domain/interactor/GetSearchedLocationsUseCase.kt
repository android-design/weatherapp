package com.fedorov.weatherapp.domain.interactor

import com.fedorov.weatherapp.domain.Result
import com.fedorov.weatherapp.domain.base.UseCase
import com.fedorov.weatherapp.domain.model.ParentLocation
import com.fedorov.weatherapp.domain.repository.RepositoryRemote
import javax.inject.Inject

class GetSearchedLocationsUseCase @Inject constructor(
    private val repositoryRemote: RepositoryRemote
) : UseCase<String, Result<List<ParentLocation>>> {
    override suspend fun execute(parameter: String): Result<List<ParentLocation>> = try {
        // Get result from remote by query.
        Result.Success(data = repositoryRemote.searchLocation(locationName = parameter))
    } catch (t: Throwable) {
        Result.Error(message = t.localizedMessage)
    }
}