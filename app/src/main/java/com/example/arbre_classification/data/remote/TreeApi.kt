package com.example.arbre_classification.data.remote

import com.example.arbre_classification.data.models.Trees
import retrofit2.http.GET
import retrofit2.http.Query


interface TreeApi {

    @GET("/api/records/1.0/search/?dataset=les-arbres&q=&rows=20&facet=domanialite&facet=arrondissement&facet=libellefrancais&facet=espece&facet=circonferenceencm&facet=hauteurenm")
    suspend fun getTrees() : Trees

    @GET("/api/records/1.0/search/")
    suspend fun getTreeByPosition(
        @Query("dataset") dataset :String = "les-arbres",
        @Query("rows") rows:String="1",
        @Query("start") position : String,
    ) : Trees
}