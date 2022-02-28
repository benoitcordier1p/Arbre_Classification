package com.example.arbre_classification.presentation.treesList

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.arbre_classification.util.ConnectionManager
import com.example.domain.fetchstrategy.FetchStrategy

open class BaseViewModel constructor(
    context: Context
) : ViewModel() {

    protected val connectionManager = ConnectionManager(context)

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