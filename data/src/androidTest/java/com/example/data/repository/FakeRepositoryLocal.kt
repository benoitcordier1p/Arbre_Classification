package com.example.data.repository

import com.example.data.local.Trees

class FakeRepositoryLocal : TreeRepositoryLocal {

    private val treesRoom = mutableListOf(
        Trees("3","Japonica",3,1233,"Boulevard")
    )

    override suspend fun getTreesRoom(): List<Trees> {
       return treesRoom
    }

    override suspend fun insertTrees(tree: Trees) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTree(tree_id: String) {
        TODO("Not yet implemented")
    }
}