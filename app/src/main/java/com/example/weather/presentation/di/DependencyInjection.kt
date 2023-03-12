package com.example.weather.presentation.di

import com.example.weather.data.repository.RepositoryImpl
import com.example.weather.data.storage.ApiStorage
import com.example.weather.data.storage.Storage
import com.example.weather.domain.repository.Repository
import com.example.weather.domain.usecase.GetCurrentLocation
import com.example.weather.domain.usecase.GetOpenWeathermapDataUseCase
import com.example.weather.domain.usecase.GetVisualCrossingData
import com.example.weather.domain.usecase.GetWeatherApiDataUseCase
import com.example.weather.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DependencyInjection = module {


    single<Storage> { ApiStorage() }

    single<Repository> { RepositoryImpl(get()) }

    factory { GetWeatherApiDataUseCase(get()) }

    factory { GetOpenWeathermapDataUseCase(get()) }

    factory { GetVisualCrossingData(get()) }

    factory { GetCurrentLocation(get()) }

    viewModel { MainViewModel(get(), get(), get(), get()) }
}