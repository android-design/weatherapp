package com.fedorov.weatherapp.ui.model

sealed class WeatherData

data class City(
    val name: String
) : WeatherData()

data class Weather(
    val temp: String
) : WeatherData()