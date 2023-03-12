package com.example.weather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.usecase.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getWeatherApiDataUseCase: GetWeatherApiDataUseCase,
    private val getOpenWeathermapDataUseCase: GetOpenWeathermapDataUseCase,
    private val getVisualCrossingData: GetVisualCrossingData,
    private val getCurrentLocation: GetCurrentLocation,
    private val getOneWeatherFromDbUseCase: GetOneWeatherFromDbUseCase,
    private val getAllWeatherFromDbUsecase: GetAllWeatherFromDbUsecase,
    private val insertWeatherToDbUseCase: InsertWeatherToDbUseCase
) : ViewModel() {


    fun loadWeatherApiData() {
        viewModelScope.launch {
            val res = getWeatherApiDataUseCase(
                latlon = "54.99,73.37"
            )
            Log.d("TAG-VM", "{WeatherApi $res.toString()}")
        }
    }

    fun loadOpenWeatherData(){
        viewModelScope.launch {
            val res = getOpenWeathermapDataUseCase(
                lat = "54.99",
                lon = "73.37",
            )
            Log.d("TAG-VM", "{OpenWeather $res.toString()}")

        }
    }

    fun loadVisualCrossingData(){
        viewModelScope.launch {
            val res = getVisualCrossingData(
                lat = "54.99",
                lon = "73.37",
            )
            Log.d("TAG-VM", "{VisualCrossing $res.toString()}")
        }
    }

    fun loadCurrentLocation() {
        viewModelScope.launch {
            val res = getCurrentLocation()
            Log.d("TAG-VM", "{Current Location $res.toString()}")
        }
    }





}