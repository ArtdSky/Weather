package com.example.weather.widget

import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.Preferences
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition

class WeatherAppWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    @Composable
    override fun Content() {

        val context = LocalContext.current
        val prefs = currentState<Preferences>()
        val city = prefs[WeatherWidgetReceiver.city]
        val currentTemperature = prefs[WeatherWidgetReceiver.currentTemperature]
        val lastTimeUpdate = prefs[WeatherWidgetReceiver.lastTimeUpdate]

        val bitcoinWidgetUiState = WeatherWidgetUiState(
            city = city ?: "",
            currentTemperature = currentTemperature ?: "",
            lastTimeUpdate = lastTimeUpdate ?: ""
        )


        WeatherWidget(bitcoinWidgetUiState)
    }
}
