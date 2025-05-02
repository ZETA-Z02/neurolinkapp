package com.zeta.neurolink.ui

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zeta.neurolink.R

class crearcuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        enableEdgeToEdge()
        setContentView(R.layout.activity_crearcuenta)

        var btnregistrar = findViewById<Button>(R.id.btnCrearCuenta)
        btnregistrar.setOnClickListener {
            registrar("HOLA DESDE LA LOGICA")
        }

    }
    public fun registrar(mensaje:String){
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT ).show()
    }
}