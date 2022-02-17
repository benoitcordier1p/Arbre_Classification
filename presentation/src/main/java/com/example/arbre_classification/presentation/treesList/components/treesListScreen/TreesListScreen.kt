package com.example.arbre_classification.presentation.treesList.components.treesListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.arbre_classification.presentation.destinations.AddTreeDestination
import com.example.arbre_classification.presentation.treesList.TreesListViewModel
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

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navigator.navigate(AddTreeDestination())
                },
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            items(state.value.size) {
                if (it >= state.value.size - 1 && !lastTree) {
                    viewModel.getTrees()
                }
                Box(modifier = Modifier.padding(12.dp)) {
                    TreeListItem(tree = state.value[it], navigator = navigator)
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
            if (error.value.isNotEmpty()) {
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