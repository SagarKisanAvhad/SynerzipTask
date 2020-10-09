package com.sagar.synerzip.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    var name: String? = null,
    var temp: Double? = null,
    var tempMin: Double? = null,
    var grndLevel: Int? = null,
    var humidity: Int? = null,
    var pressure: Int? = null,
    var seaLevel: Int? = null,
    var feelsLike: Double? = null,
    var tempMax: Double? = null
)

