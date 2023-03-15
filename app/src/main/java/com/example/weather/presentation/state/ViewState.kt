package com.example.weather.presentation.state

data class ViewState(
    val id : Int? = null,
    val latitude : String? = null,
    val longitude: String? = null,
    val currentTemperature : String? = null,
    val city: String? = null,
    val lastTimeUpdate : String? = null,
)