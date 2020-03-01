package com.fedorov.weatherapp.di

import com.fedorov.weatherapp.data.db.DAO
import com.fedorov.weatherapp.data.db.DaoImpl
import dagger.Module
import dagger.Provides

@Module
class LocalStorageModule {
    @Provides
    fun getLocalStorage(): DAO = DaoImpl()
}