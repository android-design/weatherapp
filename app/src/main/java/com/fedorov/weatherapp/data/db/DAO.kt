package com.fedorov.weatherapp.data.db

import com.fedorov.weatherapp.data.db.model.LocationModel

interface DAO {
    fun get(): List<LocationModel>
    fun insert(location: LocationModel)
    fun update(location: LocationModel)
    fun delete(cityId: Int)
}