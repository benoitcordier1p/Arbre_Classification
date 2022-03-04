package com.example.data.repository

import com.example.data.remote.models.Record

interface TreeRepositoryRemote {

    suspend fun getTrees() : List<Record>

    suspend fun getTreesFromCache(position:String) : List<Record>
}