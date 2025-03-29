package com.example.imc_tusalud

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.imc_tusalud.viewmodel.AppSettingsViewModel

class AjustesFragment : Fragment() {
    // ViewModel compartido entre fragmentos
    private val appSettingsViewModel: AppSettingsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_ajustes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Botón para regresar
        val btnRegresar = view.findViewById<Button>(R.id.btnRegreso2)
        btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_ajustesFragment3_to_homeFragment)
        }

        // Elementos UI
        val colorPicker = view.findViewById<Button>(R.id.btnColorPicker)
        val switchRecommendations = view.findViewById<Switch>(R.id.switchRecomendaciones)

        // Cambiar color de fondo
        colorPicker.setOnClickListener {
            val currentColor = appSettingsViewModel.backgroundColor.value ?: Color.WHITE
            val newColor = if (currentColor == Color.WHITE) Color.LTGRAY else Color.WHITE
            appSettingsViewModel.setBackgroundColor(newColor)
        }

        // Observar cambios en showRecommendations y actualizar el estado del switch
        appSettingsViewModel.showRecommendations.observe(viewLifecycleOwner) { isChecked ->
            switchRecommendations.isChecked = isChecked
        }

        switchRecommendations.setOnCheckedChangeListener { _, isChecked ->
            appSettingsViewModel.setShowRecommendations(isChecked)
        }

        // Observar cambios y aplicar el fondo seleccionado
        appSettingsViewModel.backgroundColor.observe(viewLifecycleOwner) { color ->
            view.setBackgroundColor(color)
        }
    }
}
