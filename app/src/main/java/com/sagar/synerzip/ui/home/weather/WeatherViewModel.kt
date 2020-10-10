package com.sagar.synerzip.ui.home.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagar.synerzip.data.db.entities.Weather
import com.sagar.synerzip.data.repository.WeatherRepository
import com.sagar.synerzip.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class WeatherViewModel(
    val repository: WeatherRepository
) : ViewModel() {

    private val weatherMutableLiveData = MutableLiveData<Resource<Weather>>()
    fun getWeatherLiveData(): LiveData<Resource<Weather>> = weatherMutableLiveData


    fun getWeatherForCity(city: String) {
        viewModelScope.launch {
            weatherMutableLiveData.postValue(Resource.loading(null))
            repository.getWeatherForCity(city)
                .catch { e ->
                    weatherMutableLiveData.postValue(Resource.error(e.toString(), null))
                }
                .flowOn(Dispatchers.Default)
                .collect {
                    weatherMutableLiveData.postValue(Resource.success(it))
                }

        }
    }

}