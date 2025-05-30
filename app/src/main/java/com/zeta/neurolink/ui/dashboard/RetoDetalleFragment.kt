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

        val videoUrl = arguments?.getString("videoUrl") ?: "https://www.youtube.com/watch?v=gzivWXNW8Jc" // Esto lo puedes pasar desde el adapter
        val descripcion = arguments?.getString("descripcion") ?: "Este reto consiste en enfrentar tus miedos sociales..."
        val consejos = arguments?.getString("consejos") ?: "1. Respira profundo.\n2. Visualiza el éxito.\n3. No te juzgues."

        // Mostrar datos
        binding.textTitulo.text = titulo
        binding.textPuntaje.text = "$puntaje pts"

        binding.textDescripcion.text = descripcion
        binding.textConsejos.text = consejos

        if (videoUrl.isNotEmpty()) {
            val webSettings = binding.webViewVideo.settings
            webSettings.javaScriptEnabled = true

            // Cargar video de YouTube usando HTML embebido
            val videoId = getYoutubeVideoId(videoUrl)
            val html = """                    
        <html>
        <body style="margin:0;">
        <iframe width="100%" height="100%" 
                src="https://www.youtube.com/embed/$videoId" 
                frameborder="0" allowfullscreen>
        </iframe>
        </body>
        </html>

    """.trimIndent()
            binding.webViewVideo.loadData(html, "text/html", "utf-8")
        }




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
    private fun getYoutubeVideoId(url: String): String {
        val regex = Regex("(?:v=|be/)([a-zA-Z0-9_-]{11})")
        val match = regex.find(url)
        return match?.groups?.get(1)?.value ?: ""
    }

}
