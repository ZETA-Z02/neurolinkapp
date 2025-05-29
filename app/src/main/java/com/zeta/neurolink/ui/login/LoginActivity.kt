package com.zeta.neurolink.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.zeta.neurolink.MainActivity
import com.zeta.neurolink.databinding.ActivityLoginBinding
import com.zeta.neurolink.R
import com.zeta.neurolink.ui.Preguntas
import com.zeta.neurolink.ui.crearcuenta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.jvm.java

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // MODO OSCURO DESACTIVADO NO TOCAR
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        //
        setContentView(R.layout.activity_login) // Primero establecer el layout
        // CREAR CUENTA BUTTON
        val btnCrearCuenta = findViewById<Button>(R.id.crearcuenta)
        btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, crearcuenta::class.java)
            startActivity(intent)
        }
        // INGRESAR AL SISTEMA BUTTON
        val btnIngresar = findViewById<Button>(R.id.login)
        btnIngresar.setOnClickListener {
            login()
        }

    }
    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
    private fun login() {
        val username = findViewById<EditText>(R.id.username).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Usuario y contraseña son requeridos", Toast.LENGTH_SHORT).show()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Primer request: Login
                val loginResponse = makePostRequest(
                    url = "https://zetta.alwaysdata.net/neurolink/login/loginCliente",
                    params = "usuario=$username&password=$password"
                )

                if (loginResponse.code == 200) {
                    val loginJson = JSONObject(loginResponse.body)
                    if (loginJson.getBoolean("success") && !loginJson.getBoolean("error")) {
                        val idusuario = loginJson.getJSONObject("data").getInt("idusuario")

                        // Segundo request: Obtener datos del usuario
                        val userDataResponse = makePostRequest(
                            url = "https://zetta.alwaysdata.net/neurolink/clientes/getById",
                            params = "idusuario=$idusuario"
                        )

                        handleUserDataResponse(userDataResponse, idusuario)
                    } else {
                        withContext(Dispatchers.Main) {
                            //Toast.makeText(this, "Acceso Denegado", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        //Toast.makeText(this, "ERROR EN EL SERVIDOR", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    //Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("API ERROR", e.message, e)
                }
            }
        }
    }

    private suspend fun makePostRequest(url: String, params: String): Response {
        val conn = URL(url).openConnection() as HttpURLConnection
        return try {
            conn.apply {
                requestMethod = "POST"
                doOutput = true
                setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                outputStream.use { it.write(params.toByteArray()) }
            }

            Response(
                code = conn.responseCode,
                body = conn.inputStream.bufferedReader().use { it.readText() }
            )
        } finally {
            conn.disconnect()
        }
    }

    private fun handleUserDataResponse(response: Response, idusuario: Int) {
        if (response.code == 200) {
            val json = JSONObject(response.body)
            if (json.getBoolean("success") && !json.getBoolean("error")) {
                val data = json.getJSONObject("data")
                val puntaje = data.getString("puntaje")

                val destination = if (puntaje == "1") {
                    MainActivity::class.java
                } else {
                    Preguntas::class.java
                }

                runOnUiThread {
                    val prefs = getSharedPreferences("sesion", MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor.putInt("idusuario", idusuario)
                    editor.apply()
                    val intent = Intent(this, destination).apply {
                        putExtra("idusuario", idusuario)
                    }
                    startActivity(intent)
                    finish()
                }
            }
        } else {
            runOnUiThread {
                Toast.makeText(this, "Error al obtener datos del usuario", Toast.LENGTH_LONG).show()
            }
        }
    }

    data class Response(val code: Int, val body: String)

}