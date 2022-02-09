package com.example.arbre_classification.presentation.treeInfo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbre_classification.domain.use_case.getTree.GetTreeUseCase
import com.example.arbre_classification.util.Constants
import com.example.arbre_classification.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeViewModel @Inject constructor(
    private val getTreeUseCase: GetTreeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(TreeState())
    val state : State<TreeState> = _state

    init{
        savedStateHandle.get<String>(Constants.POSITION)?.let { coinId ->
            getTree(coinId)
        }
    }

    private fun getTree(position:String){
        viewModelScope.launch {
            getTreeUseCase(position).collect {
                when(it){
                    is Resource.Success ->{
                        _state.value= it.data?.let { it1 -> TreeState(it1[0]) }!!
                    }
                    is Resource.Loading ->{
                        _state.value= TreeState(isLoading = true)
                    }
                    is Resource.Error ->{
                        _state.value= it.message?.let { it1 -> TreeState(error = it1) }!!
                    }
                }
            }
        }
    }
}