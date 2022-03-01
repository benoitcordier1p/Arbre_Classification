package com.example.data.repository

import com.example.data.local.TreeDao
import com.example.domain.entities.Trees
import com.example.domain.entities.toDomain
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepositoryLocal
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class TreeRepositoryLocalImpl @Inject constructor(
    private val dao: TreeDao
) : TreeRepositoryLocal {

    override suspend fun getTreesRoom(): Flow<Resource<List<Tree>>> = flow{
        try {
            emit(Resource.Loading<List<Tree>>())
            val trees = dao.getTrees().map {
                it.toDomain()
            }
            emit(Resource.Success<List<Tree>>(trees))
        } catch (e: Exception) {
            emit(Resource.Error<List<Tree>>(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Tree>>(e.localizedMessage ?: "Internet error. Check your connection")
            )
        }

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