package com.zeta.neurolink.ui.dashboard

//data class Reto(
//    val titulo:String,
//    val puntaje:Int,
//    val imagenUrl:String
//)
data class Reto(
    val titulo: String,
    val puntaje: Int,
    val imagenUrl: String,
    val edadMinima: Int,
    val nivel: String,
    val descripcion: String = "",
    val consejos: String = "",
    val videoUrl: String = ""
)
