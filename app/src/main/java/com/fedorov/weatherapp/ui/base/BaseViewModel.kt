package com.fedorov.weatherapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.weatherapp.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {
    fun <E> makeRequest(
        data: MutableLiveData<E>,
        showProgressBar: MutableLiveData<Boolean>,
        exception: MutableLiveData<String>,
        request: suspend () -> Result<E>
    ) = viewModelScope.launch(Dispatchers.IO) {
        showProgressBar.postValue(true)

        val response = request.invoke()

        Timber.d(response.toString())

        when (response) {
            is Result.Success<E> -> data.postValue(response.data)
            is Result.Error<E> -> exception.postValue(response.message)
        }

        showProgressBar.postValue(false)
    }

    fun executeInAnotherThread(
        showProgressBar: MutableLiveData<Boolean>,
        exception: MutableLiveData<String>,
        callback: suspend () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            showProgressBar.postValue(true)
            try {
                callback.invoke()
            } catch (t: Throwable) {
                exception.postValue(t.localizedMessage)
            }
            showProgressBar.postValue(false)
        }
    }
}