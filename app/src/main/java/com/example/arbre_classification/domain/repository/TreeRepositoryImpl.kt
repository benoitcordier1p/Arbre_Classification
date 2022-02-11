package com.example.arbre_classification.domain.repository

import com.example.arbre_classification.data.models.TreesComplete
import com.example.arbre_classification.data.remote.TreeApi
import javax.inject.Inject


class TreeRepositoryImpl @Inject constructor(
    private val api : TreeApi
) : TreeRepository {

    override suspend fun getTrees(start: String): TreesComplete = api.getTrees(start)

    //override suspend fun getTreeByPosition(position: String): List<Tree> = api.getTreeByPosition(position = position).records

}