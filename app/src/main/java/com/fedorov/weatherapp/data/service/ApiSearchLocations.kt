package com.fedorov.weatherapp.data.service

import com.fedorov.weatherapp.data.service.model.ParentLocationApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSearchLocations {
    @GET("/api/location/search/?")
    suspend fun getLocationsByQuery(@Query("query") locationName: String): List<ParentLocationApi>

    @GET("/api/location/search/?")
    suspend fun getLocationsByLatitude(@Query(value = "lattlong") lattlong: Float): List<ParentLocationApi>
}