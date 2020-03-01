package com.fedorov.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.fedorov.weatherapp.R
import com.fedorov.weatherapp.ui.vm.SearchViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.progressbar_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class Search : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SearchViewModel> { viewModelFactory }
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preference, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))
        initSearchView()
    }

    private fun initSearchView() {
        search_view_appbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty())
                Toast.makeText(context, newText, Toast.LENGTH_SHORT).show()
                //viewModel.getSearchedLocations(query = newText)

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