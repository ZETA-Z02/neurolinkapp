package com.zeta.neurolink.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.zeta.neurolink.MainActivity
import com.zeta.neurolink.databinding.ActivityLoginBinding

import com.zeta.neurolink.R
import com.zeta.neurolink.ui.crearcuenta
import com.zeta.neurolink.ui.home.HomeFragment
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.HttpURLConnection
import java.net.URL
import kotlin.jvm.java
import kotlin.time.TestTimeSource

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
    private fun login(){
        val username = findViewById<TextView>(R.id.username).text.toString()
        val password = findViewById<TextView>(R.id.password).text.toString()
        //Toast.makeText(this,"$username - $password",Toast.LENGTH_LONG).show()
        Thread{
            try{
                val url = URL("https://zetta.alwaysdata.net/neurolink/login/loginCliente")
                val postData = "usuario=$username&password=$password"
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
                        val success = respuestajson.getBoolean("success")
                        val error = respuestajson.getBoolean("error")
                        if(success && !error){
                            val data = respuestajson.getJSONObject("data")
                            val idusuario = data.getInt("idusuario")
                            Toast.makeText(this,"Login correcto. ID: $idusuario",Toast.LENGTH_LONG).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("idusuario",idusuario)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Acceso Denegado",Toast.LENGTH_LONG).show()
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
        // Toast.makeText(this, "$username - $password", Toast.LENGTH_SHORT).show()

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
}