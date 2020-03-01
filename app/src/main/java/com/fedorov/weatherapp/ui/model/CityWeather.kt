package com.fedorov.weatherapp.ui.model

data class CityWeather(
    val name: String,
    val id: Int,
    val lattLong: String,
    val sunRise: String,
    val sunSet: String,
    val timezone: String,
    val weakWeather: List<WeakWeather>
) {
    data class WeakWeather(
        val title: String,
        val url: String,
        val date: String,
        val minTemp: String,
        val maxTemp: String,
        val theTemp: String,
        val windSpeed: String,
        val windDirection: String,
        val airPressure: String,
        val humidity: String,
        val visibility: String
    )
}