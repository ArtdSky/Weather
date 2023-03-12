package com.example.weather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.storage.network.ApiConfig
import com.example.weather.domain.usecase.GetDataUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {


    fun loadData() {
        viewModelScope.launch {
            val res = getDataUseCase(
                key = "a417b27b7c0746ceab5124157232501",
                q = "54.99,73.37"
            )
            Log.d("TAG-VM", res.toString())
        }
    }

    fun setBaseUrl(){
        ApiConfig.setBaseUrl("qwe")
    }
}