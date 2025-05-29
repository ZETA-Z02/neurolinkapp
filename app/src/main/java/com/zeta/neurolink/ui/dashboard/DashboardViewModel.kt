package com.zeta.neurolink.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class DashboardViewModel : ViewModel() {

    private val _retos = MutableLiveData<List<Reto>>()
    val retos: LiveData<List<Reto>> = _retos

    private val todosLosRetos = listOf(
        // Ingresar los retos reales es Titulo, Puntaje que vale, IMG, edadminima, nivelnecesario
        Reto("Reto para niños 1", 100, "https://placehold.co/150", 0, "principiante"),
        Reto("Reto para adolescentes", 200, "https://placehold.co/150", 15, "intermedio"),
        Reto("Reto para jóvenes", 150, "https://placehold.co/150", 20, "avanzado"),
        Reto("Reto para adultos jóvenes", 300, "https://placehold.co/150", 25, "intermedio"),
        Reto("Reto universal", 100, "https://placehold.co/150", 11, "principiante"), // otro ejemplo
        // Agrega más retos aquí con combinaciones diferentes
    )

    fun cargarRetosPorEdadYNivel(edad: Int, nivel: String) {
        val retosFiltrados = todosLosRetos.filter { reto ->
            edad >= reto.edadMinima && nivel == reto.nivel
        }
        _retos.value = retosFiltrados
    }
}
//class DashboardViewModel : ViewModel() {
//    private val _retos = MutableLiveData<List<Reto>>().apply {
//        value = listOf(
//            Reto("Reto 1", 100, "https://placehold.co/150"),
//            Reto("Reto 2", 200, "https://placehold.co/150"),
//            Reto("Reto 3", 150, "https://placehold.co/150"),
//            Reto("Reto 4", 100, "https://placehold.co/150"),
//            Reto("Reto 5", 200, "https://placehold.co/150"),
//            Reto("Reto 6", 100, "https://placehold.co/150"),
//            Reto("Reto 7", 200, "https://placehold.co/150"),
//            Reto("Reto 8", 100, "https://placehold.co/150"),
//            Reto("Reto 9", 200, "https://placehold.co/150"),
//            Reto("Reto 10", 100, "https://placehold.co/150"),
//            Reto("Reto 11", 200, "https://placehold.co/150"),
//            Reto("Reto 12", 100, "https://placehold.co/150"),
//            Reto("Reto 13", 200, "https://placehold.co/150"),
//            Reto("Reto 14", 100, "https://placehold.co/150"),
//            Reto("Reto 15", 200, "https://placehold.co/150"),
//            Reto("Reto 16", 100, "https://placehold.co/150"),
//            Reto("Reto 17", 200, "https://placehold.co/150"),
//        )
//    }
//
//    val retos: LiveData<List<Reto>> = _retos
//}