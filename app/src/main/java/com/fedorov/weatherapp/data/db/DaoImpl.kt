package com.fedorov.weatherapp.data.db

import com.fedorov.weatherapp.data.db.model.LocationModel

class DaoImpl : DAO {
    private val db: MutableMap<Int, LocationModel> = HashMap()

    override fun read(): List<LocationModel> =
        db.map { it.value }

    override fun create(location: LocationModel) {
        db[location.woeid] = location
    }

    override fun update(location: LocationModel) {
        db[location.woeid] = location
    }

    override fun delete(cityId: Int) {
        db.remove(cityId)
    }
}