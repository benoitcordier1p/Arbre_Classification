package com.example.arbre_classification.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network

class ConnectionManager(private val context: Context) {

    interface ConnectionManagerListener {

        enum class ConnectionState {
            ONLINE,
            OFFLINE
        }

        fun onConnectionChanged(state: ConnectionState)
    }


    //State. Updated when connection updated.
    var offline = false
    private val listeners = mutableListOf<ConnectionManagerListener>()

    init {
        getConnection()
    }

    fun addListener(listener: ConnectionManagerListener) {
        listeners.add(listener)
        listener.onConnectionChanged(if (offline) {
            ConnectionManagerListener.ConnectionState.OFFLINE
        } else {
            ConnectionManagerListener.ConnectionState.ONLINE
        })
    }

    fun removeListener(listener: ConnectionManagerListener) {
        listeners.remove(listener)
    }

    private fun getConnection() {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                try {
                    offline = false
                    notifyListeners(ConnectionManagerListener.ConnectionState.ONLINE)
                } catch (e: Exception) {
                    println("Too soon")
                }

            }

            override fun onLost(network: Network) {
                offline = true
                notifyListeners(ConnectionManagerListener.ConnectionState.OFFLINE)
            }
        })
    }

    private fun notifyListeners(state: ConnectionManagerListener.ConnectionState) {
        for (listener in listeners) {
            listener.onConnectionChanged(state)
        }
    }
}

