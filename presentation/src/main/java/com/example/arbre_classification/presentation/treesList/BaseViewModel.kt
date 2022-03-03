package com.example.arbre_classification.presentation.treesList

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.arbre_classification.util.ConnectionManager
import com.example.domain.fetchstrategy.FetchStrategy
import javax.inject.Inject

open class BaseViewModel constructor(
    context: Context,
    private val connectionManager: ConnectionManager
) : ViewModel() {

    protected fun getFetchStrategy(force : Boolean) : FetchStrategy {
        return when (!connectionManager.offline) {
            true -> {
                if(force) FetchStrategy.Remote
                else FetchStrategy.Cache
            }
            false -> FetchStrategy.Local
        }
    }
}