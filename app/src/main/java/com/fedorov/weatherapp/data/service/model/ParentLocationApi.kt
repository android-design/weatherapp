package com.fedorov.weatherapp.data.service.model

import com.google.gson.annotations.SerializedName


data class ParentLocationApi(
    @SerializedName("title")
    val title: String,
    @SerializedName("location_type")
    val locationType: String,
    @SerializedName("woeid")
    val woeid: Int,
    @SerializedName("latt_long")
    val lattLong: String
)