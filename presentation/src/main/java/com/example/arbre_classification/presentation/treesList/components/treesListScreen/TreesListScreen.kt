package com.example.arbre_classification.presentation.treesList.components.treesListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.arbre_classification.presentation.treesList.TreesListViewModel
import com.example.arbre_classification.util.ConnectionManager
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

    val connection = ConnectionManager(LocalContext.current)
    val offline = remember { connection.state }
    if(offline.value) viewModel.getCacheTrees()
    else viewModel.resetTrees()


    Scaffold {
        Text(
            text = "You are currently offline",
            color = MaterialTheme.colors.error,
            modifier = Modifier.alpha(if(offline.value) 1f else 0f)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 20.dp)
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
            if (error.value.isNotEmpty() && !offline.value) {
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