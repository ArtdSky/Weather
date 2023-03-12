package com.example.weather.widget

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.text.Text

class WeatherWidget: GlanceAppWidget() {

    @Composable
    override fun Content() {
        Column(
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Hello world!")

        }
    }
}

//• Текст с информацией о текущей температуре
//• Текст с информацией о городе (определяется по геопозиции, с помощью готового API)
//• Текст с информацией, когда было последнее обновление данных (UTC Time)
//• Иконка с погодой в зависимости от погоды, можно реализовать только 3-4 сценария
//погоды, все необязательно.
//• Информация в виджете должна полностью синхронизироваться с выбранными
//настройками в самом приложении!