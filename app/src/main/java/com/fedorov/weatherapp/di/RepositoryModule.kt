package com.fedorov.weatherapp.di

import com.fedorov.weatherapp.data.RepositoryLocalImpl
import com.fedorov.weatherapp.domain.repository.RepositoryRemote
import com.fedorov.weatherapp.data.RepositoryRemoteImpl
import com.fedorov.weatherapp.domain.repository.RepositoryLocal
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepositoryRemote(repository: RepositoryRemoteImpl): RepositoryRemote

    @Binds
    @Singleton
    abstract fun provideRepositoryLocal(repository: RepositoryLocalImpl): RepositoryLocal
}