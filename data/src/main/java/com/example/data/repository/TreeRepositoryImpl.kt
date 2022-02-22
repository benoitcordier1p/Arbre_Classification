package com.example.data.repository

import com.example.data.local.TreeDao
import com.example.domain.entities.toDomain
import com.example.data.remote.models.toDomain
import com.example.data.remote.TreeApi
import com.example.domain.entities.Trees
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepository
import javax.inject.Inject


class TreeRepositoryImpl @Inject constructor(
    private val dao : TreeDao,
    private val api: TreeApi
) : TreeRepository {

    override suspend fun getTreesRoom(): List<Tree> = dao.getTrees().map {
        it.toDomain()
    }

    override suspend fun getTrees(position: String): List<Tree> = api.getTrees(position).records.map {
        it.toDomain()
    }

    override suspend fun insertTrees(tree: Trees) {
        dao.insertTree(tree)
    }

}