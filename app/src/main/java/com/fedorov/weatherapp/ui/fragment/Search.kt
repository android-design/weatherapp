package com.fedorov.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fedorov.weatherapp.R
import com.fedorov.weatherapp.ui.adapter.SearchWeatherAdapter
import com.fedorov.weatherapp.ui.vm.SearchViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.progressbar_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class Search : DaggerFragment() {

    interface OnLocationSelectedListener {
        fun addLocation(locationId: Int)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SearchViewModel> { viewModelFactory }
    private val navController by lazy { findNavController() }
    private val mAdapter by lazy { SearchWeatherAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))
        initSearchView()
        initRecyclerView()

        viewModel.getShowPB().observe(viewLifecycleOwner, Observer { show ->
            showProgressBar(show)
        })

        viewModel.getData().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                if (data.isEmpty()) Toast.makeText(
                    context,
                    getString(R.string.locations_not_found),
                    Toast.LENGTH_SHORT
                ).show()

                mAdapter.data = data
            }
        })
    }

    private fun initRecyclerView() {
        rv_search_items.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

        mAdapter.listener = object : OnLocationSelectedListener {
            override fun addLocation(locationId: Int) {
                viewModel.addCity(locationId = locationId)
                Toast.makeText(context, getString(R.string.city_was_added), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun initSearchView() {
        search_view_appbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearchedLocations(query = query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
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