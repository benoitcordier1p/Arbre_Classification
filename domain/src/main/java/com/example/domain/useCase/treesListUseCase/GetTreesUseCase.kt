package com.example.domain.useCase.treesListUseCase

import com.example.data.remote.errorHandler.ErrorHandlerImpl
import com.example.data.remote.models.TreesComplete
import com.example.data.repository.TreeRepositoryLocal
import com.example.data.repository.TreeRepositoryRemote
import com.example.domain.fetchstrategy.FetchStrategy
import com.example.domain.models.Tree
import com.example.domain.models.toDomain
import com.example.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class GetTreesUseCase @Inject constructor(
    private val repositoryLocal: TreeRepositoryLocal,
    private val repositoryRemote: TreeRepositoryRemote,
) {

    private val errorHandlerImpl = ErrorHandlerImpl()

    suspend operator fun invoke(
        start: Int,
        fetchStrategy: FetchStrategy
    ): Flow<ApiResponse<List<Tree>>> = flow {
        emit(ApiResponse.Loading<List<Tree>>())
        when (fetchStrategy) {
            FetchStrategy.Remote -> {
                val response = repositoryRemote.getTrees()
                emit(resourceHandler(response))
            }
            FetchStrategy.Local -> {
                val trees = repositoryLocal.getTreesRoom().map { it.toDomain() }
                emit(ApiResponse.Success<List<Tree>>(trees))
            }
            FetchStrategy.Cache -> {
                val response = repositoryRemote.getTreesFromCache(start.toString())
                emit(resourceHandler(response))
            }
        }

    }

    private fun resourceHandler(response: Response<TreesComplete>): ApiResponse<List<Tree>> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body!=null && body.toString().isNotBlank()) {
                ApiResponse.Success(body.records.map { it.toDomain() })
            } else {
                ApiResponse.Error<List<Tree>>(errorHandlerImpl.retroError(-1))
            }
        } else {
            ApiResponse.Error<List<Tree>>(errorHandlerImpl.retroError(response.code()))
        }
    }
}