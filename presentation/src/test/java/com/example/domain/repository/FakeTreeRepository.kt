package com.example.domain.repository

import com.example.domain.entities.Trees
import com.example.domain.models.Tree
import javax.inject.Inject


class FakeTreeRepository @Inject constructor(
) : com.example.domain.repository.TreeRepositoryLocal {
    override suspend fun getTreesRoom(): List<Tree> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTrees(tree: Trees) {
        TODO("Not yet implemented")
    }


}