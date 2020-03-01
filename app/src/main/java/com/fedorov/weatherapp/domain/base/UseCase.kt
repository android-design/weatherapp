package com.fedorov.weatherapp.domain.base

interface UseCase<P, R> {
    suspend fun execute(parameter: P): R
}