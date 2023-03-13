package com.example.weather.presentation.navigation

interface AppDestination {
    val route: String
}

object MainScreen : AppDestination {
    override val route = "main"

}


val AppTabRowScreens = listOf(MainScreen)