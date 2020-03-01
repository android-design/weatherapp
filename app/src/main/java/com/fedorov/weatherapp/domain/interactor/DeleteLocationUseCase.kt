package com.fedorov.weatherapp.domain.interactor

import com.fedorov.weatherapp.domain.base.UseCase
import com.fedorov.weatherapp.domain.repository.RepositoryLocal
import javax.inject.Inject

class DeleteLocationUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) : UseCase<Int, Unit> {
    override suspend fun execute(parameter: Int) {
        // Delete city from local storage.
        repositoryLocal.deleteCity(parameter)
    }
}