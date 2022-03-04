package com.example.data.repository

import com.example.data.local.TreeDao
import com.example.data.local.Trees
import javax.inject.Inject

class TreeRepositoryLocalImpl @Inject constructor(
    private val dao: TreeDao
) : TreeRepositoryLocal {

    override suspend fun getTreesRoom(): List<Trees> {
        return dao.getTrees()
    }

    override suspend fun insertTrees(tree: Trees) {
        return dao.insertTree(tree)
    }

    override suspend fun deleteTree(tree_id: String) {
        dao.deleteTree(tree_id = tree_id)
    }

}