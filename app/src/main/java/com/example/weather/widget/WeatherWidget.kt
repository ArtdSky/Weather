package com.example.weather.widget

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.text.Text

class WeatherWidget: GlanceAppWidget() {

    @Composable
    override fun Content() {
        Text(text = "Hello world!")
    }
}

