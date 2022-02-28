package com.example.domain.useCase.addTreeUseCase

import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepositoryLocal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTreeUseCase @Inject constructor(
    private val repository: TreeRepositoryLocal
) {

    operator fun invoke(tree: List<Tree>) {
        try {
            GlobalScope.launch {
                repository.insertTrees(tree)
            }
        } catch (e : Exception){
            println("Error inserting tree")
        }
    }
}