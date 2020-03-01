package com.fedorov.weatherapp.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.fedorov.weatherapp.domain.interactor.AddLocationUseCase
import com.fedorov.weatherapp.domain.interactor.UpdateWeatherUseCase
import com.fedorov.weatherapp.domain.model.WeatherLocation
import com.fedorov.weatherapp.ui.base.BaseViewModel
import com.fedorov.weatherapp.ui.model.CityWeather
import com.fedorov.weatherapp.utils.toModelView
import javax.inject.Inject

class CitiesViewModel @Inject constructor(
    private val updateWeatherUseCase: UpdateWeatherUseCase,
    private val addLocationUseCase: AddLocationUseCase
) : BaseViewModel() {

    private val dataTest = MutableLiveData<List<WeatherLocation>>()
    private val data = Transformations.map(dataTest) { listData ->
        listData.map { it.toModelView() }
    }
    private val isShowProgressBar = MutableLiveData<Boolean>()
    private val exception = SingleLiveEvent<String>()

    fun getShowPB(): LiveData<Boolean> = isShowProgressBar
    fun getException(): LiveData<String> = exception
    fun getData(): LiveData<List<CityWeather>?> = data

    fun updateWeather() {
        makeRequest(dataTest, isShowProgressBar, exception) {
            updateWeatherUseCase.execute()
        }
    }

    fun addCity(cityId: Int) {
        executeInAnotherThread(isShowProgressBar, exception) {
            addLocationUseCase.execute(parameter = cityId)
        }
    }
}