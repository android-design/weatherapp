package com.fedorov.weatherapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<E> : ViewModel() {
    fun makeRequest(
        data: MutableLiveData<E>,
        showProgressBar: MutableLiveData<Boolean>,
        exception: MutableLiveData<Exception>,
        request: suspend () -> E
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            showProgressBar.postValue(true)
            val response = request.invoke()
            Timber.d(response.toString())
            data.postValue(response)
        } catch (e: Exception) {
            Timber.e(e)
            when (e) {
                !is CancellationException -> {
                    exception.postValue(e)
                }
            }
        } finally {
            showProgressBar.postValue(false)
        }
    }

    fun executeInAnotherThread(callback: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            callback.invoke()
        }
    }
}