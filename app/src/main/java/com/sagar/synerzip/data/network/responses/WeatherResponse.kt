package com.sagar.synerzip.data.network.responses

import com.google.gson.annotations.SerializedName
import com.sagar.synerzip.data.db.entities.Weather

data class WeatherResponse(
    @SerializedName("visibility") @JvmField val visibility: Int,
    @SerializedName("timezone") @JvmField val timezone: Int,
    @SerializedName("main") @JvmField val main: Main,
    @SerializedName("clouds") @JvmField val clouds: Clouds,
    @SerializedName("sys") @JvmField val sys: Sys,
    @SerializedName("dt") @JvmField val dt: Int,
    @SerializedName("coord") @JvmField val coord: Coord,
    @SerializedName("weather") @JvmField val weather: List<WeatherItem>,
    @SerializedName("name") @JvmField val name: String,
    @SerializedName("cod") @JvmField val cod: Int,
    @SerializedName("id") @JvmField val id: Int,
    @SerializedName("base") @JvmField val base: String,
    @SerializedName("wind") @JvmField val wind: Wind
)

data class Wind(
    @SerializedName("deg") @JvmField val deg: Int,
    @SerializedName("speed") @JvmField val speed: Double
)

data class Main(
    @SerializedName("temp") @JvmField val temp: Double,
    @SerializedName("temp_min") @JvmField val tempMin: Double,
    @SerializedName("grnd_level") @JvmField val grndLevel: Int,
    @SerializedName("humidity") @JvmField val humidity: Int,
    @SerializedName("pressure") @JvmField val pressure: Int,
    @SerializedName("sea_level") @JvmField val seaLevel: Int,
    @SerializedName("feels_like") @JvmField val feelsLike: Double,
    @SerializedName("temp_max") @JvmField val tempMax: Double
)

data class WeatherItem(
    @SerializedName("icon") @JvmField val icon: String,
    @SerializedName("description") @JvmField val description: String,
    @SerializedName("main") @JvmField val main: String,
    @SerializedName("id") @JvmField val id: Int
)

data class Sys(
    @SerializedName("country") @JvmField val country: String,
    @SerializedName("sunrise") @JvmField val sunrise: Int,
    @SerializedName("sunset") @JvmField val sunset: Int
)

data class Clouds(
    @SerializedName("all") @JvmField val all: Int
)

data class Coord(
    @SerializedName("lon") @JvmField val lon: Double,
    @SerializedName("lat") @JvmField val lat: Double
)


fun WeatherResponse.mapToWeather(): Weather {
    return Weather(
        id = id,
        name = name,
        temp = main.temp,
        tempMin = main.tempMin,
        grndLevel = main.grndLevel,
        humidity = main.humidity,
        pressure = main.pressure,
        seaLevel = main.seaLevel,
        feelsLike = main.feelsLike,
        tempMax = main.tempMax
    )
}