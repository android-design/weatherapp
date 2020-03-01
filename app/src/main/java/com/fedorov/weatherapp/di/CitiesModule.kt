package com.fedorov.weatherapp.di

import androidx.lifecycle.ViewModel
import com.fedorov.weatherapp.ui.fragment.Cities
import com.fedorov.weatherapp.ui.vm.CitiesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CitiesModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun citiesFragment(): Cities

    @Binds
    @IntoMap
    @ViewModelKey(CitiesViewModel::class)
    abstract fun bindViewModel(viewModel: CitiesViewModel): ViewModel
}
