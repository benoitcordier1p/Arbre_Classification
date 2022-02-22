package com.example.domain.useCase.addTreeUseCase

import com.example.domain.entities.Trees
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class AddTreeUseCase @Inject constructor(
    private val repository: TreeRepository
) {

    operator fun invoke(tree: Trees) {
        try {
            GlobalScope.launch {
                repository.insertTrees(tree)
            }
        } catch (e : Exception){
            println("Error inserting tree")
        }
    }
}