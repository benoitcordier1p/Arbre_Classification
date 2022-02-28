package com.example.arbre_classification.presentation.treesList.components.treesListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.arbre_classification.presentation.treesList.TreesListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun TreesListScreen(
    viewModel: TreesListViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val state = remember { viewModel.state }
    val isLoading = remember { viewModel.isLoading }
    val error = remember { viewModel.error }
    val lastTree = remember { viewModel.lastTree }
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val offline = remember { viewModel.offline }

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
            onRefresh = { viewModel.getTrees(true) }
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 20.dp)
                    .background(MaterialTheme.colors.background)
            ) {
                items(state.value.size) {
                    if (it >= state.value.size - 1 && !offline.value && !lastTree) {
                        viewModel.getTrees(false)
                    }
                    Box(modifier = Modifier.padding(12.dp)) {
                        TreeListItem(tree = state.value[it], navigator = navigator)
                    }
                }
            }

        }
        Column {
            if (isLoading.value) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            if (error.value.isNotEmpty() && offline.value) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = error.value,
                        color = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}

