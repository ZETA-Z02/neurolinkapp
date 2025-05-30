package com.zeta.neurolink.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class DashboardViewModel : ViewModel() {

    private val _retos = MutableLiveData<List<Reto>>()
    val retos: LiveData<List<Reto>> = _retos

    private val todosLosRetos = listOf(
        Reto("Reto para niños 1", 100, "https://pngimg.com/uploads/free/free_PNG90747.png", 0, "principiante",
            "Este reto está diseñado para niños que recién empiezan a expresarse.",
            "1. Habla sobre tu juguete favorito.\n2. Muestra una sonrisa.\n3. Mira a los ojos.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Reto para adolescentes", 200, "https://placehold.co/150.png", 15, "intermedio",
            "Reto para practicar una conversación con alguien nuevo en la escuela.",
            "1. Saluda primero.\n2. Haz una pregunta sencilla.\n3. Escucha con atención.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Reto para jóvenes", 150, "https://placehold.co/150.png", 20, "avanzado",
            "Desafía tus miedos iniciando una conversación en un grupo.",
            "1. Prepárate mentalmente.\n2. No tengas miedo al silencio.\n3. Sé tú mismo.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Reto para adultos jóvenes", 300, "https://placehold.co/150.png", 25, "intermedio",
            "Prueba tu capacidad de hablar en público durante una reunión.",
            "1. Ensaya previamente.\n2. Habla lento y claro.\n3. Usa ejemplos.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Reto universal", 100, "https://placehold.co/150.png", 11, "principiante",
            "Un reto básico para romper el hielo.",
            "1. Haz contacto visual.\n2. Di 'hola' a alguien.\n3. Practica diario.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Primer reto social", 120, "https://placehold.co/150.png", 12, "principiante",
            "Aprende a iniciar una conversación con un conocido.",
            "1. Usa su nombre.\n2. Comenta algo positivo.\n3. Sé amigable.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Exposición sencilla", 250, "https://placehold.co/150.png", 18, "intermedio",
            "Realiza una mini presentación frente a 3 personas.",
            "1. Usa notas.\n2. Respira antes de hablar.\n3. Sonríe.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Reto de empatía", 130, "https://placehold.co/150.png", 13, "principiante",
            "Conversa con alguien y escucha sin interrumpir.",
            "1. Haz preguntas.\n2. No interrumpas.\n3. Valida lo que dicen.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Desafío de grupo", 180, "https://placehold.co/150.png", 17, "intermedio",
            "Participa en una dinámica grupal compartiendo ideas.",
            "1. Sé breve.\n2. Apoya a otros.\n3. Da ejemplos.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Reto de presentación", 220, "https://placehold.co/150.png", 19, "avanzado",
            "Haz una presentación frente a la clase o equipo.",
            "1. Prepara diapositivas.\n2. Controla el ritmo.\n3. Mira al público.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Confianza 1", 90, "https://placehold.co/150.png", 10, "principiante",
            "Levanta la mano para participar en clase.",
            "1. Respira profundo.\n2. Prepárate.\n3. Habla alto.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Confianza 2", 160, "https://placehold.co/150.png", 16, "intermedio",
            "Inicia una conversación con un adulto.",
            "1. Saluda cordialmente.\n2. Muestra interés.\n3. Da las gracias.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Reto visual", 80, "https://placehold.co/150.png", 9, "principiante",
            "Mira a los ojos durante una conversación.",
            "1. No fuerces.\n2. Alterna miradas.\n3. Relájate.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Reto de presentación corta", 200, "https://placehold.co/150.png", 21, "intermedio",
            "Presenta un tema por 1 minuto.",
            "1. Elige un tema conocido.\n2. Habla con claridad.\n3. Finaliza con resumen.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc"),

        Reto("Reto avanzado de liderazgo", 300, "https://placehold.co/150.png", 24, "avanzado",
            "Lidera una pequeña dinámica grupal.",
            "1. Da instrucciones claras.\n2. Anima a participar.\n3. Agradece al final.",
            "https://www.youtube.com/watch?v=gzivWXNW8Jc")
    )


    fun cargarRetosPorEdadYNivel(edad: Int, nivel: String) {
        val retosFiltrados = todosLosRetos.filter { reto ->
            edad >= reto.edadMinima && nivel == reto.nivel
        }
        _retos.value = retosFiltrados
    }
}