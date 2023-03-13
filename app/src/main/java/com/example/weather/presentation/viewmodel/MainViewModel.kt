package com.example.weather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.usecase.*
import com.example.weather.presentation.models.WeatherData
import com.example.weather.presentation.state.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getWeatherApiDataUseCase: GetWeatherApiDataUseCase,
    private val getOpenWeathermapDataUseCase: GetOpenWeathermapDataUseCase,
    private val getVisualCrossingData: GetVisualCrossingData,
    private val getCurrentLocation: GetCurrentLocation,
    private val getOneWeatherFromDbUseCase: GetOneWeatherFromDbUseCase,
    private val getAllWeatherFromDbUseCase: GetAllWeatherFromDbUseCase,
    private val insertWeatherToDbUseCase: InsertWeatherToDbUseCase
) : ViewModel() {

    init {
        loadCurrentLocation()
    }

    private val _viewState = MutableStateFlow(
        ViewState()
    )
    val viewState = _viewState.asStateFlow()

    private fun loadCurrentLocation() {
        viewModelScope.launch {
            val res = getCurrentLocation()
            Log.d("TAG-VM", "{Current Location $res}")

            _viewState.update { currentState ->
                currentState.copy(
                    latitude = res.latitude,
                    longitude = res.longitude,
                )
            }
        }
    }

    fun loadWeatherApiData() {
        val location = "${viewState.value.latitude},${viewState.value.longitude}"
//        val location = "${latitude},${longitude}"
        viewModelScope.launch {
            val res = getWeatherApiDataUseCase(
                location = location
            )
            Log.d("TAG-VM", "{WeatherApi $res}")

            _viewState.update { currentState ->
                currentState.copy(
                    currentTemperature = res.currentTemperature,
                    city = res.city,
                    lastTimeUpdate = res.lastTimeUpdate
                )
            }
        }
    }

    fun loadOpenWeatherData(){
        viewModelScope.launch {
            val latitude = viewState.value.latitude ?: return@launch
            val longitude = viewState.value.longitude ?: return@launch

            val res = getOpenWeathermapDataUseCase(
                lat = latitude,
                lon = longitude,
            )
            Log.d("TAG-VM", "{OpenWeather $res.toString()}")

            _viewState.update { currentState ->
                currentState.copy(
                    currentTemperature = res.currentTemperature,
                    city = res.city,
                    lastTimeUpdate = res.lastTimeUpdate
                )
            }
        }
    }

    fun loadVisualCrossingData(){
        viewModelScope.launch {
            val latitude = viewState.value.latitude ?: return@launch
            val longitude = viewState.value.longitude ?: return@launch

            val res = getVisualCrossingData(
                lat = latitude,
                lon = longitude,
            )
            Log.d("TAG-VM", "{VisualCrossing $res.toString()}")

            _viewState.update { currentState ->
                currentState.copy(
                    currentTemperature = res.currentTemperature,
                    city = res.city,
                    lastTimeUpdate = res.lastTimeUpdate
                )
            }
        }
    }

    fun insertWeatherToDb(){
        viewModelScope.launch {
            insertWeatherToDbUseCase(
                WeatherData(
                    id = viewState.value.id,
                    currentTemperature = viewState.value.currentTemperature,
                    city = viewState.value.city,
                    lastTimeUpdate = viewState.value.lastTimeUpdate
                )
            )
        }
    }

    fun getAllWeather(){
        viewModelScope.launch {
            val result = getAllWeatherFromDbUseCase()
            Log.d("TAG-VM", "getAllWeather ${result.toString()}")
        }
    }




}