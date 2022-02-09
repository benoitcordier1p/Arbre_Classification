package com.example.arbre_classification.presentation.treeInfo

import com.example.arbre_classification.data.models.Tree

data class TreeState (
    var tree : Tree? = null,
    var isLoading : Boolean = false,
    var error : String = ""
)