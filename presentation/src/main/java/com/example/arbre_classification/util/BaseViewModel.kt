package com.example.arbre_classification.util

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.domain.fetchstrategy.FetchStrategy

open class BaseViewModel constructor(
    context: Context
) : ViewModel() {

    protected val connectionManager = ConnectionManager(context)

    protected fun getFetchStrategy() : FetchStrategy {
        return when (!connectionManager.offline) {
            true -> FetchStrategy.Remote
            false -> FetchStrategy.Local
        }
    }
}