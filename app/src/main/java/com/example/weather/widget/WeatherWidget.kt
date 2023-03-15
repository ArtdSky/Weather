package com.example.weather.widget

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.Text
import com.example.weather.R
import com.example.weather.presentation.MainActivity
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@SuppressLint("ResourceType")
@Composable
fun WeatherWidget(weatherWidgetUiState: WeatherWidgetUiState) {
    var resourse = R.drawable.woman_ok
    Log.d("TAG-WIDGET-UI", weatherWidgetUiState.toString())

    val tempDouble = weatherWidgetUiState.currentTemperature


    tempDouble?.let {temperature ->
        if (temperature < -15) {
            resourse = R.drawable.woman_extra_cold
        }
        if (temperature > -15 && temperature < 0) {
            resourse = R.drawable.woman_cold
        }
        if (temperature > 15) {
            resourse = R.drawable.woman_warm
        }
    }
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .clickable(actionStartActivity(activity = MainActivity::class.java))
            .appWidgetBackground()
            .background(resourse)
            .padding(8.dp)
    ) {
        weatherWidgetUiState.currentTemperature?.let {
            WeatherWidgetHeader(it)
        }
        weatherWidgetUiState.city?.let {
            WeatherWidgetBody(it )
        }
        weatherWidgetUiState.lastTimeUpdate?.let{
            WeatherWidgetFooter(it)
        }
    }
}


@Composable
fun WeatherWidgetHeader(temperature: Double) {
    var resourse = R.drawable.woman_ok
    Row(
        horizontalAlignment = Alignment.Start,
        modifier = GlanceModifier.fillMaxWidth()
    ) {
        Text(
            text = temperature.toString(),
        )
    }
}

@Composable
fun WeatherWidgetBody(city: String) {
    Row(
        horizontalAlignment = Alignment.Start,
        verticalAlignment = Alignment.Bottom,
        modifier = GlanceModifier.fillMaxWidth()
    ) {
        Text(
            text = city
        )
    }
}

@Composable
fun WeatherWidgetFooter(lastTimeUpdate: String) {
    val timestamp = lastTimeUpdate.toLong()
    val instant = Instant.ofEpochSecond(timestamp)
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
        .withLocale(Locale.getDefault())
    Row(
        horizontalAlignment = Alignment.Start,
        verticalAlignment = Alignment.Bottom,
        modifier = GlanceModifier.fillMaxWidth()
    ) {
        Text(
            text = instant.atZone(ZoneId.systemDefault()).format(formatter),
        )
    }

}