package com.sagar.synerzip

import android.app.Application
import com.sagar.synerzip.data.db.AppDataBase
import com.sagar.synerzip.data.network.MyApi
import com.sagar.synerzip.data.network.NetworkConnectionInterceptor
import com.sagar.synerzip.data.preferences.PreferenceProvider
import com.sagar.synerzip.data.repository.WeatherRepository
import com.sagar.synerzip.ui.home.weather.WeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDataBase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { WeatherRepository(instance(), instance(), instance()) }
        bind() from provider { WeatherViewModelFactory(instance()) }

    }
}