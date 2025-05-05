package com.zeta.neurolink

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.postDelayed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zeta.neurolink.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private var progressStatus = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        // 3 componente para la ventana
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // END
        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.progressText)
        Thread{
            while (progressStatus < 100){
                progressStatus += 2
                Thread.sleep(40)

                runOnUiThread {
                    progressBar.progress = progressStatus
                    progressText.text = "Cargando... $progressStatus%"
                }
            }
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }.start()

    }
}