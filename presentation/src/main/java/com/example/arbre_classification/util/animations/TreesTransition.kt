package com.example.arbre_classification.util.animations

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavBackStackEntry
import com.example.arbre_classification.presentation.destinations.TreesListScreenDestination
import com.example.arbre_classification.presentation.navDestination
import com.ramcosta.composedestinations.spec.DestinationStyle


@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
object TreesTransitions : DestinationStyle.Animated {
    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
        return when (initialState.navDestination) {
            TreesListScreenDestination -> {
                slideInHorizontally(
                    initialOffsetX = { 500 },
                    animationSpec = tween(300)
                )
            }
            else -> null
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition? {
        return when (initialState.navDestination) {
            TreesListScreenDestination ->
                slideInHorizontally(
                    initialOffsetX = { -700 },
                    animationSpec = tween(500)
                )
            else -> null
        }
    }

}