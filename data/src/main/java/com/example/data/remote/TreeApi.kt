package com.example.data.remote

import com.example.data.models.TreesComplete
import retrofit2.http.GET
import retrofit2.http.Query


interface TreeApi {

    @GET("/api/records/1.0/search/?dataset=les-arbres&q=&facet=domanialite&facet=arrondissement&facet=libellefrancais&facet=espece&facet=circonferenceencm&facet=hauteurenm")
    suspend fun getTrees(@Query("start") start: String): TreesComplete
}