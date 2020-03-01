package com.fedorov.weatherapp.domain.base

interface UseCaseWithoutParameter<R> {
    suspend fun execute(): R
}