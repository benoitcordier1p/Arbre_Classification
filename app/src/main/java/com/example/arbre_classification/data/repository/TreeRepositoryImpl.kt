package com.example.arbre_classification.data.repository

import com.example.arbre_classification.data.models.Tree
import com.example.arbre_classification.data.models.Trees
import com.example.arbre_classification.data.remote.TreeApi
import com.example.arbre_classification.domain.TreeRepository
import javax.inject.Inject


class TreeRepositoryImpl @Inject constructor(
    private val api : TreeApi
) : TreeRepository{

    override suspend fun getTrees(): List<Tree> = api.getTrees().records

    override suspend fun getTreeByPosition(position: String): List<Tree> = api.getTreeByPosition(position = position).records

}