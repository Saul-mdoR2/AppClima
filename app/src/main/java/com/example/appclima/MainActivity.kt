package com.example.appclima

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    // ESTOS SON LOS ELEMENTOS QUE ESTAN EN LA ACTIVITY MAIN, DONDE VAMOS A MOSTRAR LOS DATOS.
    var tvCiudad: TextView? = null
    var tvGrados: TextView? = null
    var tvEstatus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvTemperatura)
        tvEstatus = findViewById(R.id.tvEstatus)

        val ciudad = intent.getStringExtra(Ciudades.TAG)

        if (Network.hayRed(this)) {
            // KEY: 7065606df090a01b0f5a6dbe83d97c06
            solicitudHTTPVolley("http://api.openweathermap.org/data/2.5/weather?id=$ciudad&appid=7065606df090a01b0f5a6dbe83d97c06&units=metric&lang=es")
        } else {
            Toast.makeText(this, "No hay conexión a internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun solicitudHTTPVolley(url: String) {
        // LA COLA PARA ALMACENAR LAS SOLICITUDES
        val queue = Volley.newRequestQueue(this)

        // STRINGREQUEST VA A REGRESAR RESULTADO EN STRING. PIDE VARIOS PARAMETROS:
        // 1. EL TIPO DE SOLICITUD 2. EL URL  3. UN LISTENER DE RESPUESTAS. EN ESTE CASO SERÍA UN LISTENER DE STRINGS  4. UN ERRORLISTENER
        val solicitud =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                try {
                    Log.d("solicitudHTTPVolley", response)

                    val gson = Gson()
                    // LA RESPUESTA DE LA API LO MAPEA A LA CLASE CIUDAD
                    val ciudad = gson.fromJson(response, Ciudad::class.java)
                    tvCiudad?.text = ciudad.name
                    tvGrados?.text = ciudad.main?.temp.toString() + "°"
                    tvEstatus?.text = ciudad.weather?.get(0)?.description
                } catch (e: Exception) {
                }
            }, Response.ErrorListener { })
        // SE AGREGA LA SOLICITUD A LA COLA
        queue.add(solicitud)
    }
}