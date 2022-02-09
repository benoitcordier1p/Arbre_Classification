package com.example.arbre_classification.domain.use_case.getTree

import com.example.arbre_classification.data.models.Tree
import com.example.arbre_classification.data.repository.TreeRepository
import com.example.arbre_classification.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTreeUseCase @Inject constructor(
    private val repository: TreeRepository
) {

    operator fun invoke(position : String) : Flow<Resource<List<Tree>>> = flow {
        try {
            emit(Resource.Loading<List<Tree>>())
            val trees = repository.getTreeByPosition(position)
            emit(Resource.Success<List<Tree>>(trees))
        } catch (e : HttpException){
            emit(Resource.Error<List<Tree>>(e.localizedMessage?:"An error occurred"))
        } catch (e : IOException){
            emit(Resource.Error<List<Tree>>(e.localizedMessage?:"Internet error. Check your connection"))
        }
    }
}