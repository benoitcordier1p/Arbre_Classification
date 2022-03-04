package com.example.domain.useCase.deleteTreeUseCase

import com.example.data.repository.TreeRepositoryLocal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeleteTreeUseCase @Inject constructor(
    private val repository: TreeRepositoryLocal
) {

    operator fun invoke(tree_id: String) {
        try {
            GlobalScope.launch {
                repository.deleteTree(tree_id)
            }
        } catch (e : Exception){
            println("Error deleting tree")
        }
    }
}