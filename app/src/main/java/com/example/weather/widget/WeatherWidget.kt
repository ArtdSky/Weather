package com.example.weather.widget

import android.annotation.SuppressLint
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
    val tempDouble = weatherWidgetUiState.currentTemperature.toDouble()
    tempDouble.let {
        if (it < -15) {
            resourse = R.drawable.woman_extra_cold
        }
        if (it > -15 && it < 0) {
            resourse = R.drawable.woman_cold
        }
        if (it > 15) {
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
        WeatherWidgetHeader(weatherWidgetUiState.currentTemperature)
        WeatherWidgetBody(
            weatherWidgetUiState.city,
        )
        WeatherWidgetFooter(weatherWidgetUiState.lastTimeUpdate)
    }
}


@Composable
fun WeatherWidgetHeader(temperature: String) {
    var resourse = R.drawable.woman_ok
    Row(
        horizontalAlignment = Alignment.Start,
        modifier = GlanceModifier.fillMaxWidth()
    ) {
        Text(
            text = temperature,
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