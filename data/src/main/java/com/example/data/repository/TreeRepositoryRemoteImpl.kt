package com.example.data.repository

import com.example.data.remote.TreeApi
import com.example.data.remote.models.TreesComplete
import retrofit2.Response
import javax.inject.Inject

class TreeRepositoryRemoteImpl @Inject constructor(
    private val api: TreeApi,
) : TreeRepositoryRemote {

    override suspend fun getTrees(): Response<TreesComplete> {
        return api.getForceTrees("0")
    }

    override suspend fun getTreesFromCache(position: String): Response<TreesComplete> {
        return api.getTrees(position)
    }
}