package com.fedorov.weatherapp.utils

import com.fedorov.weatherapp.data.db.model.LocationModel
import com.fedorov.weatherapp.data.service.model.LocationApi
import com.fedorov.weatherapp.data.service.model.ParentLocationApi
import com.fedorov.weatherapp.domain.model.WeatherLocation
import com.fedorov.weatherapp.domain.model.ParentLocation
import com.fedorov.weatherapp.ui.model.CityWeather
import com.fedorov.weatherapp.ui.model.WeatherFound
import java.text.SimpleDateFormat
import java.util.*

val inputDateFormatHeader by lazy { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US) }
val inputDateFormatDetailWeather by lazy { SimpleDateFormat("yyyy-MM-dd", Locale.US) }
val outputDateFormatHeader by lazy { SimpleDateFormat("HH:mm", Locale.US) }
val outputDateFormatDetailWeather by lazy { SimpleDateFormat("dd.MM.yyyy", Locale.US) }

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

fun ParentLocationApi.toDomain(): ParentLocation =
    ParentLocation(
        title,
        locationType,
        woeid,
        lattLong
    )

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

fun WeatherLocation.toModelView(): CityWeather = CityWeather(
    title,
    woeid,
    lattLong,
    outputDateFormatHeader.format(sunRise),
    outputDateFormatHeader.format(sunSet),
    timezone,
    consolidatedWeather.map {
        CityWeather.WeakWeather(
            it.weatherStateName,
            "https://www.metaweather.com/static/img/weather/${it.weatherStateAbbr}.svg",
            outputDateFormatDetailWeather.format(it.applicableDate),
            it.minTemp.toString(),
            it.maxTemp.toString(),
            it.theTemp.toString(),
            it.windSpeed.toString(),
            it.windDirection.toString(),
            it.airPressure.toString(),
            it.humidity.toString(),
            it.visibility.toString()
        )
    }
)

fun ParentLocation.toModelView(): WeatherFound = WeatherFound(
    title,
    locationType,
    woeid,
    lattLong
)