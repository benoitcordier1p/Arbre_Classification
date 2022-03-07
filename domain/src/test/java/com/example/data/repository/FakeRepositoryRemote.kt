package com.example.data.repository

import com.example.data.remote.models.Record

class FakeRepositoryRemote : TreeRepositoryRemote {


    override suspend fun getTrees(): List<Record> {
        TODO("Not yet implemented")
    }

    override suspend fun getTreesFromCache(position: String): List<Record> {
        TODO("Not yet implemented")
    }
}