package com.example.arbre_classification.presentation.treesList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.arbre_classification.util.ConnectionManager
import com.example.domain.fetchstrategy.FetchStrategy

open class BaseViewModel constructor(
    private val connectionManager: ConnectionManager
) : ViewModel(), ConnectionManager.ConnectionManagerListener {

    var offline = mutableStateOf(connectionManager.offline)

    init {
        connectionManager.addListener(this)
    }

    protected fun getFetchStrategy(force : Boolean) : FetchStrategy {
        return when (!connectionManager.offline) {
            true -> {
                if(force) FetchStrategy.Remote
                else FetchStrategy.Cache
            }
            false -> FetchStrategy.Local
        }
    }

    override fun onConnectionChanged(state: ConnectionManager.ConnectionManagerListener.ConnectionState) {
        offline.value = state == ConnectionManager.ConnectionManagerListener.ConnectionState.OFFLINE
    }

    override fun onCleared() {
        super.onCleared()
        connectionManager.removeListener(this)
    }
}