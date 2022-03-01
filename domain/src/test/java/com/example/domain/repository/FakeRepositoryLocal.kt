package com.example.domain.repository

import com.example.domain.models.Tree
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryLocal : TreeRepositoryLocal {

    private val treesRoom = mutableListOf(
        Tree("3", "Place du Mexique", 1, "Hysteira", 1342)
    )

    override suspend fun getTreesRoom(): Flow<Resource<List<Tree>>> {
        return flow {
            emit(Resource.Success<List<Tree>>(treesRoom))
        }
    }

    override suspend fun insertTrees(tree: List<Tree>) {
        TODO("Not yet implemented")
    }
}