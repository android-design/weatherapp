package com.fedorov.weatherapp.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fedorov.weatherapp.domain.interactor.AddLocationUseCase
import com.fedorov.weatherapp.domain.interactor.UpdateWeatherUseCase
import com.fedorov.weatherapp.domain.model.Location
import com.fedorov.weatherapp.ui.base.BaseViewModel
import javax.inject.Inject

class CitiesViewModel @Inject constructor(
    private val updateWeatherUseCase: UpdateWeatherUseCase,
    private val addLocationUseCase: AddLocationUseCase
) :
    BaseViewModel<List<Location>>() {

    private val data = MutableLiveData<List<Location>>()
    private val isShowProgressBar = MutableLiveData<Boolean>()
    private val exception =
        SingleLiveEvent<Exception>()

    fun getShowPB(): LiveData<Boolean> = isShowProgressBar
    fun getException(): LiveData<Exception> = exception
    fun getData(): LiveData<List<Location>> = data

    fun updateWeather() {
        makeRequest(data, isShowProgressBar, exception) {
            updateWeatherUseCase.execute()
        }
    }

    fun addCity(cityId: Int) {
        makeRequest(data, isShowProgressBar, exception) {
            addLocationUseCase.execute(cityId = cityId)
            updateWeatherUseCase.execute()
        }
    }
}