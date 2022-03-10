package com.example.arbre_classification.presentation.treesList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.arbre_classification.util.ConnectionManager
import com.example.arbre_classification.util.Constants
import com.example.data.remote.errorHandler.ErrorEntity
import com.example.domain.models.Tree
import com.example.domain.useCase.addTreeUseCase.AddTreeUseCase
import com.example.domain.useCase.deleteTreeUseCase.DeleteTreeUseCase
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    connectionManager: ConnectionManager,
) : BaseViewModel(connectionManager) {

    //State. Updated when new tress are loaded.
    private val _state = mutableStateOf<List<Tree>>(listOf())
    val state: State<List<Tree>> = _state
    var savedTreeList = listOf<Tree>()

    //Variables to define UI
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var lastTree = mutableStateOf(false)

    //State used to refresh the list on connection changes
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    //Variable for search
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    //Variable used for lazy loading, updated when the user to scroll to the bottom of the list
    private var index = -1

    init {
        getTrees(false)
    }

    fun getTrees(force: Boolean) {
        viewModelScope.launch {
            index += 1
            error.value=""
            getTreesUseCase.invoke(Constants.NUMBER_OF_ROWS * index, getFetchStrategy(force)).collect {
                when (it) {
                    is Resource.Success -> {
                        lastTree.value = Constants.NUMBER_OF_ROWS * index > it.data!!.size
                        _state.value+=(it.data as List<Tree>)
                        addTreeUseCase(it.data!!)
                    }
                    is Resource.Loading -> isLoading.value = true
                    is Resource.Error -> error.value = when(it.error){
                        is ErrorEntity.Network -> "A network error has occurred"
                        is ErrorEntity.NotFound -> "API endpoint not found"
                        is ErrorEntity.ServiceUnavailable -> "Service Unavailable. Check your internet connection"
                        else -> "An unexpected error has occurred. Please try again later"
                    }
                }
            }
            isLoading.value = false
        }
    }

    fun deleteTree(position:Int){
        viewModelScope.launch {
            deleteTreeUseCase(_state.value[position].id)
        }
        val mutableClone = _state.value as MutableList<Tree>
        mutableClone.removeAt(position)
        _state.value = mutableClone
    }

    fun searchTree(query: String) {
        val listToSearch = if(isSearchStarting) {
            _state.value
        } else {
            savedTreeList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                _state.value = savedTreeList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.id.contains(query.trim(), ignoreCase = true) ||
                        it.espece.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                savedTreeList = _state.value
                isSearchStarting = false
            }
            _state.value = results
            isSearching.value = true
        }
    }

    fun forceRefresh(){
        index=-1
        val mutableClone = _state.value as MutableList<Tree>
        mutableClone.clear()
        _state.value = mutableClone
        getTrees(true)
    }
}
