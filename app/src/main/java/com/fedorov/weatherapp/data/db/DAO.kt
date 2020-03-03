package com.fedorov.weatherapp.data.db

import androidx.room.*
import com.fedorov.weatherapp.data.db.model.LocationModel

@Dao
interface DAO {
    @Insert
    fun create(location: LocationModel)

    @Query("SELECT * FROM locationmodel ORDER BY title ASC")
    fun getAll(): List<LocationModel>

    @Update
    fun update(location: LocationModel)

    @Query("DELETE FROM locationmodel WHERE woeid = :cityId")
    fun delete(cityId: Int)
}