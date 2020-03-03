package com.fedorov.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fedorov.weatherapp.data.db.model.LocationModel


@Database(entities = [LocationModel::class], version = 1, exportSchema = false)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun dao(): DAO
}