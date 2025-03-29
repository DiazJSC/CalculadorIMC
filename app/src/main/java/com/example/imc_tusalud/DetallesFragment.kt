package com.example.imc_tusalud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.imc_tusalud.viewmodel.DetallesViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.fragment.app.activityViewModels
import com.example.imc_tusalud.viewmodel.AppSettingsViewModel


class DetallesFragment : Fragment() {
    private val detallesViewModel: DetallesViewModel by viewModels()
    private lateinit var appSettingsViewModel: AppSettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detalles, container, false)
        val btnRegresar = root.findViewById<Button>(R.id.btnRegreso)
        val txtMensaje = root.findViewById<TextView>(R.id.txtMensaje)
        val btnActualizar = root.findViewById<Button>(R.id.btnActualizar)

        // Observar cambios en el LiveData y actualizar el TextView
        detallesViewModel.texto.observe(viewLifecycleOwner) { nuevoTexto ->
            txtMensaje.text = nuevoTexto
        }

        btnActualizar.setOnClickListener {
            detallesViewModel.actualizarTexto("¡Actualizado gracias a ViewModel!")
        }

        btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_detallesFragment_to_homeFragment)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appSettingsViewModel: AppSettingsViewModel by activityViewModels()

        appSettingsViewModel.backgroundColor.observe(viewLifecycleOwner) { color ->
            view.setBackgroundColor(color)
        }
    }
}