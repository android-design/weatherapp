package com.fedorov.weatherapp.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.fedorov.weatherapp.domain.interactor.AddLocationUseCase
import com.fedorov.weatherapp.domain.interactor.GetSearchedLocationsUseCase
import com.fedorov.weatherapp.domain.model.WeatherLocation
import com.fedorov.weatherapp.ui.base.BaseViewModel
import com.fedorov.weatherapp.ui.model.WeatherFound
import com.fedorov.weatherapp.ui.mapper.toModelViewWeatherFound
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val addLocationUseCase: AddLocationUseCase,
    private val getSearchedLocationsUseCase: GetSearchedLocationsUseCase
) : BaseViewModel() {

    private val dataTest = MutableLiveData<List<WeatherLocation>>()
    private val data = Transformations.map(dataTest) { listData ->
        listData.map { it.toModelViewWeatherFound() }
    }
    private val isShowProgressBar = MutableLiveData<Boolean>()
    private val exception =
        SingleLiveEvent<String>()

    fun getShowPB(): LiveData<Boolean> = isShowProgressBar
    fun getException(): LiveData<String> = exception
    fun getData(): LiveData<List<WeatherFound>> = data

    fun addCity(locationId: Int) {
        executeInAnotherThread(isShowProgressBar, exception) {
            addLocationUseCase.execute(parameter = locationId)
        }
    }

    fun getSearchedLocations(query: String) {
        makeRequest(dataTest, isShowProgressBar, exception) {
            getSearchedLocationsUseCase.execute(parameter = query)
        }
    }
}