package com.example.imc_tusalud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetallesViewModel : ViewModel() {

    // LiveData para almacenar el texto
    private val _texto = MutableLiveData<String>().apply {
        value = "Este es el fragmento de Detalles"
    }
    val texto: LiveData<String> = _texto

    // Método para actualizar el texto
    fun actualizarTexto(nuevoTexto: String) {
        _texto.value = nuevoTexto
    }
}
