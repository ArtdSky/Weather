package com.example.weather.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.example.weather.data.repository.RepositoryImpl
import com.example.weather.data.storage.ApiStorage
import com.example.weather.data.storage.local.WeatherDatabase
import com.example.weather.data.storage.local.WeatherRepository
import com.example.weather.domain.usecase.GetAllWeatherFromDbUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        getData(context)

    }


    private fun getData(context: Context) {
        coroutineScope.launch {
            val glanceId = GlanceAppWidgetManager(context).getGlanceIds(WeatherAppWidget::class.java)
                .firstOrNull()
            val result = withContext(Dispatchers.IO) {
                GetAllWeatherFromDbUseCase(
                    RepositoryImpl(
                        ApiStorage(
                            WeatherRepository(
                                WeatherDatabase.getDatabase(context).weatherDao()
                            )
                        )
                    )
                )()
            }
            glanceId?.let {
                updateAppWidgetState(context, PreferencesGlanceStateDefinition, it) { pref ->
                    pref.toMutablePreferences().apply {
                        this[city] = result[0].city
                        this[currentTemperature] = result[0].currentTemperature
                        this[lastTimeUpdate] = result[0].lastTimeUpdate
                    }
                }
                Log.d("TAG-WIDGET", "getAllWeather ${result.toString()}")

                glanceAppWidget.update(context, it)
            }
        }
    }
        companion object {
            val city = stringPreferencesKey("city")
            val currentTemperature = stringPreferencesKey("currentTemperature")
            val lastTimeUpdate = stringPreferencesKey("lastTimeUpdate")
        }

}