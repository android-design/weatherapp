package com.fedorov.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fedorov.weatherapp.R
import com.fedorov.weatherapp.ui.MainActivity
import com.fedorov.weatherapp.ui.vm.CitiesViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.android.synthetic.main.progressbar_main.*
import javax.inject.Inject

class Cities : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CitiesViewModel> { viewModelFactory }
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        get_weather.setOnClickListener {
            viewModel.updateWeather()
        }

        put_weather1.setOnClickListener {
            viewModel.addCity(2122265)
        }

        put_weather2.setOnClickListener {
            viewModel.addCity(834463)
        }

        viewModel.getShowPB().observe(viewLifecycleOwner, Observer { show ->
            showProgressBar(show)
        })

        viewModel.getData().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                text.text = data.toString()
            }
        })

        fab.setOnClickListener {
            (activity as? MainActivity)?.openSettings()
        }
    }

    private fun showProgressBar(show: Boolean) {
        progressBarGroup?.let {
            when (show) {
                true -> it.visibility = View.VISIBLE
                else -> it.visibility = View.INVISIBLE
            }
        }
    }
}