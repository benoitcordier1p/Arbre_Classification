package com.example.data.repository

import com.example.data.models.toDomain
import com.example.data.remote.TreeApi
import com.example.domain.models.Tree
import com.example.domain.repository.TreeRepository
import javax.inject.Inject


class TreeRepositoryImpl @Inject constructor(
    private val api: TreeApi
) : TreeRepository {

    override suspend fun getTrees(start: String): List<Tree> = api.getTrees(start).records.map { it.toDomain() }

}