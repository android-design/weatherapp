package com.fedorov.weatherapp.data.converter

import com.fedorov.weatherapp.data.db.model.LocationModel
import com.fedorov.weatherapp.data.service.model.LocationApi
import com.fedorov.weatherapp.data.service.model.ParentLocationApi
import com.fedorov.weatherapp.domain.model.ParentLocation
import com.fedorov.weatherapp.domain.model.WeatherLocation
import java.text.SimpleDateFormat
import java.util.*

val inputDateFormatHeader by lazy { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US) }
val inputDateFormatDetailWeather by lazy { SimpleDateFormat("yyyy-MM-dd", Locale.US) }

fun LocationApi.toDomain(): WeatherLocation = WeatherLocation(title,
    inputDateFormatHeader.parse(sunRise) ?: Date(0),
    inputDateFormatHeader.parse(sunSet) ?: Date(0),
    title,
    woeid,
    lattLong,
    timezone,
    consolidatedWeather.map {
        WeatherLocation.ConsolidatedWeather(
            it.id,
            it.weatherStateName,
            it.weatherStateAbbr,
            it.windDirectionCompass,
            it.created,
            inputDateFormatDetailWeather.parse(it.applicableDate) ?: Date(0),
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

fun ParentLocationApi.toDomain(): ParentLocation =
    ParentLocation(
        title,
        locationType,
        woeid,
        lattLong
    )

fun LocationModel.toDomain(): WeatherLocation = WeatherLocation(
    time,
    sunRise,
    sunSet,
    title,
    woeid,
    lattLong,
    timezone,
    consolidatedWeather.map {
        WeatherLocation.ConsolidatedWeather(
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

fun WeatherLocation.toModel(): LocationModel = LocationModel(
    time,
    sunRise,
    sunSet,
    title,
    woeid,
    lattLong,
    timezone,
    consolidatedWeather.map {
        LocationModel.ConsolidatedWeatherModel(
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
    }
)