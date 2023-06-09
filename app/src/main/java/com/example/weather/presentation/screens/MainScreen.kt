package com.example.weather.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weather.R
import com.example.weather.presentation.screens.components.DropdownItem
import com.example.weather.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun MainScreen(
    navController: NavHostController,
    vm: MainViewModel
) {
    val state by vm.viewState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val checkedStateRu = remember { mutableStateOf(false) }
    val checkedStateUsa = remember { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableStateOf(0) }
    var selectedItemHandler by remember { mutableStateOf<(() -> Unit)?>(null) }

    val shouldShowImage = remember { mutableStateOf(false) }
    state.currentTemperature?.let {
        shouldShowImage.value = true
    }
    Log.d("TAG-SCREEN", state.toString())

    val items = listOf(
        DropdownItem("WeatherApi") {
            coroutineScope.launch {
                vm.loadWeatherApiData().await()
                vm.insertWeatherToDb().await()
                vm.getAllWeather().await()
            }
        },
        DropdownItem("OpenWeather") {
            coroutineScope.launch {
                vm.loadOpenWeatherData().await()
                vm.insertWeatherToDb().await()
                vm.getAllWeather().await()
            }
        },
        DropdownItem("VisualCrossing") {
            coroutineScope.launch {
                vm.loadVisualCrossingData().await()
                vm.insertWeatherToDb().await()
                vm.getAllWeather().await()
            }
        }
    )
    var selectedItem by remember { mutableStateOf(items.first().source) }
    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val config = LocalConfiguration.current
    val resources = LocalContext.current.resources


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Box {
                Text(
                    text = stringResource(R.string.data_source) + ": $selectedItem",
                )
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(Icons.Default.ArrowDropDown, "Dropdown")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    items.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            onClick = {
                                selectedItem = item.source
                                item.onClick()
                                expanded = false
                                selectedItemIndex = index
                                selectedItemHandler = item.onClick
                            }
                        ) {
                            Text(text = item.source)
                        }
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.temperature)
                )
                Spacer(modifier = Modifier.width(10.dp))
                state.currentTemperature?.let {
                    Text(
                        text = it
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.city)
                )
                Spacer(modifier = Modifier.width(10.dp))
                state.city?.let {
                    Text(
                        text = it
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.last_update)
                )
                Spacer(modifier = Modifier.width(10.dp))
                state.lastTimeUpdate?.let {
                    val timestamp = it.toLong()
                    val instant = Instant.ofEpochSecond(timestamp)
                    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
                        .withLocale(Locale.getDefault())
                    Text(
                        text = instant.atZone(ZoneId.systemDefault()).format(formatter)
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row {
                    Checkbox(
                        checked = checkedStateUsa.value,
                        onCheckedChange = {
                            config.setLocale(Locale("en"))
                            resources.updateConfiguration(
                                context.resources.configuration,
                                resources.displayMetrics
                            )
                            checkedStateUsa.value = it
                            checkedStateRu.value = false
                        },
                    )
                    Icon(
                        painter = painterResource(R.drawable.flag_usa),
                        contentDescription = "USA icon",
                        tint = Color.Unspecified
                    )
                }

                Button(
                    onClick = {
                        selectedItemHandler?.let { it() }
                    }
                ) {
                    Text(text = stringResource(R.string.button_update))
                }


                Row {
                    Checkbox(
                        checked = checkedStateRu.value,
                        onCheckedChange = {
                            config.setLocale(Locale("ru"))
                            resources.updateConfiguration(
                                context.resources.configuration,
                                resources.displayMetrics
                            )
                            checkedStateRu.value = it
                            checkedStateUsa.value = false
                        },
                    )
                    Icon(
                        painter = painterResource(R.drawable.flag_russia),
                        contentDescription = "Ru icon",
                        tint = Color.Unspecified
                    )
                }

            }
            if (shouldShowImage.value) {
                TemperatureImage(state.currentTemperature)
            }
        }
    }


}

@Composable
fun TemperatureImage(temperature: String?) {
    val tempDouble = temperature?.let { it.toDouble() }
    var resourse = R.drawable.woman_ok
    tempDouble?.let {
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

    Box {
        Image(
            painter = painterResource(id = resourse),
            contentDescription = "Temperature image"
        )
    }

}