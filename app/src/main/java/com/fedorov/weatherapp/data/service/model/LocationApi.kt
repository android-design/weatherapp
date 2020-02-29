package com.fedorov.weatherapp.data.service.model

import com.google.gson.annotations.SerializedName


data class LocationApi(
    @SerializedName("time")
    val time: String,
    @SerializedName("sun_rise")
    val sunRise: String,
    @SerializedName("sun_set")
    val sunSet: String,
    @SerializedName("title")
    val title: String, // Name of the location
    @SerializedName("woeid")
    val woeid: Int,
    @SerializedName("latt_long")
    val lattLong: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeatherApi>
) {
    data class ConsolidatedWeatherApi(
        @SerializedName("id")
        val id: Long,
        @SerializedName("weather_state_name")
        val weatherStateName: String, // Text description of the weather state
        @SerializedName("weather_state_abbr")
        val weatherStateAbbr: String, // One or two letter abbreviation of the weather state
        @SerializedName("wind_direction_compass")
        val windDirectionCompass: String,
        @SerializedName("created")
        val created: String,
        @SerializedName("applicable_date")
        val applicableDate: String,
        @SerializedName("min_temp")
        val minTemp: Double,
        @SerializedName("max_temp")
        val maxTemp: Double,
        @SerializedName("the_temp")
        val theTemp: Double,
        @SerializedName("wind_speed")
        val windSpeed: Double,
        @SerializedName("wind_direction")
        val windDirection: Double,
        @SerializedName("air_pressure")
        val airPressure: Double,
        @SerializedName("humidity")
        val humidity: Int,
        @SerializedName("visibility")
        val visibility: Double,
        @SerializedName("predictability")
        val predictability: Int
    )
}