package com.example.data.repository

import com.example.data.remote.models.TreesComplete
import retrofit2.Response

interface TreeRepositoryRemote {

    suspend fun getTrees() : Response<TreesComplete>

    suspend fun getTreesFromCache(position:String) : Response<TreesComplete>
}