package com.example.domain.repository

import com.example.data.models.TreesComplete
import com.example.data.remote.TreeApi
import javax.inject.Inject


class TreeRepositoryImpl @Inject constructor(
    private val api : TreeApi
) : TreeRepository {

    override suspend fun getTrees(start: String): TreesComplete = api.getTrees(start)

    //override suspend fun getTreeByPosition(position: String): List<Tree> = api.getTreeByPosition(position = position).records

}