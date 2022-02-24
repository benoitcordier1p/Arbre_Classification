package com.example.domain.useCase.addTreeUseCase

import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepository
import io.realm.RealmConfiguration
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTreeUseCase @Inject constructor(
    private val repository: TreeRepository
) {

    operator fun invoke(tree: Tree) {
        try {
            GlobalScope.launch {
                repository.insertTreesRealm(tree)
            }
        } catch (e : Exception){
            println("Error inserting tree")
        }
    }
}