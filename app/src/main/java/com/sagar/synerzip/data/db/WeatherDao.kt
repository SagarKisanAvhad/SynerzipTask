package com.sagar.synerzip.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sagar.synerzip.data.db.entities.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: Weather)

    @Query("SELECT * FROM weather WHERE name = :city")
    fun getWeatherForCity(city: String): Weather
}