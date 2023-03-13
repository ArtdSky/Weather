package com.example.weather.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidgetReceiver
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

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        getData(context)
    }

    override val glanceAppWidget = WeatherWidget()

    private fun getData(context: Context) {
        coroutineScope.launch {
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
            Log.d("TAG-WIDGET", "getAllWeather ${result.toString()}")
        }
    }

}