package com.fedorov.weatherapp.di

import com.fedorov.weatherapp.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, SearchModule::class, CitiesModule::class, RepositoryModule::class, NetModule::class])
interface AppComponent : AndroidInjector<App>