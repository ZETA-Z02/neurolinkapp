package com.zeta.neurolink.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zeta.neurolink.MainActivity
import com.zeta.neurolink.R
import com.zeta.neurolink.ui.login.LoginActivity
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.HttpURLConnection
import java.net.URL
import java.util.Calendar

class crearcuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        enableEdgeToEdge()
        setContentView(R.layout.activity_crearcuenta)

        //fecha
        fechaClick()
        //genero
        val generos = listOf("Masculino", "Femenino", "Otro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, generos)
        val inputGenero = findViewById<AutoCompleteTextView>(R.id.inputGenero)
        inputGenero.setAdapter(adapter)
        // boton crear
        val btnregistrar = findViewById<Button>(R.id.btnCrearCuenta)
        btnregistrar.setOnClickListener {
            registrar()
//            val intent = Intent(this, Terminos::class.java)
//            startActivity(intent)
//            finish()
        }
    }
    private fun registrar(){
        val correo = findViewById<TextView>(R.id.inputCorreo).text.toString()
        val password = findViewById<TextView>(R.id.inputPassword).text.toString()
        val nombres = findViewById<TextView>(R.id.inputNombre).text.toString()
        val apellidos = findViewById<TextView>(R.id.inputApellido).text.toString()
        val fechaNac = findViewById<TextView>(R.id.inputEdad).text.toString()
        val genero = findViewById<TextView>(R.id.inputGenero).text.toString()
        if(correo.isEmpty() || password.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || fechaNac.isEmpty()){
            Toast.makeText(this,"Datos Vacios",Toast.LENGTH_LONG).show()
            return
        }
        Thread{
            try{
                val url = URL("https://zetta.alwaysdata.net/neurolink/login/crearUsuario")
                val postData = "email=$correo&password=$password&nombres=$nombres&apellidos=$apellidos&fechaNac=$fechaNac&genero=$genero&ciudad=ciudad"
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.doOutput = true
                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded")
                conn.outputStream.use{it.write(postData.toByteArray())}
                val responseCode = conn.responseCode
                val response = conn.inputStream.bufferedReader().use {it.readText()}
                runOnUiThread{
                    if(responseCode == 200){
                        // Puedes parsear el json usando JSONObject
                        val respuestajson = JSONObject(response)
                        val success = respuestajson.getBoolean("response")
                        val error = respuestajson.getBoolean("error")
                        if(success && !error){
                            val intent = Intent(this, Terminos::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Intente de nuevo",Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this,"ERROR EN EL SERVIDOR",Toast.LENGTH_LONG).show()
                    }
                }
            }catch(e:Exception){
                runOnUiThread{
                    Toast.makeText(this,"${e.message}",Toast.LENGTH_LONG).show()
                    Log.e("API ERROR", "${e.message}")
                }
            }
        }.start()
        Toast.makeText(this,"Creando cuenta....", Toast.LENGTH_SHORT ).show()
    }
    private fun fechaClick(){
        val fecha = findViewById<TextView>(R.id.inputEdad)
        fecha.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // Mostrar la fecha en el campo
                val fechaTexto = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                fecha.setText(fechaTexto)
            }, year, month, day)
            datePickerDialog.show()
        }
    }
}