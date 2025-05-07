package com.zeta.neurolink.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zeta.neurolink.R
import com.zeta.neurolink.ui.login.LoginActivity

class Terminos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_terminos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // BOTONES
        val checkbox = findViewById<CheckBox>(R.id.checkBox)
        val btncontinuar = findViewById<Button>(R.id.btncontinuar)
        // FUNCIONALIDAD
        btncontinuar.setOnClickListener{
            if(checkbox.isChecked){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Acepta los terminos", Toast.LENGTH_LONG).show()
            }
        }
    }
    // verifica si se activo el checkbox
    private fun verificar(){}
}