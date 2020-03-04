package com.fedorov.weatherapp.ui.mapper

import com.fedorov.weatherapp.R
import com.fedorov.weatherapp.domain.model.WeatherLocation
import com.fedorov.weatherapp.ui.model.CityWeather
import com.fedorov.weatherapp.ui.model.WeatherFound
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

val outputDateFormatHeader by lazy { SimpleDateFormat("E, dd MMMM", Locale.US) }
val outputDateFormatDetailWeather by lazy { SimpleDateFormat("dd.MM.yyyy", Locale.US) }
val outputDateFormatHours by lazy { SimpleDateFormat("HH:mm:ss", Locale.US) }

fun WeatherLocation.toModelViewCityWeather(): CityWeather {
    var temperature = ""
    var imgId = 0
    var date = ""
    var minTemp = ""
    var maxTemp = ""
    var windDirection = ""
    var weatherStateName = ""
    var windSpeed = ""

    consolidatedWeather.firstOrNull()?.let {
        temperature = "${it.theTemp.roundToInt()}℃"
        imgId =
            imageResolver(it.weatherStateAbbr)
        date = outputDateFormatHeader.format(it.applicableDate)
        minTemp = "${it.minTemp.roundToInt()}℃"
        maxTemp = "${it.maxTemp.roundToInt()}℃"
        windDirection = it.windDirectionCompass
        weatherStateName = it.weatherStateName
        windSpeed = "${it.windSpeed.roundToInt()} km/h"
    }

    return CityWeather(
        id = woeid,
        date = date,
        imgId = imgId,
        locationName = title,
        weatherStateName = weatherStateName,
        temp = temperature,
        lattLong = lattLong,
        minTemp = minTemp,
        maxTemp = maxTemp,
        windDirection = windDirection,
        windSpeed = windSpeed,
        sunRise = outputDateFormatHours.format(sunRise),
        sunSet = outputDateFormatHours.format(sunSet),
        timezone = timezone,
        weakWeather = consolidatedWeather.map {
            CityWeather.WeakWeather(
                it.weatherStateName,
                "https://www.metaweather.com/static/img/weather/png/${it.weatherStateAbbr}.png",
                outputDateFormatDetailWeather.format(it.applicableDate),
                "${it.minTemp.roundToInt()}℃",
                "${it.maxTemp.roundToInt()}℃",
                it.theTemp.toString(),
                "${it.windSpeed.roundToInt()} km/h",
                it.windDirectionCompass,
                it.airPressure.toString(),
                it.humidity.toString(),
                it.visibility.toString()
            )
        }
    )
}

fun WeatherLocation.toModelViewWeatherFound(): WeatherFound {
    var temperature = ""
    var imgId = 0

    consolidatedWeather.firstOrNull()?.let {
        temperature = it.theTemp.roundToInt().run {
            if (this > 0) "+$this"
            else "-$this"
        }

        imgId =
            imageResolver(it.weatherStateAbbr)
    }

    return WeatherFound(
        woeid = woeid,
        name = title,
        temperature = temperature,
        imgId = imgId
    )
}

enum class WeatherImage(val image: Int) {
    SNOW(R.drawable.sn),
    SLEET(R.drawable.sl),
    HAIL(R.drawable.h),
    THUNDERSTORM(R.drawable.t),
    HEAVYRAIN(R.drawable.hr),
    LIGHTRAIN(R.drawable.lr),
    SHOWERS(R.drawable.s),
    HEAVYCLOUD(R.drawable.hc),
    LIGHTCLOUD(R.drawable.lc),
    CLEAR(R.drawable.c),
    UNKNOW(R.drawable.uk)
}

fun imageResolver(imageForSearch: String) = when (imageForSearch) {
    "sn" -> WeatherImage.SNOW.image
    "sl" -> WeatherImage.SLEET.image
    "hl" -> WeatherImage.HAIL.image
    "t" -> WeatherImage.THUNDERSTORM.image
    "hr" -> WeatherImage.HEAVYRAIN.image
    "lr" -> WeatherImage.LIGHTRAIN.image
    "s" -> WeatherImage.SHOWERS.image
    "hc" -> WeatherImage.HEAVYCLOUD.image
    "lc" -> WeatherImage.LIGHTCLOUD.image
    "c" -> WeatherImage.CLEAR.image
    else -> WeatherImage.UNKNOW.image
}