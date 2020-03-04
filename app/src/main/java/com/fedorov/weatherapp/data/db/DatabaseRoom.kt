package com.fedorov.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fedorov.weatherapp.data.db.model.RoomConverter
import com.fedorov.weatherapp.data.db.model.LocationModel


@Database(entities = [LocationModel::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverter::class)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun dao(): DAO
}