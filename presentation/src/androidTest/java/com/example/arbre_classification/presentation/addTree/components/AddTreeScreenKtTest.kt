package com.example.arbre_classification.presentation.addTree.components

import androidx.compose.material.Scaffold
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.example.arbre_classification.BottomNavigationBar
import com.example.arbre_classification.MainActivity
import com.example.arbre_classification.presentation.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AddTreeScreenKtTest{

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

    /*
    @Test
    fun a_Input_Tree() {
        runBlocking {
            composeRule.onNodeWithTag("Button_Add").performClick()
            composeRule.onNodeWithTag("input_id").performTextInput("1")
            composeRule.onNodeWithTag("input_espece").performTextInput("Japonica")
            composeRule.onNodeWithTag("input_hauteur").performTextInput("1")
            composeRule.onNodeWithTag("input_circonference").performTextInput("1")
            composeRule.onNodeWithTag("input_adresse").performTextInput("Boulevard de rien")
        }
    }
    */
}