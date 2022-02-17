package com.example.arbre_classification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.arbre_classification.presentation.NavGraphs
import com.example.arbre_classification.presentation.ui.theme.Arbre_ClassificationTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Arbre_ClassificationTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

