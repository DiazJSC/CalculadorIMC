package com.example.imc_tusalud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.activityViewModels
import com.example.imc_tusalud.viewmodel.AppSettingsViewModel



class HomeFragment : Fragment() {

    private lateinit var txtAltura: EditText
    private lateinit var txtPeso: EditText
    private lateinit var txtResultado: EditText
    private lateinit var labelBajo: TextView
    private lateinit var labelNormal: TextView
    private lateinit var labelObeso: TextView
    private lateinit var labelExtremo: TextView
    private lateinit var btnCalcular: Button
    private lateinit var appSettingsViewModel: AppSettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val btnIrDetalle = root.findViewById<Button>(R.id.btnDetalle)
        val btnIrAjuste = root.findViewById<Button>(R.id.btnConfi)

        btnIrDetalle.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detallesFragment)
        }
        btnIrAjuste.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_ajustesFragment3)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appSettingsViewModel: AppSettingsViewModel by activityViewModels()

        appSettingsViewModel.backgroundColor.observe(viewLifecycleOwner) { color ->
            view.setBackgroundColor(color)
        }

        val imgRangoIMC = view.findViewById<ImageView>(R.id.imageView4)

        // Observar cambios en showRecommendations y actualizar la visibilidad de la imagen
        appSettingsViewModel.showRecommendations.observe(viewLifecycleOwner) { show ->
            imgRangoIMC.visibility = if (show) View.VISIBLE else View.GONE
        }

        // Mapeo de elementos del XML al código Kotlin
        txtAltura = view.findViewById(R.id.txtAltura)
        txtPeso = view.findViewById(R.id.txtPeso)
        txtResultado = view.findViewById(R.id.txtResultado)
        labelBajo = view.findViewById(R.id.labelBajo)
        labelNormal = view.findViewById(R.id.labelNormal)
        labelObeso = view.findViewById(R.id.labelObeso)
        labelExtremo = view.findViewById(R.id.labelExtremo)
        btnCalcular = view.findViewById(R.id.btnResul)

        // Elementos invisibles al arrancar
        labelBajo.visibility = View.GONE
        labelNormal.visibility = View.GONE
        labelObeso.visibility = View.GONE
        labelExtremo.visibility = View.GONE

        // Configurar el botón para calcular el IMC
        btnCalcular.setOnClickListener {
            calcularIMC()
        }

        txtAltura.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                txtAltura.hint = ""
            } else {
                txtAltura.hint = "Ingresa tu altura en CM"
            }
        }

        txtPeso.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                txtPeso.hint = ""
            } else {
                txtPeso.hint = "Ingresa tu peso en KG"
            }
        }
    }

    private fun calcularIMC() {
        val altura = txtAltura.text.toString()
        val peso = txtPeso.text.toString()

        if (altura.isEmpty() || peso.isEmpty()) {
            Toast.makeText(requireContext(), "¡Debe ingresar valores en cada espacio!", Toast.LENGTH_SHORT).show()
        } else {
            val alturaDouble = altura.toDouble()
            val pesoDouble = peso.toDouble()
            val metros = alturaDouble / 100
            val imc = pesoDouble / (metros * metros)
            val respuesta = String.format("%.1f", imc)
            txtResultado.setText(respuesta)

            // Ocultar y mostrar etiquetas según el resultado
            labelBajo.visibility = if (imc < 18.5) View.VISIBLE else View.GONE
            labelNormal.visibility = if (imc in 18.5..29.9) View.VISIBLE else View.GONE
            labelObeso.visibility = if (imc in 30.0..34.9) View.VISIBLE else View.GONE
            labelExtremo.visibility = if (imc >= 35) View.VISIBLE else View.GONE
        }
    }
}
