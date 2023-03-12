package com.example.weather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.usecase.GetOpenWeathermapDataUseCase
import com.example.weather.domain.usecase.GetVisualCrossingData
import com.example.weather.domain.usecase.GetWeatherApiDataUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getWeatherApiDataUseCase: GetWeatherApiDataUseCase,
    private val getOpenWeathermapDataUseCase: GetOpenWeathermapDataUseCase,
    private val getVisualCrossingData: GetVisualCrossingData
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





}