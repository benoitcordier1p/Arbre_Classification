package com.example.data.repository

import com.example.data.remote.TreeApi
import com.example.data.remote.models.toDomain
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepositoryRemote
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class TreeRepositoryRemoteImpl @Inject constructor(
    private val api: TreeApi,
) : TreeRepositoryRemote {

    override suspend fun getTrees(): Flow<Resource<List<Tree>>> = flow {
        try {
            emit(Resource.Loading<List<Tree>>())
            val trees = api.getForceTrees("0").records.map {
                it.toDomain()
            }
            emit(Resource.Success<List<Tree>>(trees))
        } catch (e: Exception) {
            emit(Resource.Error<List<Tree>>(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Tree>>(e.localizedMessage ?: "Internet error. Check your connection")
            )
        }
    }

    override suspend fun getTreesFromCache(position: String): Flow<Resource<List<Tree>>> = flow {
        try {
            emit(Resource.Loading<List<Tree>>())
            val trees = api.getTrees(position).records.map {
                it.toDomain()
            }
            emit(Resource.Success<List<Tree>>(trees))
        } catch (e: Exception) {
            emit(Resource.Error<List<Tree>>(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Tree>>(e.localizedMessage ?: "Internet error. Check your connection")
            )
        }
    }

}