package com.example.arbre_classification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arbre_classification.domain.models.Tree
import com.example.arbre_classification.presentation.treeInfo.components.treeScreen.TreeScreen
import com.example.arbre_classification.presentation.treesList.components.treesListScreen.TreesListScreen
import com.example.arbre_classification.presentation.ui.theme.Arbre_ClassificationTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Arbre_ClassificationTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "TreesList"){

                    composable(route = "TreesList"){
                        TreesListScreen(navController = navController)
                    }

                    composable(route = "TreesList/{tree}"){ backStackEntry ->
                        val treeJson =  backStackEntry.arguments?.getString("tree")
                        val treeObject = Gson().fromJson(treeJson, Tree::class.java)
                        TreeScreen(tree = treeObject!!)
                    }

                    composable(route = "AddTree"){

                    }
                }
            }
        }
    }
}

