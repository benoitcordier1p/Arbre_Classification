package com.example.data.repository

import com.example.data.local.TreeDao
import com.example.domain.entities.toDomain
import com.example.data.remote.models.toDomain
import com.example.data.remote.TreeApi
import com.example.domain.entities.Trees
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepositoryLocal
import javax.inject.Inject
import javax.inject.Named


class TreeRepositoryLocalImpl @Inject constructor(
    private val dao: TreeDao
) : TreeRepositoryLocal {

    override suspend fun getTreesRoom(): List<Tree> = dao.getTrees().map {
        it.toDomain()
    }

    override suspend fun insertTrees(tree: List<Tree>) {
        //dao.nukeTable()
        tree.forEach {
            dao.insertTree(
                Trees(
                    it.id,
                    it.espece,
                    it.hauteurenm,
                    it.circonferenceencm,
                    it.adresse
                )
            )
        }

    }

}