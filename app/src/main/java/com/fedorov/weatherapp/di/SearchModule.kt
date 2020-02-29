package com.fedorov.weatherapp.di

import androidx.lifecycle.ViewModel
import com.fedorov.weatherapp.ui.fragment.Search
import com.fedorov.weatherapp.ui.vm.CitiesViewModel
import com.fedorov.weatherapp.ui.vm.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module for the SearchAlbumFragment.
 */
@Module
abstract class SearchModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun preferenceFragment(): Search

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindViewModel(viewModel: SearchViewModel): ViewModel
}
