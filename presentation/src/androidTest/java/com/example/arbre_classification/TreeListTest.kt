package com.example.arbre_classification

import androidx.compose.material.Scaffold
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.rememberNavController
import com.example.arbre_classification.presentation.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@HiltAndroidTest
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
            val navHostEngine = rememberNavHostEngine()
            Scaffold(
                bottomBar = { BottomNavigationBar(navController = navController) }
            ) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController,
                    engine = navHostEngine
                )
            }
        }
    }

    @Test
    fun a_treeList_isDisplayed() {
        runBlocking {
            composeRule.onNodeWithTag("Tree_List").assertIsDisplayed()
        }
    }

    @Test
    fun b_Trees_Are_Displayed(){
        runBlocking {
            composeRule.onNodeWithTag("Tree_Item_0").assertIsDisplayed()
        }
    }

    @Test
    fun c_Check_Number_Of_Trees(){
        runBlocking {
            composeRule.onNodeWithTag("Tree_List").performScrollToIndex(19)
            composeRule.onNodeWithTag("Tree_Item_19").assertIsDisplayed()
        }
    }
}