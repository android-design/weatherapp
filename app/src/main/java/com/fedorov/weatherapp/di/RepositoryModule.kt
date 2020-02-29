package com.fedorov.weatherapp.di

import com.fedorov.weatherapp.domain.repository.Repository
import com.fedorov.weatherapp.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideCache(repository: RepositoryImpl): Repository
}