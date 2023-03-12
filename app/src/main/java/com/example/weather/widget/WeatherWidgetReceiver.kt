package com.example.weather.widget

import androidx.glance.appwidget.GlanceAppWidgetReceiver

class WeatherWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget = WeatherWidget()
}