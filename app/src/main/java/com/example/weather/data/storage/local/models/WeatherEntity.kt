package com.example.weather.data.storage.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    val id : Int? = null,
    val currentTemperature : String,
    val city: String,
    val lastTimeUpdate : String
)
