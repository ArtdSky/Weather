package com.example.weather.presentation.di

import com.example.weather.data.repository.RepositoryImpl
import com.example.weather.data.storage.ApiStorage
import com.example.weather.data.storage.Storage
import com.example.weather.data.storage.local.WeatherDao
import com.example.weather.data.storage.local.WeatherDatabase
import com.example.weather.data.storage.local.WeatherRepository
import com.example.weather.domain.repository.Repository
import com.example.weather.domain.usecase.*
import com.example.weather.presentation.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DependencyInjection = module {

    // Database
    single {
        WeatherDatabase.getDatabase(androidContext())
    }
    single<WeatherDao> {
        get<WeatherDatabase>().weatherDao()
    }

    // Repository
    single {
        WeatherRepository(get())
    }

    single<Storage> { ApiStorage(get()) }

    single<Repository> { RepositoryImpl(get()) }

    // UseCase
    factory { GetWeatherApiDataUseCase(get()) }

    factory { GetOpenWeathermapDataUseCase(get()) }

    factory { GetVisualCrossingData(get()) }

    factory { GetCurrentLocation(get()) }

    factory { GetAllWeatherFromDbUseCase(get()) }

    factory { GetOneWeatherFromDbUseCase(get()) }

    factory { InsertWeatherToDbUseCase(get()) }


    viewModel { MainViewModel(get(), get(), get(), get(), get(), get(), get()) }
}