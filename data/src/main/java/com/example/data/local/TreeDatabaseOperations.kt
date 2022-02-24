package com.example.data.local

import android.content.Context
import com.example.domain.models.Tree
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TreeDatabaseOperations @Inject constructor(
    private val context: Context
) {
    suspend fun insertTree(tree: Tree) {

        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .schemaVersion(1L)
            .build()

        val realm = Realm.getInstance(config)

        realm.executeTransaction { realmTransaction ->

            val treeInsert = Trees(
                id = tree.id,
                espece = tree.espece,
                hauteur = tree.hauteurenm,
                circonference = tree.circonferenceencm,
                adresse = tree.adresse)
            realmTransaction.insertOrUpdate(treeInsert)
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