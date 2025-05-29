package com.zeta.neurolink.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeta.neurolink.R

class RetoAdapter(
    private val lista: List<Reto>,
    private val onClick: (Reto) -> Unit
) : RecyclerView.Adapter<RetoAdapter.RetoViewHolder>() {

    class RetoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imagenReto)
        val titulo: TextView = itemView.findViewById(R.id.tituloReto)
        val puntaje: TextView = itemView.findViewById(R.id.puntajeReto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reto, parent, false)
        return RetoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RetoViewHolder, position: Int) {
        val reto = retos[position]
        holder.titulo.text = reto.titulo
        holder.puntaje.text = "${reto.puntaje} pts"
        holder.itemView.setOnClickListener { onClick(reto) }
    }
    private var retos: List<Reto> = lista

    fun updateRetos(nuevaLista: List<Reto>) {
        retos = nuevaLista
        notifyDataSetChanged()
    }

    override fun getItemCount() = retos.size
}