package com.fedorov.weatherapp.data.db

import com.fedorov.weatherapp.data.db.model.LocationModel

interface DAO {
    fun create(location: LocationModel)
    fun read(): List<LocationModel>
    fun update(location: LocationModel)
    fun delete(cityId: Int)
}