package com.example.weather.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.weather.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.*

@Composable
fun MainScreen(
    navController: NavHostController,
    vm: MainViewModel
) {
    val state by vm.viewState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    Log.d("TAG-SCREEN", state.toString())

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            onClick = {
                coroutineScope.launch {
                    vm.loadWeatherApiData().await()
                    vm.insertWeatherToDb().await()
                    vm.getAllWeather().await()
                }
            }
        ) {
            Text(text = "loadWeatherApiData")
        }
        Button(
            onClick = {
                coroutineScope.launch {
                    vm.loadOpenWeatherData().await()
                    vm.insertWeatherToDb().await()
                    vm.getAllWeather().await()
                }
            }
        ) {
            Text(text = "loadOpenWeatherData")
        }
        Button(
            onClick = {
                coroutineScope.launch {
                    vm.loadVisualCrossingData().await()
                    vm.insertWeatherToDb().await()
                    vm.getAllWeather().await()
                }
            }
        ) {
            Text(text = "loadVisualCrossingData")
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    vm.getAllWeather().await()
                }
            }
        ) {
            Text(text = "LOG DB")
        }
    }

}