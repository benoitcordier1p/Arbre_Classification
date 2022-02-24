package com.example.data.repository

import com.example.data.local.TreeDatabaseOperations
import com.example.data.local.toDomain
import com.example.data.remote.TreeApi
import com.example.data.remote.models.toDomain
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepository
import io.realm.RealmConfiguration
import javax.inject.Inject


class TreeRepositoryImpl @Inject constructor(
    private val api: TreeApi,
    private val dao : TreeDatabaseOperations
) : TreeRepository {

    override suspend fun insertTreesRealm(tree: Tree) = dao.insertTree(tree)

    override suspend fun getTreesRealm(): List<Tree> = dao.getTrees().map {
        it.toDomain()
    }

    override suspend fun getTrees(position: String): List<Tree> = api.getTrees(position).records.map {
        it.toDomain()
    }

}