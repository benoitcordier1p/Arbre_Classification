package com.example.domain.useCase.addTreeUseCase

import com.example.data.repository.TreeRepositoryLocal
import com.example.domain.models.Tree
import com.example.domain.models.toEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTreeUseCase @Inject constructor(
    private val repository: TreeRepositoryLocal
) {

    operator fun invoke(tree: List<Tree>) {
        try {
            GlobalScope.launch {
                tree.forEach {
                    repository.insertTrees(it.toEntity())
                }
            }
        } catch (e : Exception){
            println("Error inserting tree")
        }
    }
}