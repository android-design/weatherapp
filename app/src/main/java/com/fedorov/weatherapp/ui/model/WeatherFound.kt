package com.fedorov.weatherapp.ui.model

data class WeatherFound(
    val woeid: Int,
    val name: String,
    val temperature: String,
    val imgId: Int
)