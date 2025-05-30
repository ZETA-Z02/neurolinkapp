package com.zeta.neurolink.ui.dashboard

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zeta.neurolink.MainActivity
import com.zeta.neurolink.R
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class RetoAdapter(
    private val lista: List<Reto>,
    private val onClick: (Reto) -> Unit
) : RecyclerView.Adapter<RetoAdapter.RetoViewHolder>() {

    private var retos: List<Reto> = lista

    class RetoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imagenReto)
        val titulo: TextView = itemView.findViewById(R.id.tituloReto)
        val puntaje: TextView = itemView.findViewById(R.id.puntajeReto)
        val botonLogrado: Button = itemView.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reto, parent, false)
        return RetoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RetoViewHolder, position: Int) {
        val reto = retos[position]
        holder.titulo.text = reto.titulo
        holder.puntaje.text = "${reto.puntaje} pts"

        Glide.with(holder.itemView.context)
            .load(reto.imagenUrl)
            .placeholder(R.drawable.circle_background)
            .error(R.drawable.circle_background)
            .into(holder.imagen)

        holder.itemView.setOnClickListener { onClick(reto) }

        holder.botonLogrado.setOnClickListener {
            val context = holder.itemView.context
            enviarPuntajeAlServidor(reto.puntaje, context)
            eliminarTemporalmente(position)
        }
    }

    override fun getItemCount() = retos.size

    fun updateRetos(nuevaLista: List<Reto>) {
        retos = nuevaLista
        notifyDataSetChanged()
    }

    private fun eliminarTemporalmente(pos: Int) {
        val listaTemporal = retos.toMutableList()
        val eliminado = listaTemporal.removeAt(pos)
        updateRetos(listaTemporal)

        Handler(Looper.getMainLooper()).postDelayed({
            val restaurada = listaTemporal.toMutableList()
            restaurada.add(pos, eliminado)
            updateRetos(restaurada)
        }, 20000) // 5 segundos
    }

    private fun enviarPuntajeAlServidor(puntaje: Int, context: Context) {
        val mainActivity = context as? MainActivity
        val idusuario = mainActivity?.idusuario ?: return

        Thread {
            try {
                val url = URL("https://zetta.alwaysdata.net/neurolink/clientes/sumarPuntaje")
                val postData = "idusuario=$idusuario&puntaje=$puntaje"
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.doOutput = true
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                conn.outputStream.use { it.write(postData.toByteArray()) }

                val responseCode = conn.responseCode
                val response = conn.inputStream.bufferedReader().use { it.readText() }

                Log.d("RESPUESTA", response)
                if (responseCode == 200) {
                    JSONObject(response).let {
                        val success = it.getBoolean("success")
                        if (success) {
                            (context as? Activity)?.runOnUiThread {
                                Toast.makeText(context, "Puntaje enviado ✅", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al enviar puntaje: ${e.message}")
                (context as? Activity)?.runOnUiThread {
                    Toast.makeText(context, "Error al enviar puntaje", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }
}
