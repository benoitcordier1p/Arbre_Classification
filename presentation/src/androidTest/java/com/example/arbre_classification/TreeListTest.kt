package com.example.arbre_classification

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arbre_classification.di.AppModule
import com.example.arbre_classification.presentation.treeInfo.components.treeScreen.TreeScreen
import com.example.arbre_classification.presentation.treesList.components.treesListScreen.TreesListScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@HiltAndroidTest
@UninstallModules(AppModule::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TreeListTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "TreesList"){
                composable(route = "TreesList"){
                    TreesListScreen(navController = navController)
                }
                composable(route = "TreesList/{treeId}"){
                    TreeScreen()
                }
            }
        }
    }

    @Test
    fun a_treeList_isDisplayed(){
        runBlocking {
            delay(5000L)
            composeRule.onNodeWithTag("Tree_Button_3628f6fbf88415e25dab87843fd2caa65bf7a0a6").assertIsDisplayed()
        }
    }

    //Problème sur ce test. Le performClick() ne fonctionne pas?
    /*
    @Test
    fun b_treeItem_isDisplayed(){
        runBlocking {
            delay(5000L)
            composeRule.onNodeWithTag("Tree_Button_3628f6fbf88415e25dab87843fd2caa65bf7a0a6").performClick()
            delay(5000L)
            composeRule.onNodeWithTag("Tree_Item_3628f6fbf88415e25dab87843fd2caa65bf7a0a6").assertIsDisplayed()
        }
    }
    */
}