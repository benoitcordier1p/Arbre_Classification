package com.example.arbre_classification.presentation.treesList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbre_classification.domain.useCase.treesListUseCase.GetTreesUseCase
import com.example.arbre_classification.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreesListViewModel @Inject constructor(
    private val getTreesUseCase: GetTreesUseCase
) : ViewModel(){

    private val _state = mutableStateOf(TreesListState())
    var state : State<TreesListState> = _state

    init{
        getTrees()
    }

    private fun getTrees(){
        viewModelScope.launch {
            getTreesUseCase().collect {
                when(it){
                    is Resource.Success ->{
                        _state.value= it.data?.let { it1 -> TreesListState((it1)) }!!
                    }
                    is Resource.Loading ->{
                        _state.value= TreesListState(isLoading = true)
                    }
                    is Resource.Error ->{
                        _state.value= it.message?.let { it1 -> TreesListState(error = it1) }!!
                    }
                }
            }
        }
    }
}