package com.example.weather.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.presentation.viewmodel.MainViewModel

@Composable
fun NavState(
    vm : MainViewModel
) {

    val navController = rememberNavController()

    NavGraph(navController = navController, vm = vm)
}