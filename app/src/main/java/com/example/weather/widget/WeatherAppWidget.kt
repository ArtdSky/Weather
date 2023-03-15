package com.example.weather.widget

import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.Preferences
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition

class WeatherAppWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition


    @Composable
    override fun Content() {

        val prefs = currentState<Preferences>()
        val city = prefs[WeatherWidgetReceiver.city]
        val currentTemperature = prefs[WeatherWidgetReceiver.currentTemperature]
        val lastTimeUpdate = prefs[WeatherWidgetReceiver.lastTimeUpdate]

        val weatherWidgetUiState = WeatherWidgetUiState(
            city = city ?: "",
            currentTemperature = currentTemperature ?: 0.0,
            lastTimeUpdate = lastTimeUpdate ?: "0"
        )


        WeatherWidget(weatherWidgetUiState)
    }
}
