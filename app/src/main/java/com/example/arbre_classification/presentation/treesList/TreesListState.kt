package com.example.arbre_classification.presentation.treesList

import com.example.arbre_classification.domain.models.Tree

data class TreesListState (
    var trees : List<Tree> = emptyList(),
    var isLoading : Boolean = false,
    var error : String = ""
)