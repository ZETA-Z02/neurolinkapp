package com.zeta.neurolink.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.zeta.neurolink.MainActivity
import com.zeta.neurolink.R

class Preguntas : AppCompatActivity() {
    // ID del usuario recibido desde Login
    var idusuario: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preguntas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.  setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        idusuario = intent.getIntExtra("idusuario", -1)

        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)
        btnSiguiente.setOnClickListener {
            val respuestas = mutableListOf<Int>()

            // Suponiendo que tienes 15 RadioGroups con IDs pregunta1, pregunta2, ..., pregunta15
            for (i in 1..15) {
                val radioGroupId = resources.getIdentifier("pregunta$i", "id", packageName)
                val radioGroup = findViewById<RadioGroup>(radioGroupId)
                val seleccion = radioGroup.checkedRadioButtonId

                if (seleccion != -1) {
                    val opcionSeleccionada = radioGroup.indexOfChild(radioGroup.findViewById(seleccion))
                    respuestas.add(opcionSeleccionada + 1)
                } else {
                    respuestas.add(0) // No respondió
                }
            }

            // Sumar total
            val puntajeTotal = respuestas.sum()
            val modulo = when (puntajeTotal) {
                in 0..24 -> 1
                in 25..34 -> 2
                in 35..44 -> 3
                in 45..54 -> 4
                in 55..75 -> 5
                else -> 0 // Error
            }

            // Enviar a servidor
            enviarClasificacion(idusuario, puntajeTotal, modulo)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("idusuario", idusuario)
            startActivity(intent)
            finish()
        }
    }

    private fun enviarClasificacion(idusuario: Int, puntaje: Int, modulo: Int) {
        val url = "https://zetta.alwaysdata.net/neurolink/clientes/update"
        val requestQueue = Volley.newRequestQueue(this)

        val postRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
                Log.d("RESPUESTA", response)
            },
            Response.ErrorListener { error ->
                Log.e("ERROR", error.toString())
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["idusuario"] = idusuario.toString()
                params["puntaje"] = "1"
                params["nivel"] = modulo.toString()
                return params
            }
        }

        requestQueue.add(postRequest)
    }
}
