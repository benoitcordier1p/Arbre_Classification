package com.example.arbre_classification.presentation.treesList.components.treesListScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.arbre_classification.presentation.treesList.TreesListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Destination(start = true)
@Composable
fun TreesListScreen(
    viewModel: TreesListViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val state = remember { viewModel.state }
    val isLoading = remember { viewModel.isLoading }
    val error = remember { viewModel.error }
    //val lastTree = remember { viewModel.lastTree }
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val offline = remember { viewModel.offline }

    println(state.value.size)

    DisposableEffect(key1 = viewModel) {
        viewModel.onStart()
        onDispose { viewModel.onStop() }
    }

    Scaffold {
        Text(
            text = "You are currently offline",
            color = MaterialTheme.colors.error,
            modifier = Modifier.alpha(if (offline.value) 1f else 0f)
        )
        SwipeRefresh(
            state = SwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.forceRefresh() }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 20.dp)
                    .background(MaterialTheme.colors.background)
                    .testTag("Tree_List")
            ) {
                items(state.value.size) {
                    if (it >= state.value.size - 1 && !offline.value) {
                        LaunchedEffect(key1 = Unit, block = {
                            viewModel.getTrees(false)
                        })
                    }

                    val dismissState = rememberDismissState(
                        confirmStateChange = { itDismiss ->
                            if (itDismiss == DismissValue.DismissedToEnd) {
                                viewModel.deleteTree(it)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(),
                        background = {
                            val color by animateColorAsState(
                                targetValue = when (dismissState.targetValue) {
                                    DismissValue.Default -> MaterialTheme.colors.background
                                    DismissValue.DismissedToEnd -> MaterialTheme.colors.primary
                                    DismissValue.DismissedToStart -> MaterialTheme.colors.primary
                                }
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = color)
                            )
                        },
                        dismissContent = {
                            Box(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .testTag("Tree_Item_$it")
                            ) {
                                TreeListItem(tree = state.value[it], navigator = navigator)
                            }
                        },

                        )
                }
            }
        }
        LoadingErrorScreen(
            isLoading = isLoading.value,
            offline = offline.value,
            error = error.value
        )
    }
}

@Composable
fun LoadingErrorScreen(isLoading: Boolean, offline: Boolean, error: String) {
    Column {
        if (isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        if (error.isNotEmpty() && offline) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = error,
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}

