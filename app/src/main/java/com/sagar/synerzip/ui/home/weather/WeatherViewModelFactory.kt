package com.sagar.synerzip.ui.home.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sagar.synerzip.data.repository.WeatherRepository

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(
    private val repository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository) as T
    }
}