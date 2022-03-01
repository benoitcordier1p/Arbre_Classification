package com.example.domain.useCase.treesListUseCase

import com.example.domain.fetchstrategy.FetchStrategy
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepositoryLocal
import com.example.domain.repository.TreeRepositoryRemote
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetTreesUseCase @Inject constructor(
    private val repositoryLocal: TreeRepositoryLocal,
    private val repositoryRemote: TreeRepositoryRemote
) {

    suspend operator fun invoke(start: Int, fetchStrategy: FetchStrategy): Flow<Resource<List<Tree>>> {
        return when (fetchStrategy) {
            FetchStrategy.Remote -> repositoryRemote.getTrees()
            FetchStrategy.Local -> repositoryLocal.getTreesRoom()
            FetchStrategy.Cache -> repositoryRemote.getTreesFromCache(start.toString())
        }
    }
}