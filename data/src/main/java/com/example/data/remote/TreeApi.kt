package com.example.data.remote

import com.example.data.remote.models.TreesComplete
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val CACHE_CONTROL_HEADER = "Cache-Control"
const val CACHE_CONTROL_NO_CACHE = "no-cache"
const val URL = "/api/records/1.0/search/?dataset=les-arbres&q=&rows=20&facet=domanialite&facet=arrondissement&facet=libellefrancais&facet=espece&facet=circonferenceencm&facet=hauteurenm"
interface TreeApi {

    @GET(URL)
    suspend fun getTrees(@Query("start") start: String): TreesComplete

    @GET(URL)
    @Headers("$CACHE_CONTROL_HEADER: $CACHE_CONTROL_NO_CACHE")
    suspend fun getForceTrees(@Query("start") start: String): TreesComplete
}