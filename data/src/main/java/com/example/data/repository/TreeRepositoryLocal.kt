package com.example.data.repository

import com.example.data.local.Trees

interface TreeRepositoryLocal {

    suspend fun getTreesRoom(): List<Trees>

    suspend fun insertTrees(tree: Trees)

    suspend fun deleteTree(tree_id: String)
}