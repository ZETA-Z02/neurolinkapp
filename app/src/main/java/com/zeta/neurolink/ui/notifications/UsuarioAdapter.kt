package com.zeta.neurolink.ui.notifications
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeta.neurolink.R

class UsuarioAdapter(private val listaUsuarios: List<Usuario>) :
    RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNombre: TextView = itemView.findViewById(R.id.textNombre)
        val textPuntaje: TextView = itemView.findViewById(R.id.textPuntaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listaUsuarios[position]
        holder.textNombre.text = "${usuario.nombres} ${usuario.apellidos}"
        holder.textPuntaje.text = "${usuario.puntaje} pts"
    }

    override fun getItemCount(): Int = listaUsuarios.size
}
