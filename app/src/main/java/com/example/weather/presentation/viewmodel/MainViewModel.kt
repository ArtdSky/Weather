package com.example.weather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.usecase.*
import com.example.weather.presentation.models.WeatherData
import com.example.weather.presentation.state.ViewState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
    private val insertWeatherToDbUseCase: InsertWeatherToDbUseCase,
    private val updateWeatherInDbUseCase: UpdateWeatherInDbUseCase,
    private val clearDbUseCase: ClearDbUseCase,
) : ViewModel() {

    init {
        clearWeatherDb()
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

    suspend fun loadWeatherApiData() : Deferred<Unit> {
        val location = "${viewState.value.latitude},${viewState.value.longitude}"
        return viewModelScope.async(Dispatchers.IO) {
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

    suspend fun loadOpenWeatherData(): Deferred<Unit> {
        return viewModelScope.async(Dispatchers.IO) {
            val latitude = viewState.value.latitude ?: return@async
            val longitude = viewState.value.longitude ?: return@async

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

    suspend fun loadVisualCrossingData() : Deferred<Unit>{
        return viewModelScope.async(Dispatchers.IO) {
            val latitude = viewState.value.latitude ?: return@async
            val longitude = viewState.value.longitude ?: return@async

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

    suspend fun insertWeatherToDb() : Deferred<Unit>{
        return viewModelScope.async(Dispatchers.IO) {
            insertWeatherToDbUseCase(
                WeatherData(
                    id = 1,
                    currentTemperature = viewState.value.currentTemperature,
                    city = viewState.value.city,
                    lastTimeUpdate = viewState.value.lastTimeUpdate
                )
            )
        Log.d("TAG-VM", "insertWeatherToDb")
        }

    }

    fun clearWeatherDb() {
        viewModelScope.launch {
            clearDbUseCase()
            Log.d("TAG-VM", "clearWeatherDb")
        }
    }

    fun updateWeatherInDb() {
        viewModelScope.launch {
            updateWeatherInDbUseCase(
                data = WeatherData(
                    id = 1,
                    currentTemperature = viewState.value.currentTemperature,
                    city = viewState.value.city,
                    lastTimeUpdate = viewState.value.lastTimeUpdate
                )
            )
            Log.d("TAG-VM", "updateWeatherInDb")
        }
    }

    suspend fun getAllWeather(): Deferred<Unit>  {
        return viewModelScope.async(Dispatchers.IO) {
            val result = getAllWeatherFromDbUseCase()
            Log.d("TAG-VM", "getAllWeather ${result.toString()}")
        }
    }


}