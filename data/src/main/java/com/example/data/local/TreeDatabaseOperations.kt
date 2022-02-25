package com.example.data.local

import android.content.Context
import com.example.domain.entities.Trees
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class TreeDatabaseOperations @Inject constructor(
    private val context: Context
) {
    suspend fun insertTree(tree: Trees) {

        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .schemaVersion(1L)
            .build()

        val realm = Realm.getInstance(config)

        realm.executeTransaction { realmTransaction ->
            realmTransaction.insertOrUpdate(tree)
        }

    }

    suspend fun getTrees(): List<Trees> {

        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .schemaVersion(1L)
            .allowQueriesOnUiThread(true)
            .build()

        val realm = Realm.getInstance(config)
        val trees = mutableListOf<Trees>()

        realm.executeTransaction { realmTransaction ->
            trees.addAll(realmTransaction
                .where(Trees::class.java)
                .findAll()
            )
        }
        return trees
    }
}