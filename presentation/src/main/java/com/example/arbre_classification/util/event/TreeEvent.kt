package com.example.arbre_classification.util.event

import com.example.domain.models.Tree

sealed class TreeEvent : EventInt {
    class DeleteTree(val tree: Tree) : TreeEvent()
}