package com.example.domain.useCase.treesListUseCase

import com.example.data.remote.errorHandler.ErrorHandlerImpl
import com.example.data.repository.TreeRepositoryLocal
import com.example.data.repository.TreeRepositoryRemote
import com.example.domain.fetchstrategy.FetchStrategy
import com.example.domain.models.Tree
import com.example.domain.models.toDomain
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTreesUseCase @Inject constructor(
    private val repositoryLocal: TreeRepositoryLocal,
    private val repositoryRemote: TreeRepositoryRemote,
) {

    private val errorHandlerImpl= ErrorHandlerImpl()

    suspend operator fun invoke(
        start: Int,
        fetchStrategy: FetchStrategy
    ): Flow<Resource<List<Tree>>> = flow {
        try {
            emit(Resource.Loading<List<Tree>>())
            val trees = when (fetchStrategy) {
                FetchStrategy.Remote -> repositoryRemote.getTrees().map { it.toDomain() }
                FetchStrategy.Local -> repositoryLocal.getTreesRoom().map { it.toDomain() }
                FetchStrategy.Cache -> repositoryRemote.getTreesFromCache(start.toString())
                    .map { it.toDomain() }
            }
            emit(Resource.Success<List<Tree>>(trees))
        } catch (e: Throwable) {
            emit(Resource.Error<List<Tree>>(errorHandlerImpl(e)))
        }
    }
}