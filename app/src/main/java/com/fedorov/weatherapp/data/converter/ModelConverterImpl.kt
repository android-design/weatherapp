package com.fedorov.weatherapp.data.converter

import com.fedorov.weatherapp.data.db.model.LocationModel
import com.fedorov.weatherapp.data.service.model.LocationApi
import com.fedorov.weatherapp.data.service.model.ParentLocationApi
import com.fedorov.weatherapp.domain.model.Location
import com.fedorov.weatherapp.domain.model.ParentLocation
import java.text.SimpleDateFormat
import java.util.*

val formatter: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")

class ModelConverterImpl : ModelConverter {
    override fun locationApiToModel(locatonApi: LocationApi): LocationModel =
        LocationModel(locatonApi.title, locatonApi.sunRise, locatonApi.sunSet,
            locatonApi.title, locatonApi.woeid,
            locatonApi.lattLong,
            locatonApi.timezone,
            locatonApi.consolidatedWeather.map {
                LocationModel.ConsolidatedWeatherModel(
                    it.id,
                    it.weatherStateName,
                    it.weatherStateAbbr,
                    it.windDirectionCompass,
                    it.created,
                    formatter.parse(it.applicableDate) ?: Date(0),
                    it.minTemp,
                    it.maxTemp,
                    it.theTemp,
                    it.windSpeed,
                    it.windDirection,
                    it.airPressure,
                    it.humidity,
                    it.visibility,
                    it.predictability
                )
            }
        )

    override fun locationModelToDomain(locationModel: LocationModel): Location = Location(
        locationModel.time,
        locationModel.sunRise,
        locationModel.sunSet,
        locationModel.title,
        locationModel.woeid,
        locationModel.lattLong,
        locationModel.timezone,
        locationModel.consolidatedWeather.map {
            Location.ConsolidatedWeather(
                it.id,
                it.weatherStateName,
                it.weatherStateAbbr,
                it.windDirectionCompass,
                it.created,
                it.applicableDate,
                it.minTemp,
                it.maxTemp,
                it.theTemp,
                it.windSpeed,
                it.windDirection,
                it.airPressure,
                it.humidity,
                it.visibility,
                it.predictability
            )
        })

    override fun parentLocationApiToDomain(parentLocationApi: ParentLocationApi): ParentLocation =
        ParentLocation(
            parentLocationApi.title,
            parentLocationApi.locationType,
            parentLocationApi.woeid,
            parentLocationApi.lattLong
        )
}