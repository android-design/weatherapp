package com.fedorov.weatherapp.di

import android.content.Context
import com.fedorov.weatherapp.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, SearchModule::class, CitiesModule::class, RepositoryModule::class, RemoteModule::class, LocalStorageModule::class])
interface AppComponent : AndroidInjector<App>{
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}