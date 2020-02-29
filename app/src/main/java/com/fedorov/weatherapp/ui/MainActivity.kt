package com.fedorov.weatherapp.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.fedorov.weatherapp.R
import com.fedorov.weatherapp.ui.fragment.CitiesDirections
import com.fedorov.weatherapp.ui.fragment.Search
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.host_fragment) }
    private val navHost by lazy { supportFragmentManager.findFragmentById(R.id.host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAppBar()
        initSearchView()
    }

    private fun initAppBar() {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        //val mmm = findViewById<Menu>(R.menu.main_menu)
        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.preferenceFragment) {
                search_view_appbar.visibility = View.VISIBLE
            } else {
                search_view_appbar.visibility = View.INVISIBLE
            }
        }
    }

    private fun initSearchView() {
        search_view_appbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                navHost?.let { navFragment ->
                    navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                        (fragment as Search?)?.findLocations(query)
                    }
                }

                hideSoftKeyboard()
                search_view_appbar.clearFocus()

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    fun hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                it.windowToken,
                0
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    fun openSettings() {
        val directions = CitiesDirections.actionCitiesFragmentToPreferenceFragment()
        navController.navigate(directions)
    }
}
