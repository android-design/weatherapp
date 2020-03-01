package com.fedorov.weatherapp.ui

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.fedorov.weatherapp.R
import com.fedorov.weatherapp.ui.fragment.CitiesDirections

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.host_fragment) }
    private val navHost by lazy { supportFragmentManager.findFragmentById(R.id.host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun openSettings() {
        val directions = CitiesDirections.actionCitiesFragmentToPreferenceFragment()
        navController.navigate(directions)
    }
}