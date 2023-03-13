package com.example.weather.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weather.presentation.screens.MainScreen
import com.example.weather.presentation.viewmodel.MainViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    vm: MainViewModel
) {

    NavHost(
        navController = navController,
        startDestination = MainScreen.route,

        ) {
        composable(route = MainScreen.route) {
            MainScreen(
                navController = navController,
                vm = vm
            )
        }


    }


}