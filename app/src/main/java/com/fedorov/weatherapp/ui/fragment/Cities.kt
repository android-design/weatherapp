package com.fedorov.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.fedorov.weatherapp.R
import com.fedorov.weatherapp.ui.adapter.WeatherAdapter
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
    private val mAdapter by lazy { WeatherAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateWeather()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        get_weather.setOnClickListener {
            viewModel.updateWeather()
        }

        viewModel.getShowPB().observe(viewLifecycleOwner, Observer { show ->
            showProgressBar(show)
        })

        viewModel.getData().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                mAdapter.data = data
            }
        })

        fab.setOnClickListener {
            val directions = CitiesDirections.actionCitiesFragmentToPreferenceFragment()
            navController.navigate(directions)
        }
    }

    private fun initRecyclerView() {
        val snapHelper = PagerSnapHelper()

        main_rv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = mAdapter
        }
        snapHelper.attachToRecyclerView(main_rv)
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