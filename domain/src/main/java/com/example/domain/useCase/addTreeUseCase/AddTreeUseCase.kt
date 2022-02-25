package com.example.domain.useCase.addTreeUseCase

import com.example.domain.entities.Trees
import com.example.domain.repository.TreeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTreeUseCase @Inject constructor(
    private val repository: TreeRepository
) {

    operator fun invoke(tree: Trees) {
        try {
            GlobalScope.launch {
                repository.insertTreesRealm(tree)
            }
        } catch (e : Exception){
            println("Error inserting tree")
        }
    }
}