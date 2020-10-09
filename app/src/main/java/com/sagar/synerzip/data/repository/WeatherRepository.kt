package com.sagar.synerzip.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sagar.synerzip.data.db.AppDataBase
import com.sagar.synerzip.data.db.entities.Weather
import com.sagar.synerzip.data.network.MyApi
import com.sagar.synerzip.data.network.SafeApiRequest
import com.sagar.synerzip.data.network.responses.mapToWeather
import com.sagar.synerzip.data.preferences.PreferenceProvider
import com.sagar.synerzip.util.AppDateFormat
import com.sagar.synerzip.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit

private const val MINIMUM_INTERVAL = 24

class WeatherRepository(
    private val api: MyApi,
    private val db: AppDataBase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    private val weather = MutableLiveData<Weather>()
    private var interestedCity = ""

    init {
        weather.observeForever {
            prefs.saveSavedAt(interestedCity, AppDateFormat.df_Date.format(Date()))
            saveWeatherOfCity(it)
        }

    }

    private fun saveWeatherOfCity(weather: Weather) {
        Coroutines.io {
            db.getWeatherDao().insertWeather(weather)
        }
    }

    suspend fun getWeatherForCity(city: String): LiveData<Weather> {
        return withContext(Dispatchers.IO) {
            fetchWeatherForCity(city)
            db.getWeatherDao().getWeatherForCity(city)
        }
    }

    private suspend fun fetchWeatherForCity(city: String) {
        interestedCity = city
        val lastSavedAt = prefs.getSavedAt(interestedCity)

        if (lastSavedAt == null || isFetchNeeded(lastSavedAt)) {
            val weatherResponse = apiRequest { api.getWeatherByCity(interestedCity) }
            weather.postValue(weatherResponse.mapToWeather())
        }
    }

    private fun isFetchNeeded(lastSavedAt: String): Boolean {
        val duration = Date().time - AppDateFormat.df_Date.parse(lastSavedAt).time
        return TimeUnit.MILLISECONDS.toHours(duration) > MINIMUM_INTERVAL
    }
}