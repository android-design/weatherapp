package com.fedorov.weatherapp.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.fedorov.weatherapp.domain.interactor.AddLocationUseCase
import com.fedorov.weatherapp.domain.interactor.GetSearchedLocationsUseCase
import com.fedorov.weatherapp.domain.model.ParentLocation
import com.fedorov.weatherapp.ui.base.BaseViewModel
import com.fedorov.weatherapp.ui.model.WeatherFound
import com.fedorov.weatherapp.utils.toModelView
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val addLocationUseCase: AddLocationUseCase,
    private val getSearchedLocationsUseCase: GetSearchedLocationsUseCase
) : BaseViewModel() {

    private val dataTest = MutableLiveData<List<ParentLocation>>()
    private val data = Transformations.map(dataTest) { listData ->
        listData.map { it.toModelView() }
    }
    private val isShowProgressBar = MutableLiveData<Boolean>()
    private val exception =
        SingleLiveEvent<String>()

    fun getShowPB(): LiveData<Boolean> = isShowProgressBar
    fun getException(): LiveData<String> = exception
    fun getData(): LiveData<List<WeatherFound>> = data

    fun addCity(cityId: Int) {
        executeInAnotherThread(isShowProgressBar, exception) {
            addLocationUseCase.execute(parameter = cityId)
        }
    }

    fun getSearchedLocations(query: String) {
        makeRequest(dataTest, isShowProgressBar, exception) {
            getSearchedLocationsUseCase.execute(parameter = query)
        }
    }
}