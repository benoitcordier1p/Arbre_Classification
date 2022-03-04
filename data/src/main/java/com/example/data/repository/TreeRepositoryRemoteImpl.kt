package com.example.data.repository

import com.example.data.remote.TreeApi
import com.example.data.remote.models.Record
import javax.inject.Inject

class TreeRepositoryRemoteImpl @Inject constructor(
    private val api: TreeApi,
) : TreeRepositoryRemote {

    override suspend fun getTrees(): List<Record> {
        return api.getForceTrees("0").records
    }

    override suspend fun getTreesFromCache(position: String): List<Record> {
        return api.getTrees(position).records
    }

}