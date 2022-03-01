package com.example.domain.repository

import com.example.domain.models.Tree
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryRemote : TreeRepositoryRemote {

    private val trees = mutableListOf(
        Tree("1", "Rue de Clichy", 132, "Japonica", 32)
    )

    private val treesCache = mutableListOf(
        Tree("2", "Boulevard de Magenta", 13, "Japonica", 3)
    )

    override suspend fun getTrees(): Flow<Resource<List<Tree>>> {
        return flow {
            emit(Resource.Success<List<Tree>>(trees))
        }
    }

    override suspend fun getTreesFromCache(position: String): Flow<Resource<List<Tree>>> {
        return flow {
            emit(Resource.Success<List<Tree>>(treesCache))
        }
    }
}