package com.example.weather.presentation.screens

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.weather.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    vm: MainViewModel
) {
    val state by vm.viewState.collectAsState()

//    vm.loadWeatherApiData()
//    vm.loadOpenWeatherData()
//    vm.loadVisualCrossingData()
//    vm.insertWeatherToDb()
//    vm.getAllWeather()


    Log.d("TAG-SCREEN", state.toString())


    Text(text="hi world")
}