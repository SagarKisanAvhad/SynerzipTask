package com.sagar.synerzip.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sagar.synerzip.data.db.AppDataBase
import com.sagar.synerzip.data.db.entities.Weather
import com.sagar.synerzip.data.network.MyApi
import com.sagar.synerzip.data.network.SafeApiRequest
import com.sagar.synerzip.data.network.responses.mapToWeather
import com.sagar.synerzip.data.preferences.PreferenceProvider
import com.sagar.synerzip.util.AppDateFormat
import com.sagar.synerzip.util.Coroutines
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

        }

    }

    private fun saveWeatherOfCity(weather: Weather) {
        Coroutines.io {
            db.getWeatherDao().insertWeather(weather)
        }
    }

    fun getWeatherForCity(city: String): Flow<Weather> {
        return flow {
            emit(fetchWeatherForCity(city))
        }
    }

    private suspend fun fetchWeatherForCity(city: String): Weather {
        interestedCity = city
        val lastSavedAt = prefs.getSavedAt(interestedCity)

        return if (lastSavedAt == null || isFetchNeeded(lastSavedAt)) {
            val weatherResponse = apiRequest { api.getWeatherByCity(interestedCity) }

            Log.d("Response", weatherResponse.toString())

            prefs.saveSavedAt(interestedCity, AppDateFormat.df_Date.format(Date()))
            saveWeatherOfCity(weatherResponse.mapToWeather())
            weatherResponse.mapToWeather()
        } else {
            db.getWeatherDao().getWeatherForCity(city)
        }
    }

    private fun isFetchNeeded(lastSavedAt: String): Boolean {
        val duration = Date().time - AppDateFormat.df_Date.parse(lastSavedAt).time
        return TimeUnit.MILLISECONDS.toHours(duration) > MINIMUM_INTERVAL
    }
}