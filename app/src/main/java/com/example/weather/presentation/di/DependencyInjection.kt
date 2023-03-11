package com.example.weather.presentation.di

import com.example.weather.data.repository.RepositoryImpl
import com.example.weather.data.storage.ApiStorage
import com.example.weather.data.storage.Storage
import com.example.weather.domain.repository.Repository
import com.example.weather.domain.usecase.GetDataUseCase
import com.example.weather.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val  DependencyInjection = module {


    single<Storage> { ApiStorage() }

    single<Repository> { RepositoryImpl(get())  }

    factory { GetDataUseCase(get()) }

    viewModel{ MainViewModel(get()) }
}