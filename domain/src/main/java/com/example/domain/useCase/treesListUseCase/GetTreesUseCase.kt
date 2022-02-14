package com.example.domain.useCase.treesListUseCase

import com.example.data.models.Tree
import com.example.data.models.toTreeList
import com.example.domain.repository.TreeRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTreesUseCase @Inject constructor(
    private val repository: TreeRepository
) {

    operator fun invoke(start: Int) : Flow<Resource<List<Tree>>> = flow {
        try {
            emit(Resource.Loading<List<Tree>>())
            val trees = repository.getTrees(start.toString()).toTreeList()
            emit(Resource.Success<List<Tree>>(trees))
        } catch (e : HttpException){
            emit(Resource.Error<List<Tree>>(e.localizedMessage?:"An error occurred"))
        } catch (e : IOException){
            emit(Resource.Error<List<Tree>>(e.localizedMessage?:"Internet error. Check your connection"))
        }
    }
}