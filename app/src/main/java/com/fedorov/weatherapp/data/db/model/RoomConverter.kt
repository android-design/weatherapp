package com.fedorov.weatherapp.data.db.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class RoomConverter {
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return value?.let { Date(it) }
        }

        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time
        }

    @TypeConverter
    fun fromConsolidateWeatherModel(value: List<LocationModel.ConsolidatedWeatherModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<LocationModel.ConsolidatedWeatherModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toConsolidateWeatherModel(value: String): List<LocationModel.ConsolidatedWeatherModel> {
        val gson = Gson()
        val type = object : TypeToken<List<LocationModel.ConsolidatedWeatherModel>>() {}.type
        return gson.fromJson(value, type)
    }
}