package com.example.domain.repository

import javax.inject.Inject


class FakeTreeRepository @Inject constructor(
) : com.example.domain.repository.TreeRepository {

    override suspend fun getTrees(): List<Tree> {
        return listOf(
            Tree(fields = Fields("1 Boulevard de Clichy","17",1,"hispanica",1,"nom"),recordid = "1"),
            Tree(fields = Fields("2 Boulevard de Clichy","17",1,"hispanica",1,"nom"),recordid = "2"),
            Tree(fields = Fields("3 Boulevard de Clichy","17",1,"hispanica",1,"nom"),recordid = "3")
        )
    }

    override suspend fun getTreeByPosition(position: String): List<Tree> {
        TODO("Not yet implemented")
    }

}