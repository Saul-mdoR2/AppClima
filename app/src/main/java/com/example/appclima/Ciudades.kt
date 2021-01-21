package com.example.appclima

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Ciudades : AppCompatActivity() {

    companion object {
        const val TAG = "com.example.appclima.ciudades.CIUDAD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val bMexico = findViewById<Button>(R.id.btnMexico)
        val bParis = findViewById<Button>(R.id.btnParis)
        val bShanghai = findViewById<Button>(R.id.btnShanghai)
        val bSabinas = findViewById<Button>(R.id.btnSabinas)

        bMexico.setOnClickListener {
            intentSiguienteActividad("3527646")
        }

        bParis.setOnClickListener {
            intentSiguienteActividad("2988507")
        }

        bShanghai.setOnClickListener {
            intentSiguienteActividad("1796236")
        }

        bSabinas.setOnClickListener {
            intentSiguienteActividad("3988333")
        }

    }

    fun intentSiguienteActividad(id: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(TAG, id)
        startActivity(intent)
    }
}