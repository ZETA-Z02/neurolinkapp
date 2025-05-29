package com.zeta.neurolink.ui.notifications
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeta.neurolink.databinding.FragmentNotificationsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val usuarios = mutableListOf<Usuario>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        binding.recyclerUsuarios.layoutManager = LinearLayoutManager(requireContext())
        obtenerUsuarios()
        return binding.root
    }

    private fun obtenerUsuarios() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("https://zetta.alwaysdata.net/neurolink/clientes/get")
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                conn.connectTimeout = 5000
                conn.readTimeout = 5000
                val responseCode = conn.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = conn.inputStream.bufferedReader().readText()
                    val json = JSONObject(response)
                    val dataArray = json.getJSONArray("data")
                    val lista = parseUsuarios(dataArray)

                    // Ordenamos por puntaje descendente
                    lista.sortByDescending { it.puntaje }

                    withContext(Dispatchers.Main) {
                        val adapter = UsuarioAdapter(lista)
                        binding.recyclerUsuarios.adapter = adapter
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Error: $responseCode", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun parseUsuarios(jsonArray: JSONArray): MutableList<Usuario> {
        val lista = mutableListOf<Usuario>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val usuario = Usuario(
                idusuario = obj.getInt("idusuario"),
                nombres = obj.getString("nombres"),
                apellidos = obj.getString("apellidos"),
                puntaje = obj.getInt("puntaje")
            )
            lista.add(usuario)
        }
        return lista
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
