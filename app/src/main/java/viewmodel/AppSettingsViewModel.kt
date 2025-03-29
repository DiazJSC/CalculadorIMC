package com.example.imc_tusalud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppSettingsViewModel : ViewModel() {

    // LiveData para el color de fondo
    private val _backgroundColor = MutableLiveData<Int>()
    val backgroundColor: LiveData<Int> get() = _backgroundColor

    // LiveData para el estado de las recomendaciones
    private val _showRecommendations = MutableLiveData<Boolean>(true)
    val showRecommendations: LiveData<Boolean> = _showRecommendations

    // Métodos para actualizar valores
    fun setBackgroundColor(color: Int) {
        _backgroundColor.value = color
    }

    fun setShowRecommendations(value: Boolean) {
        _showRecommendations.value = value
    }
}
