package com.example.weather.presentation.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.weather.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
     myViewModel: MainViewModel = koinViewModel()
) {
    myViewModel.loadWeatherApiData()
    myViewModel.loadOpenWeatherData()
    myViewModel.loadVisualCrossingData()
    myViewModel.loadCurrentLocation()

    Text(text="hi world")
}