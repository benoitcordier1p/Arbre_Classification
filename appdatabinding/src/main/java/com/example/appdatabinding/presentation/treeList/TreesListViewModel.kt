package com.example.appdatabinding.presentation.treeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Tree
import com.example.domain.models.mock
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase
import com.example.domain.util.Resource
import com.example.appdatabinding.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreesListViewModel @Inject constructor(
    private val getTreesUseCase: GetTreesUseCase
) : ViewModel() {

    //State. Updated when new tress are loaded.
    private val _state = MutableLiveData(emptyList<Tree>())
    var state: LiveData<List<Tree>> = _state

    //Variables to define UI
    var isLoading = MutableLiveData(false)
    var error = MutableLiveData("")
    var lastTree = MutableLiveData(false)

    //Variable used for lazy loading, updated when the user to scroll to the bottom of the list
    private var index = 0
    var select: Tree = mock()

    init {
        getTrees()

    }


    fun getTrees() {
        viewModelScope.launch {
            error.value=""
            getTreesUseCase(index * Constants.NUMBER_OF_ROWS).collect {
                when (it) {
                    is Resource.Success -> {
                        _state.value = _state.value?.plus(it.data as List<Tree>)
                        lastTree.value = Constants.NUMBER_OF_ROWS * index > _state.value!!.size
                        index += 1
                    }
                    is Resource.Loading -> isLoading.value = true
                    is Resource.Error -> error.value = it.message!!
                }
            }
            isLoading.value = false
        }
    }

    fun itemSelection(tree: Tree) {
        select = tree
    }
}