package com.example.domain.useCase.treesListUseCase

import com.example.domain.fetchstrategy.FetchStrategy
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetTreesUseCase @Inject constructor(
    private val repository: TreeRepository
) {

    operator fun invoke(start: Int, fetchStrategy: FetchStrategy): Flow<Resource<List<Tree>>> = flow {
        try {
            when (fetchStrategy) {
                FetchStrategy.Remote -> {
                    emit(Resource.Loading<List<Tree>>())
                    emit(Resource.Success<List<Tree>>(repository.getTrees(start.toString())))
                }
                FetchStrategy.Local -> {
                    emit(Resource.Loading<List<Tree>>())
                    emit(Resource.Success<List<Tree>>(repository.getTreesRoom()))
                }
                FetchStrategy.Cache -> {

                }
            }
        } catch (e: Exception) {
            emit(Resource.Error<List<Tree>>(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Tree>>(e.localizedMessage ?: "Internet error. Check your connection")
            )
        }
    }
}