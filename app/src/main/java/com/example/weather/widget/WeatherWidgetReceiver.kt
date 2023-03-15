package com.example.weather.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.example.weather.data.storage.local.WeatherDatabase
import com.example.weather.data.storage.local.models.WeatherEntity
import kotlinx.coroutines.*

class WeatherWidgetReceiver(

) : GlanceAppWidgetReceiver() {

    private val coroutineScope = MainScope()

    override val glanceAppWidget = WeatherAppWidget()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        coroutineScope.launch {
            getData(context)
        }

    }


    private suspend fun getData(context: Context) {
        val glanceIds = GlanceAppWidgetManager(context)
            .getGlanceIds(WeatherAppWidget::class.java)

        val weatherList = withContext(Dispatchers.IO) {
            WeatherDatabase.getDatabase(context).weatherDao().getAllWeather()
        }

        glanceIds.forEach { glanceId ->
            updateWidgetUi(context, glanceId, weatherList)
        }

    }

    private fun updateWidgetUi(context: Context, glanceId: GlanceId, weatherList: List<WeatherEntity>) {
        coroutineScope.launch {
            updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) { pref ->
                pref.toMutablePreferences().apply {
                    this[city] = weatherList[0].city
                    this[currentTemperature] = weatherList[0].currentTemperature.toDouble()
                    this[lastTimeUpdate] = weatherList[0].lastTimeUpdate
                }
            }
            Log.d("TAG-WIDGET", "updateWidgetUi ${weatherList}")

            glanceAppWidget.update(context, glanceId)
        }
    }



    override fun onEnabled(context: Context) {
        super.onEnabled(context)
            coroutineScope.launch {
                getData(context)
            }
        }

    companion object {
        val city = stringPreferencesKey("city")
        val currentTemperature = doublePreferencesKey("currentTemperature")
        val lastTimeUpdate = stringPreferencesKey("lastTimeUpdate")
    }

}