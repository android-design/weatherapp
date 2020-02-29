package com.fedorov.weatherapp.data.converter

import com.fedorov.weatherapp.data.db.model.LocationModel
import com.fedorov.weatherapp.data.service.model.LocationApi
import com.fedorov.weatherapp.data.service.model.ParentLocationApi
import com.fedorov.weatherapp.domain.model.Location
import com.fedorov.weatherapp.domain.model.ParentLocation

interface ModelConverter {
    fun locationApiToModel(locatonApi: LocationApi): LocationModel
    fun locationModelToDomain(locationModel: LocationModel): Location
    fun parentLocationApiToDomain(parentLocationApi: ParentLocationApi): ParentLocation
}