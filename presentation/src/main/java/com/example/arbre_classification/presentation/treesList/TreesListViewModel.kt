package com.example.arbre_classification.presentation.treesList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbre_classification.util.Constants
import com.example.domain.entities.Trees
import com.example.domain.models.Tree
import com.example.domain.useCase.addTreeUseCase.AddTreeUseCase
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase
import com.example.domain.useCase.treesListUseCase.GetTreesUseCaseFromDB
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreesListViewModel @Inject constructor(
    private val getTreesUseCase: GetTreesUseCase,
    private val addTreeUseCase : AddTreeUseCase,
    private val getTreesUseCaseFromDB: GetTreesUseCaseFromDB
) : ViewModel() {

    //State. Updated when new tress are loaded.
    private val _state = mutableStateOf<List<Tree>>(emptyList())
    var state: State<List<Tree>> = _state

    //Variables to define UI
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var lastTree = false

    //Variable used for lazy loading, updated when the user to scroll to the bottom of the list
    private var index = 0

    init {
        getTrees()
    }

    fun getTrees() {
        viewModelScope.launch {
            getTreesUseCase(index * Constants.NUMBER_OF_ROWS).collect {
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

            viewModelScope.launch {
                _state.value.forEach {
                    addTreeUseCase(Trees(it.id, it.espece, it.hauteurenm, it.circonferenceencm, it.adresse))
                }
            }
        }
    }

    fun getCacheTrees(){
        viewModelScope.launch {
            error.value=""
            getTreesUseCaseFromDB().collect {
                when (it) {
                    is Resource.Success -> _state.value = it.data as List<Tree>
                    is Resource.Loading -> isLoading.value = true
                    is Resource.Error -> error.value = it.message!!
                }
            }
            isLoading.value = false
        }
    }

    fun resetTrees(){
        _state.value = emptyList()
        index=0
        error.value = ""
        getTrees()

    }
}
