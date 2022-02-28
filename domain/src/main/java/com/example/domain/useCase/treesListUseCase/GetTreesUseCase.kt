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

    operator fun invoke(start: Int, fetchStrategy: FetchStrategy): Flow<Resource<List<Tree>>> = flow {
        try {
            when (fetchStrategy) {
                FetchStrategy.Remote -> {
                    emit(Resource.Loading<List<Tree>>())
                    val trees = repositoryRemote.getTrees()
                    repositoryLocal.insertTrees(trees)
                    emit(Resource.Success<List<Tree>>(trees))
                }
                FetchStrategy.Local -> {
                    emit(Resource.Loading<List<Tree>>())
                    emit(Resource.Success<List<Tree>>(repositoryLocal.getTreesRoom()))
                }
                FetchStrategy.Cache -> {
                    emit(Resource.Loading<List<Tree>>())
                    emit(Resource.Success<List<Tree>>(repositoryRemote.getTreesFromCache(start.toString())))
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