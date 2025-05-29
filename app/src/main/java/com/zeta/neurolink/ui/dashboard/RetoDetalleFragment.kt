package com.zeta.neurolink.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zeta.neurolink.databinding.FragmentRetoDetalleBinding

class RetoDetalleFragment : Fragment() {

    private var _binding: FragmentRetoDetalleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRetoDetalleBinding.inflate(inflater, container, false)
        val root = binding.root

        // Obtener argumentos
        val titulo = arguments?.getString("titulo") ?: "Sin título"
        val puntaje = arguments?.getInt("puntaje") ?: 0

        // Mostrar datos
        binding.textTitulo.text = titulo
        binding.textPuntaje.text = "$puntaje pts"

        // Habilita la flecha de retroceso
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Le avisas que quieres manejar el botón de regreso
        setHasOptionsMenu(true)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        _binding = null
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp() // Navega hacia atrás
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
