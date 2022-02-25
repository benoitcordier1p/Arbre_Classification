package com.example.arbre_classification.presentation.treesList

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.arbre_classification.util.BaseViewModel
import com.example.arbre_classification.util.ConnectionManager
import com.example.arbre_classification.util.Constants
import com.example.domain.models.Tree
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreesListViewModel @Inject constructor(
    private val getTreesUseCase: GetTreesUseCase,
    context: Context
) : BaseViewModel(context), ConnectionManager.ConnectionManagerListener {

    //State. Updated when new tress are loaded.
    private val _state = mutableStateOf<List<Tree>>(emptyList())
    var state: State<List<Tree>> = _state

    //Variables to define UI
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var lastTree = false
    var offline = mutableStateOf(false)

    //Variable used for lazy loading, updated when the user to scroll to the bottom of the list
    private var index = 0

    fun onStart() {
        connectionManager.addListener(this)
        getTrees()
    }

    fun onStop(){
        connectionManager.removeListener(this)
    }

    fun getTrees() {
        viewModelScope.launch {
            getTreesUseCase.invoke(index, getFetchStrategy()).collect {
                println("${getFetchStrategy()} and ${it.data}")
                when (it) {
                    is Resource.Success -> {
                        lastTree = Constants.NUMBER_OF_ROWS * index >= it.data!!.size
                        _state.value += it.data as List<Tree>
                    }
                    is Resource.Loading -> isLoading.value = true
                    is Resource.Error -> error.value = it.message!!
                }
            }
            isLoading.value = false
            index += 1
        }
    }

    override fun onConnectionChanged(state: ConnectionManager.ConnectionManagerListener.ConnectionState) {
        offline.value = state == ConnectionManager.ConnectionManagerListener.ConnectionState.OFFLINE
    }
}
