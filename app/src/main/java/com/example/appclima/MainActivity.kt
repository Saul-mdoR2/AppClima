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

    var tvCiudad: TextView? = null
    var tvGrados: TextView? = null
    var tvEstatus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvTemperatura)
        tvEstatus = findViewById(R.id.tvEstatus)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(Ciudades.TAG)

        if (Network.hayRed(this)) {
            // KEY: 7065606df090a01b0f5a6dbe83d97c06
            solicitudHTTPVolley("http://api.openweathermap.org/data/2.5/weather?id=$id&appid=7065606df090a01b0f5a6dbe83d97c06&units=metric&lang=es")
        } else {
            Toast.makeText(this, "No hay conexión a internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun solicitudHTTPVolley(url: String) {
        val queue = Volley.newRequestQueue(this)

        val solicitud =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                try {
                    Log.d("solicitudHTTPVolley", response)

                    val gson = Gson()
                    val cd = gson.fromJson(response, Ciudad::class.java)
                    tvCiudad?.text = cd.name
                    tvGrados?.text =
                        resources.getString(R.string.formato_temperatura, cd.main?.temp.toString())
                    tvEstatus?.text = cd.weather?.get(0)?.description
                } catch (e: Exception) {
                }
            }, Response.ErrorListener { })
        queue.add(solicitud)
    }
}