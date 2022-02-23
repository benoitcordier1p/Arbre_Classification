package com.example.arbre_classification.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class ConnectionManager(context: Context) {

    //State. Updated when connection updated.
    private val _state = mutableStateOf(false)
    var state: State<Boolean> = _state

    init {
        getConnection(context)
    }

    private fun getConnection(context: Context){
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                try {
                    _state.value = false
                } catch (e :Exception){
                    println("Too soon")
                }

            }

            override fun onLost(network: Network) {
                _state.value = true
            }
        })
    }
}