package com.example.arbre_classification.presentation.treesList

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.arbre_classification.util.ConnectionManager
import com.example.arbre_classification.util.Constants
import com.example.domain.models.Tree
import com.example.domain.useCase.addTreeUseCase.AddTreeUseCase
import com.example.domain.useCase.deleteTreeUseCase.DeleteTreeUseCase
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreesListViewModel @Inject constructor(
    private val getTreesUseCase: GetTreesUseCase,
    private val addTreeUseCase: AddTreeUseCase,
    private val deleteTreeUseCase: DeleteTreeUseCase,
    private val connectionManager: ConnectionManager,
    context: Context
) : BaseViewModel(context,connectionManager),
    ConnectionManager.ConnectionManagerListener {

    //State. Updated when new tress are loaded.
    private val _state = mutableStateOf<MutableList<Tree>>(mutableListOf())
    val state: State<List<Tree>> = _state

    //Variables to define UI
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var lastTree = mutableStateOf(false)
    var offline = mutableStateOf(false)
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    //Variable used for lazy loading, updated when the user to scroll to the bottom of the list
    private var index = -1

    fun onStart() {
        connectionManager.addListener(this)
        getTrees(false)
    }

    fun onStop() {
        connectionManager.removeListener(this)
    }

    fun getTrees(force: Boolean) {
        viewModelScope.launch {
            index += 1
            if(force) _state.value.clear()
            getTreesUseCase.invoke(Constants.NUMBER_OF_ROWS * index, getFetchStrategy(force)).collect {
                when (it) {
                    is Resource.Success -> {
                        lastTree.value = Constants.NUMBER_OF_ROWS * index >= it.data!!.size
                        _state.value.addAll(it.data as List<Tree>)
                        addTreeUseCase(it.data!!)
                    }
                    is Resource.Loading -> isLoading.value = true
                    is Resource.Error -> error.value = it.message!!
                }
            }
            isLoading.value = false
        }
    }

    fun deleteTree(position:Int){
        println("delete")
        viewModelScope.launch {
            deleteTreeUseCase(_state.value[position].id)
        }
        _state.value.removeAt(position)
    }

    override fun onConnectionChanged(state: ConnectionManager.ConnectionManagerListener.ConnectionState) {
        offline.value = state == ConnectionManager.ConnectionManagerListener.ConnectionState.OFFLINE
    }

    fun forceRefresh(){
        index=-1
        getTrees(true)
    }
}
