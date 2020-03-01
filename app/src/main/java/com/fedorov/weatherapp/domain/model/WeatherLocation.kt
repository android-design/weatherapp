package com.fedorov.weatherapp.domain.model

import java.util.*

data class WeatherLocation(
    val time: String,
    val sunRise: Date,
    val sunSet: Date,
    val title: String, // Name of the location
    val woeid: Int,
    val lattLong: String,
    val timezone: String,
    val consolidatedWeather: List<ConsolidatedWeather>
) {
    data class ConsolidatedWeather(
        val id: Long,
        val weatherStateName: String, // Text description of the weather state
        val weatherStateAbbr: String, // One or two letter abbreviation of the weather state
        val windDirectionCompass: String,
        val created: String,
        val applicableDate: Date,
        val minTemp: Double,
        val maxTemp: Double,
        val theTemp: Double,
        val windSpeed: Double,
        val windDirection: Double,
        val airPressure: Double,
        val humidity: Int,
        val visibility: Double,
        val predictability: Int
    )
}