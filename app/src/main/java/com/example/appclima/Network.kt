package com.example.appclima

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity

class Network {

    companion object{
        fun hayRed(activity: AppCompatActivity):Boolean{
            // EL OBJETO CONNECTIVITYMANAGER ADMINISTRA TEMAS DE RED.
            // EL METODO GETSYSTEMSERVICE FORMA PARTE DE UNA ACTIVIDAD.
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(networkInfo)

            return if(capabilities!=null){
                networkInfo != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            } else false
        }
    }
}