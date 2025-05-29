package com.zeta.neurolink.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zeta.neurolink.MainActivity
import com.zeta.neurolink.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // En HomeFragment
        val prefs = requireContext().getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val idusuario = prefs.getInt("idusuario", -1)
        if (idusuario != -1) {
            //Toast.makeText(requireContext(), "ID cargado: $idusuario", Toast.LENGTH_SHORT).show()
            obtenerDatosUsuario(idusuario)
        } else {
            Toast.makeText(requireContext(), "ID de usuario no encontrado", Toast.LENGTH_SHORT).show()
        }

        val root: View = binding.root
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtenerDatosUsuario(idusuario: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("https://zetta.alwaysdata.net/neurolink/clientes/getById")
                val postData = "idusuario=$idusuario"
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.doOutput = true
                conn.connectTimeout = 5000
                conn.readTimeout = 5000
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                conn.outputStream.use { it.write(postData.toByteArray()) }
                val responseCode = conn.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = conn.inputStream.bufferedReader().use { it.readText() }
                    val json = JSONObject(response)
                    val data = json.getJSONObject("data")
                    withContext(Dispatchers.Main) {
                        cargarDatosUsuario(data)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Error del servidor: $responseCode", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun cargarDatosUsuario(jsonUsuario: JSONObject) {
        val nombre = jsonUsuario.getString("nombres")
        val puntaje = jsonUsuario.getInt("puntaje")
        val categoria = jsonUsuario.getString("nivel")

        binding.textNombreUsuario.text = nombre
        binding.textPuntaje.text = "Puntaje: $puntaje"
        binding.textCategoria.text = "Categoría: $categoria"

        val nivel = when {
            puntaje >= 500 -> "Oro"
            puntaje >= 200 -> "Bronce"
            puntaje >= 50 -> "Inicial"
            else -> "Novato"
        }
        binding.textNivel.text = "Nivel: $nivel"

        val progreso = (puntaje % 500) * 100 / 500
        binding.progressCircle.progress = progreso
        binding.textProgressCenter.text = "$puntaje pts"

        val retosFaltantes = (500 - (puntaje % 500)) / 100
        binding.textResumenNivel.text =
            "Estás a $retosFaltantes retos de alcanzar el siguiente nivel. ¡Sigue así!"
    }
}
