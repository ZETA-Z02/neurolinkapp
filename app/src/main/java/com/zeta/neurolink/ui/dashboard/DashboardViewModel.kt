package com.zeta.neurolink.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class DashboardViewModel : ViewModel() {

    private val _retos = MutableLiveData<List<Reto>>()
    val retos: LiveData<List<Reto>> = _retos

    private val todosLosRetos = listOf(
        Reto("Reto 1:Nombrar 5 emociones sentidas hoy", 50,
            "https://previews.123rf.com/images/onyxprj/onyxprj2111/onyxprj211100015/176580968-concepto-de-salud-mental-cabeza-humana-con-flores-%C3%A1mate-a-ti-mismo-y-mente-limpia-ilustraci%C3%B3n-vector.jpg",
            0, "Inteligencia Emocional",
            "Identifica las emociones que experimentaste durante el día.",
            "1. Piensa en los momentos clave de tu día.\n2. Nombra la emoción sentida.\n3. Reflexiona por qué la sentiste.",
            "https://www.youtube.com/watch?v=0XYfVTTTVV0"),

        Reto("Reto 2: Escribir cómo reacciones ante enojo", 100,
            "https://previews.123rf.com/images/onyxprj/onyxprj2111/onyxprj211100015/176580968-concepto-de-salud-mental-cabeza-humana-con-flores-%C3%A1mate-a-ti-mismo-y-mente-limpia-ilustraci%C3%B3n-vector.jpg",
            15, "Inteligencia Emocional",
            "Analiza tus reacciones cuando sientes enojo.",
            "1. Describe una situación reciente.\n2. Anota cómo actuaste.\n3. Piensa cómo podrías mejorar esa reacción.",
            "https://www.youtube.com/watch?v=0XYfVTTTVV0"),

        Reto("Reto 3: Hacer un diario emocional por 3 días", 150,
            "https://previews.123rf.com/images/onyxprj/onyxprj2111/onyxprj211100015/176580968-concepto-de-salud-mental-cabeza-humana-con-flores-%C3%A1mate-a-ti-mismo-y-mente-limpia-ilustraci%C3%B3n-vector.jpg",
            15, "Inteligencia Emocional",
            "Explora tus emociones mediante la escritura continua.",
            "1. Dedica 5 minutos al día.\n2. Anota emociones sentidas y su intensidad.\n3. Observa patrones al tercer día.",
            "https://www.youtube.com/watch?v=0XYfVTTTVV0"),

        Reto("Reto 4: Aplicar la rueda de emociones en una discusión reciente", 200,
            "https://previews.123rf.com/images/onyxprj/onyxprj2111/onyxprj211100015/176580968-concepto-de-salud-mental-cabeza-humana-con-flores-%C3%A1mate-a-ti-mismo-y-mente-limpia-ilustraci%C3%B3n-vector.jpg",
            15, "Inteligencia Emocional",
            "Profundiza en lo que sentiste en una discusión usando la rueda emocional.",
            "1. Identifica emoción primaria y secundaria.\n2. Analiza cómo influyó en tu conducta.\n3. Reflexiona alternativas.",
            "https://www.youtube.com/watch?v=0XYfVTTTVV0"),

        Reto("Reto Final: Reconocer una emoción antes de reaccionar (y contarlo)", 300,
            "https://previews.123rf.com/images/onyxprj/onyxprj2111/onyxprj211100015/176580968-concepto-de-salud-mental-cabeza-humana-con-flores-%C3%A1mate-a-ti-mismo-y-mente-limpia-ilustraci%C3%B3n-vector.jpg",
            15, "Inteligencia Emocional",
            "Ejercita la autorregulación emocional anticipando tus reacciones.",
            "1. Narra una situación difícil.\n2. Di qué emoción anticipaste.\n3. Explica cómo eso ayudó a mejorar tu respuesta.",
            "https://www.youtube.com/watch?v=0XYfVTTTVV0"),

        Reto("Reto 1: Saludar a alguien nuevo hoy", 100,
            "https://media.istockphoto.com/id/1383459268/es/vector/ataque-de-p%C3%A1nico-en-lugar-superpoblado-ilustraci%C3%B3n-vectorial-a-color-plano.jpg?s=612x612&w=0&k=20&c=G3tcQBGb7sNKF3Cj5J-7JMsv_KQpfrNaZakGBPxvUlQ=",
            15, "Ansiedad Social y Timidez",
            "Practica una interacción simple para romper el hielo.",
            "1. Identifica a alguien que no conoces.\n2. Salúdalo con confianza.\n3. Anota cómo te sentiste.",
            "https://www.youtube.com/watch?v=xMXdUgeqCAA"),

        Reto("Reto 2: Mantener contacto visual 10 segundos", 150,
            "https://media.istockphoto.com/id/1383459268/es/vector/ataque-de-p%C3%A1nico-en-lugar-superpoblado-ilustraci%C3%B3n-vectorial-a-color-plano.jpg?s=612x612&w=0&k=20&c=G3tcQBGb7sNKF3Cj5J-7JMsv_KQpfrNaZakGBPxvUlQ=",
            15, "Ansiedad Social y Timidez",
            "Fortalece la seguridad al comunicarte mirando a los ojos.",
            "1. Elige a alguien de confianza.\n2. Conversa y míralo a los ojos.\n3. Cronometra el tiempo y describe tu experiencia.",
            "https://www.youtube.com/watch?v=xMXdUgeqCAA"),

        Reto("Reto 3: Preguntar algo a un desconocido (chat o presencial)", 200,
            "https://media.istockphoto.com/id/1383459268/es/vector/ataque-de-p%C3%A1nico-en-lugar-superpoblado-ilustraci%C3%B3n-vectorial-a-color-plano.jpg?s=612x612&w=0&k=20&c=G3tcQBGb7sNKF3Cj5J-7JMsv_KQpfrNaZakGBPxvUlQ=",
            15, "Ansiedad Social y Timidez",
            "Enfrenta la incomodidad de hablar con extraños.",
            "1. Piensa en una pregunta sencilla.\n2. Acércate con respeto.\n3. Agradece su respuesta y anota cómo fue.",
            "https://www.youtube.com/watch?v=xMXdUgeqCAA"),

        Reto("Reto 4: Hablar 30 segundos en voz alta en público o con 3 personas", 300,
            "https://media.istockphoto.com/id/1383459268/es/vector/ataque-de-p%C3%A1nico-en-lugar-superpoblado-ilustraci%C3%B3n-vectorial-a-color-plano.jpg?s=612x612&w=0&k=20&c=G3tcQBGb7sNKF3Cj5J-7JMsv_KQpfrNaZakGBPxvUlQ=",
            15, "Ansiedad Social y Timidez",
            "Desarrolla habilidades para hablar frente a grupos.",
            "1. Prepara un tema breve.\n2. Ensaya antes.\n3. Habla con claridad y confianza.",
            "https://www.youtube.com/watch?v=xMXdUgeqCAA"),

        Reto("Reto Final: Participar en una conversación grupal y compartir cómo te sentiste", 400,
            "https://media.istockphoto.com/id/1383459268/es/vector/ataque-de-p%C3%A1nico-en-lugar-superpoblado-ilustraci%C3%B3n-vectorial-a-color-plano.jpg?s=612x612&w=0&k=20&c=G3tcQBGb7sNKF3Cj5J-7JMsv_KQpfrNaZakGBPxvUlQ=",
            15, "Ansiedad Social y Timidez",
            "Involúcrate activamente en una conversación grupal.",
            "1. Escucha con atención.\n2. Aporta al menos una idea.\n3. Reflexiona sobre tu experiencia.",
            "https://www.youtube.com/watch?v=xMXdUgeqCAA"),

        Reto("Reto 1: Escribir 3 pensamientos negativos y reemplazarlos por 3 positivos",
            100, "https://media.istockphoto.com/id/1446995300/es/vector/mujer-fuerte-linda-chica-y-brazos-musculosos-detr%C3%A1s-de-ella-independencia-mente-propia-y.jpg?s=612x612&w=0&k=20&c=h9HSV2V9xi8E_gRhICnFxJsAT_n0QXwk9BN0S_bvcxs=",
            15, "Miedo al Ridiculo y Baja Autoestima",
            "Entrena tu mente para transformar lo negativo en constructivo.",
            "1. Identifica 3 pensamientos limitantes.\n2. Crea una versión positiva de cada uno.\n3. Repítelos en voz alta.",
            "https://www.youtube.com/watch?v=v3SuHJavE6A"),

        Reto("Reto 2: Grabar un audio haciendo un reto “ridículo” (ej: cantar)",
            200, "https://media.istockphoto.com/id/1446995300/es/vector/mujer-fuerte-linda-chica-y-brazos-musculosos-detr%C3%A1s-de-ella-independencia-mente-propia-y.jpg?s=612x612&w=0&k=20&c=h9HSV2V9xi8E_gRhICnFxJsAT_n0QXwk9BN0S_bvcxs=",
            15, "Miedo al Ridiculo y Baja Autoestima",
            "Desafía tu temor al qué dirán haciendo algo fuera de lo común.",
            "1. Elige un reto que te dé vergüenza.\n2. Grábalo sin juzgarte.\n3. Escúchalo con amabilidad.",
            "https://www.youtube.com/watch?v=v3SuHJavE6A"),

        Reto("Reto 3: Hacer algo sin preocuparte por la opinión ajena (y registrarlo)",
            300, "https://media.istockphoto.com/id/1446995300/es/vector/mujer-fuerte-linda-chica-y-brazos-musculosos-detr%C3%A1s-de-ella-independencia-mente-propia-y.jpg?s=612x612&w=0&k=20&c=h9HSV2V9xi8E_gRhICnFxJsAT_n0QXwk9BN0S_bvcxs=",
            15, "Miedo al Ridiculo y Baja Autoestima",
            "Libérate del juicio externo haciendo algo por ti.",
            "1. Escoge una acción que te gustaría hacer.\n2. Hazla sin consultar a nadie.\n3. Escribe cómo te sentiste.",
            "https://www.youtube.com/watch?v=v3SuHJavE6A"),

        Reto("Reto 4: Crear un mantra personal de autovaloración y repetirlo por 5 días",
            200, "https://media.istockphoto.com/id/1446995300/es/vector/mujer-fuerte-linda-chica-y-brazos-musculosos-detr%C3%A1s-de-ella-independencia-mente-propia-y.jpg?s=612x612&w=0&k=20&c=h9HSV2V9xi8E_gRhICnFxJsAT_n0QXwk9BN0S_bvcxs=",
            15, "Miedo al Ridiculo y Baja Autoestima",
            "Reafirma tu autoestima con frases positivas repetidas.",
            "1. Escribe un mantra que te haga sentir valioso.\n2. Repítelo en voz alta cada mañana.\n3. Anota el cambio tras 5 días.",
            "https://www.youtube.com/watch?v=v3SuHJavE6A"),

        Reto("Reto Final: Hacer algo que antes evitabas por miedo al ridículo",
            400, "https://media.istockphoto.com/id/1446995300/es/vector/mujer-fuerte-linda-chica-y-brazos-musculosos-detr%C3%A1s-de-ella-independencia-mente-propia-y.jpg?s=612x612&w=0&k=20&c=h9HSV2V9xi8E_gRhICnFxJsAT_n0QXwk9BN0S_bvcxs=",
            15, "Miedo al Ridiculo y Baja Autoestima",
            "Atrévete a hacer algo pendiente por temor.",
            "1. Elige una situación que sueles evitar.\n2. Enfréntala con calma.\n3. Escribe tu experiencia final.",
            "https://www.youtube.com/watch?v=v3SuHJavE6A"),

        Reto("Reto 1: Escribir sobre un momento doloroso y cómo te afectó",
            150, "https://static.wixstatic.com/media/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg/v1/fill/w_720,h_480,al_c,lg_1,q_80/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg",
            15, "Trauma y Rechazo",
            "Explora tu historia emocional enfrentando un momento difícil.",
            "1. Recuerda el momento.\n2. Describe tu emoción.\n3. Escribe qué aprendiste.",
            "https://www.youtube.com/watch?v=NmbGZ-KIlJI"),

        Reto("Reto 2:Escribir sobre un momento doloroso y cómo te afectó",
            250, "https://static.wixstatic.com/media/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg/v1/fill/w_720,h_480,al_c,lg_1,q_80/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg",
            15, "Trauma y Rechazo",
            "Profundiza en otra experiencia pasada y su impacto.",
            "1. Sé sincero contigo.\n2. Identifica cómo te marcó.\n3. Describe tu resiliencia actual.",
            "https://www.youtube.com/watch?v=NmbGZ-KIlJI"),

        Reto("Reto 3: Realizar una carta de perdón (a ti o a otro) y leerla",
            300, "https://static.wixstatic.com/media/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg/v1/fill/w_720,h_480,al_c,lg_1,q_80/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg",
            15, "Trauma y Rechazo",
            "Libera emociones mediante el perdón.",
            "1. Escribe la carta desde el corazón.\n2. Léela en voz alta (puedes estar solo).\n3. Siente el alivio.",
            "https://www.youtube.com/watch?v=NmbGZ-KIlJI"),

        Reto("Reto 4: Hacer una lista de logros pese al trauma",
            200, "https://static.wixstatic.com/media/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg/v1/fill/w_720,h_480,al_c,lg_1,q_80/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg",
            15, "Trauma y Rechazo",
            "Reconoce tu fuerza más allá del dolor vivido.",
            "1. Piensa en tus logros grandes o pequeños.\n2. Anótalos todos.\n3. Léele la lista a alguien de confianza.",
            "https://www.youtube.com/watch?v=NmbGZ-KIlJI"),

        Reto("Reto Final: Comprometerse a una acción sanadora (psicoterapia, apoyo, comunidad)",
            500, "https://static.wixstatic.com/media/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg/v1/fill/w_720,h_480,al_c,lg_1,q_80/fa08a2_0b6415e7af2b4a64ac85277830a660e2~mv2.jpeg",
            15, "Trauma y Rechazo",
            "Da un paso real hacia tu bienestar emocional.",
            "1. Elige una acción concreta.\n2. Haz un plan para cumplirla.\n3. Compártelo con alguien.",
            "https://www.youtube.com/watch?v=NmbGZ-KIlJI"),

        Reto("Reto 1: Crear tu jerarquía de miedos personales (5 niveles)",
            100, "https://media.istockphoto.com/id/1298280194/es/vector/logotipo-vectorial-en-el-que-una-imagen-abstracta-de-una-ilustraci%C3%B3n-del-trabajo-de-sisifo.jpg?s=612x612&w=0&k=20&c=Ju3KJ7RIQ7Jrjv_w0iPT5QxTXc4tpqLohX2tD4rEDj4=",
            15, "Adaptación y Resiliencia",
            "Organiza tus miedos para enfrentarlos de forma progresiva.",
            "1. Anota 5 miedos de menor a mayor intensidad.\n2. Describe por qué te afectan.\n3. Guarda la lista para próximos retos.",
            "https://www.youtube.com/watch?v=Xen2JWmdOf0"),

        Reto("Reto 2: Completar el primer nivel sin evitarlo",
            200, "https://media.istockphoto.com/id/1298280194/es/vector/logotipo-vectorial-en-el-que-una-imagen-abstracta-de-una-ilustraci%C3%B3n-del-trabajo-de-sisifo.jpg?s=612x612&w=0&k=20&c=Ju3KJ7RIQ7Jrjv_w0iPT5QxTXc4tpqLohX2tD4rEDj4=",
            15, "Adaptación y Resiliencia",
            "Enfrenta tu miedo más leve y vence la evitación.",
            "1. Actúa frente al miedo nivel 1.\n2. Registra lo que sentiste.\n3. Celebra el avance.",
            "https://www.youtube.com/watch?v=Xen2JWmdOf0"),

        Reto("Reto 3: Usar una técnica de relajación antes de una situación tensa",
            150, "https://media.istockphoto.com/id/1298280194/es/vector/logotipo-vectorial-en-el-que-una-imagen-abstracta-de-una-ilustraci%C3%B3n-del-trabajo-de-sisifo.jpg?s=612x612&w=0&k=20&c=Ju3KJ7RIQ7Jrjv_w0iPT5QxTXc4tpqLohX2tD4rEDj4=",
            15, "Adaptación y Resiliencia",
            "Aprende a gestionar el estrés antes de actuar.",
            "1. Elige una técnica (respiración, mindfulness, etc).\n2. Practícala antes de la situación difícil.\n3. Evalúa si te ayudó.",
            "https://www.youtube.com/watch?v=Xen2JWmdOf0"),

        Reto("Reto 4: Exponerse voluntariamente a un reto nuevo (y contarlo)",
            300, "https://media.istockphoto.com/id/1298280194/es/vector/logotipo-vectorial-en-el-que-una-imagen-abstracta-de-una-ilustraci%C3%B3n-del-trabajo-de-sisifo.jpg?s=612x612&w=0&k=20&c=Ju3KJ7RIQ7Jrjv_w0iPT5QxTXc4tpqLohX2tD4rEDj4=",
            15, "Adaptación y Resiliencia",
            "Sal de tu zona de confort enfrentando lo desconocido.",
            "1. Elige un reto que nunca hiciste.\n2. Hazlo de forma segura.\n3. Cuenta la experiencia a alguien.",
            "https://www.youtube.com/watch?v=Xen2JWmdOf0"),

        Reto("Reto Final: Alcanzar el nivel más alto de tu jerarquía",
            500, "https://media.istockphoto.com/id/1298280194/es/vector/logotipo-vectorial-en-el-que-una-imagen-abstracta-de-una-ilustraci%C3%B3n-del-trabajo-de-sisifo.jpg?s=612x612&w=0&k=20&c=Ju3KJ7RIQ7Jrjv_w0iPT5QxTXc4tpqLohX2tD4rEDj4=",
            15, "Adaptación y Resiliencia",
            "Enfrenta tu mayor miedo de la jerarquía.",
            "1. Prepárate bien.\n2. Realiza la acción con calma.\n3. Evalúa tu progreso general.",
            "https://www.youtube.com/watch?v=Xen2JWmdOf0")
    )


    fun cargarRetosPorEdadYNivel(edad: Int, nivel: String) {
        val retosFiltrados = todosLosRetos.filter { reto ->
            edad >= reto.edadMinima && nivel == reto.nivel
        }
        _retos.value = retosFiltrados
    }
}