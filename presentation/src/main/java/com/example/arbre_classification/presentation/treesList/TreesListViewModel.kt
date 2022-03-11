package com.example.arbre_classification.presentation.treesList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.arbre_classification.util.ConnectionManager
import com.example.arbre_classification.util.Constants
import com.example.arbre_classification.util.event.RxEventHandler
import com.example.arbre_classification.util.event.TreeEvent
import com.example.data.remote.errorHandler.ErrorEntity
import com.example.domain.models.Tree
import com.example.domain.useCase.addTreeUseCase.AddTreeUseCase
import com.example.domain.useCase.deleteTreeUseCase.DeleteTreeUseCase
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase
import com.example.domain.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.Disposable
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
    private var savedTreeList = listOf<Tree>()

    //Variables to define UI
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var endReached = mutableStateOf(false)

    //State used to refresh the list on connection changes
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    //Variable for search
    private var isSearchStarting = true
    private var isSearching = mutableStateOf(false)

    //Variable used for lazy loading, updated when the user to scroll to the bottom of the list
    private var index = -1
    private var deleteEventDisposable: Disposable? = null

    init {
        getTrees(false)

        deleteEventDisposable = RxEventHandler.publishEventObservable
            .ofType(TreeEvent::class.java)
            .subscribe { event ->
                if (event is TreeEvent.DeleteTree) {
                    deleteTree(event.tree)
                }
            }
    }

    fun getTrees(force: Boolean) {
        viewModelScope.launch {
            index += 1
            error.value = ""
            getTreesUseCase.invoke(Constants.NUMBER_OF_ROWS * index, getFetchStrategy(force))
                .collect {
                    when (it) {
                        is ApiResponse.Success -> {
                            endReached.value = index * Constants.NUMBER_OF_ROWS >= it.data!!.size
                            _state.value += (it.data as List<Tree>)
                            addTreeUseCase(it.data!!)
                        }
                        is ApiResponse.Loading -> isLoading.value = true
                        is ApiResponse.Error -> error.value = when (it.error) {
                            is ErrorEntity.Network -> "A network error has occurred. Check your internet connection"
                            is ErrorEntity.NotFound -> "API endpoint not found"
                            is ErrorEntity.Unauthorized -> "Unauthorized request"
                            is ErrorEntity.BadRequest -> "An error occurred while requesting the server"
                            is ErrorEntity.ServiceUnavailable -> "Service Unavailable. Check your internet connection"
                            else -> "An unexpected error has occurred. Please try again later"
                        }
                    }
                }
            isLoading.value = false
        }
    }

    fun deleteTree(tree: Tree) {
        viewModelScope.launch {
            deleteTreeUseCase(tree.id)
        }
        val listClone = _state.value as MutableList<Tree>
        listClone.removeAt(_state.value.indexOf(tree))
        _state.value = listClone
    }

    fun searchTree(query: String) {
        val listToSearch = if (isSearchStarting) {
            _state.value
        } else {
            savedTreeList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                _state.value = savedTreeList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.id.contains(query.trim(), ignoreCase = true) ||
                        it.espece.contains(query.trim(), ignoreCase = true)
            }
            if (isSearchStarting) {
                savedTreeList = _state.value
                isSearchStarting = false
            }
            _state.value = results
            isSearching.value = true
        }
    }

    fun forceRefresh() {
        index = -1
        val mutableClone = _state.value as MutableList<Tree>
        mutableClone.clear()
        _state.value = mutableClone
        getTrees(true)
    }
}
