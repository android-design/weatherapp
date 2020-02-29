package com.fedorov.weatherapp.ui.mapper

import com.fedorov.weatherapp.domain.model.Location
import com.fedorov.weatherapp.ui.model.City
import com.fedorov.weatherapp.ui.model.WeatherData
import javax.inject.Inject

class WeatherModelViewMapper @Inject constructor() {
    fun domainModelToWeatherData(location: Location): WeatherData {
        return City(location.title)
    }
}