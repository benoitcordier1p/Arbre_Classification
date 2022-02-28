package com.example.data.repository

import com.example.data.remote.TreeApi
import com.example.data.remote.models.toDomain
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepositoryRemote
import javax.inject.Inject


class TreeRepositoryRemoteImpl @Inject constructor(
    private val api: TreeApi,
) : TreeRepositoryRemote {


    override suspend fun getTrees(): List<Tree> =
        api.getForceTrees("0").records.map {
            it.toDomain()
        }

    override suspend fun getTreesFromCache(position: String): List<Tree> =
        api.getTrees(position).records.map {
            it.toDomain()
        }
}