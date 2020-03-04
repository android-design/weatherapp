package com.fedorov.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.fedorov.weatherapp.data.db.DAO
import com.fedorov.weatherapp.data.db.DatabaseRoom
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): DatabaseRoom {
        return Room.databaseBuilder(
            context.applicationContext,
            DatabaseRoom::class.java,
            "Weather.db"
        ).build()
    }

    @Provides
    fun getLocalStorage(database: DatabaseRoom): DAO = database.dao()
}