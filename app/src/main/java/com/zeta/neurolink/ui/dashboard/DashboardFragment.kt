package com.zeta.neurolink.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeta.neurolink.databinding.FragmentDashboardBinding
import androidx.navigation.fragment.findNavController
import com.zeta.neurolink.MainActivity
import com.zeta.neurolink.R
import com.zeta.neurolink.ui.Preguntas
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.Calendar


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RetoAdapter
    private lateinit var viewModel: DashboardViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        adapter = RetoAdapter(emptyList()) { reto ->
            val bundle = Bundle().apply {
                putString("titulo", reto.titulo)
                putInt("puntaje", reto.puntaje)
                putString("imagenUrl", reto.imagenUrl)
                putString("videoUrl", reto.videoUrl)
                putString("descripcion", reto.descripcion)
                putString("consejos", reto.consejos)
            }
            findNavController().navigate(R.id.retoDetalleFragment, bundle)
        }

        binding.recyclerViewRetos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewRetos.adapter = adapter

        // val idusuario = 6 // ID de prueba
        val mainActivity = activity as? MainActivity
        val idusuario = mainActivity?.idusuario ?: -1
        //Toast.makeText(requireContext(), "id: $idusuario", Toast.LENGTH_LONG).show()
        //Llamada a la API
        peticion(idusuario) { edad, nivel ->
            val nivelTexto = when (nivel) {
                1 -> "Inteligencia Emocional"
                2 -> "Ansiedad Social y Timidez"
                3 -> "Miedo al Ridiculo y Baja Autoestima"
                4 -> "Trauma y Rechazo"
                5 -> "Adaptación y Resiliencia"
                else -> "Principiante"
            }
            // 💡 Usa estos datos para cargar los retos
            viewModel.cargarRetosPorEdadYNivel(edad, nivelTexto)
        }

        viewModel.retos.observe(viewLifecycleOwner) { lista ->
            adapter.updateRetos(lista)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun peticion(idusuario: Int, callback: (edad: Int, nivel: Int) -> Unit) {
        Thread {
            try {
                val url = URL("https://zetta.alwaysdata.net/neurolink/clientes/getById")
                val postData = "idusuario=$idusuario"
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.doOutput = true
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                conn.outputStream.use { it.write(postData.toByteArray()) }

                val responseCode = conn.responseCode
                val response = conn.inputStream.bufferedReader().use { it.readText() }

                requireActivity().runOnUiThread {
                    if (responseCode == 200) {
                        val respuestajson = JSONObject(response)
                        val success = respuestajson.getBoolean("success")
                        val error = respuestajson.getBoolean("error")
                        if (success && !error) {
                            val data = respuestajson.getJSONObject("data")
                            val fechaNac = data.getString("fechaNac")
                            // val fecha = "2005-07-05" // formato: yyyy-MM-dd
                            val partes = fechaNac.split("-") // separa en ["2005", "07", "05"]

                            val anio = partes[0].toInt()
                            val mes = partes[1].toInt()
                            val dia = partes[2].toInt()
                            val nivel = data.getInt("nivel")

                            // Convertir fecha de nacimiento a edad
                            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                            val edad = currentYear - anio

                            callback(edad, nivel)
                        } else {
                            Toast.makeText(requireContext(), "Acceso Denegado", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "ERROR EN EL SERVIDOR", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("API ERROR", e.message ?: "Error desconocido")
                }
            }
        }.start()
    }

}