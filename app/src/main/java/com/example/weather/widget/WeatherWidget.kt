package com.example.weather.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.*
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.layout.*
import androidx.glance.text.Text
import com.example.weather.R
import com.example.weather.presentation.MainActivity

@Composable
fun WeatherWidget(weatherWidgetUiState: WeatherWidgetUiState) {

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .clickable(actionStartActivity(activity = MainActivity::class.java))
            .padding(8.dp)
    ) {
        WeatherWidgetHeader()
        WeatherWidgetBody(weatherWidgetUiState.currentTemperature)
        WeatherWidgetFooterBody(
            weatherWidgetUiState.city,
            weatherWidgetUiState.lastTimeUpdate,
        )
    }
}

@Composable
fun WeatherWidgetHeader() {

    Row(
        horizontalAlignment = Alignment.Start,
        modifier = GlanceModifier.fillMaxWidth()
    ) {
        Image(
            provider = ImageProvider(R.drawable.flag_usa),
            contentDescription = "test"
        )
    }
}

@Composable
fun WeatherWidgetBody(temperature : String){
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
fun WeatherWidgetFooterBody(city : String, lastTimeUpdate : String){
    Row(
        horizontalAlignment = Alignment.Start,
        modifier = GlanceModifier.fillMaxWidth()
    ) {
        Text(
            text = city,
        )
        Text(
            text = lastTimeUpdate,
        )
    }

}