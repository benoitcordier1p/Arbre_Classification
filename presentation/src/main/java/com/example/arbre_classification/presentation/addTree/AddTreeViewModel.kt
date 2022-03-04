package com.example.arbre_classification.presentation.addTree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Tree
import com.example.domain.useCase.addTreeUseCase.AddTreeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTreeViewModel @Inject constructor(
    private val addTreeUseCase: AddTreeUseCase,
) : ViewModel() {

    fun addTree(tree: List<Tree>) {
        viewModelScope.launch {
            addTreeUseCase(tree)
        }
    }
}
