package com.fedorov.weatherapp.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fedorov.weatherapp.domain.interactor.AddLocationUseCase
import com.fedorov.weatherapp.domain.interactor.GetSearchedLocationsUseCase
import com.fedorov.weatherapp.domain.model.ParentLocation
import com.fedorov.weatherapp.ui.base.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val addLocationUseCase: AddLocationUseCase,
    private val getSearchedLocationsUseCase: GetSearchedLocationsUseCase
) :
    BaseViewModel<List<ParentLocation>>() {

    private val data = MutableLiveData<List<ParentLocation>>()
    private val isShowProgressBar = MutableLiveData<Boolean>()
    private val exception =
        SingleLiveEvent<Exception>()

    fun getShowPB(): LiveData<Boolean> = isShowProgressBar
    fun getException(): LiveData<Exception> = exception
    fun getData(): LiveData<List<ParentLocation>> = data

    fun addCity(cityId: Int) {
        executeInAnotherThread {
            addLocationUseCase.execute(cityId = cityId)
        }
    }

    fun getSearchedLocations(query: String) {
        makeRequest(data, isShowProgressBar, exception) {
            getSearchedLocationsUseCase.execute(query = query)
        }
    }
}