package com.example.domain.useCase.treesListUseCase

import com.example.domain.entities.Trees
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class GetTreesUseCaseFromDB @Inject constructor(
    private val repository: TreeRepository
) {

    operator fun invoke(): Flow<Resource<List<Tree>>> = flow {
        try {
            emit(Resource.Loading<List<Tree>>())
            emit(Resource.Success<List<Tree>>(repository.getTreesRoom()))
        } catch (e: Exception) {
            emit(Resource.Error<List<Tree>>(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Tree>>(
                    e.localizedMessage ?: "Internet error. Check your connection"
                )
            )
        }
    }
}