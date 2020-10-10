package com.sagar.synerzip.ui.home.weather

import androidx.lifecycle.ViewModel
import com.sagar.synerzip.data.repository.WeatherRepository

class WeatherViewModel(
    val repository: WeatherRepository
) : ViewModel() {

}