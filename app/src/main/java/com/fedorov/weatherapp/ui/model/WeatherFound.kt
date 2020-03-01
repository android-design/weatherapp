package com.fedorov.weatherapp.ui.model

data class WeatherFound(
    val title:String,
    val locationType: String,
    val woeid: Int,
    val lattLong: String
)