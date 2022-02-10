package com.example.arbre_classification.presentation.treesList.components.treesListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.arbre_classification.presentation.treeInfo.TreeViewModel
import com.example.arbre_classification.presentation.treesList.TreesListViewModel

@Composable
fun TreesListScreen(
    viewModel: TreesListViewModel = hiltViewModel(),
    navController: NavController
){

    val state = remember { viewModel.state }
    Scaffold (
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colors.background
                )
        ){
            items(state.value.trees){ tree ->
                Box(modifier = Modifier.padding(12.dp)){
                    TreeListItem(tree = tree,viewModel = viewModel,navController = navController)
                }

            }
        }

        if(state.value.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }

        }
        if(state.value.error.isEmpty()){
            Text(
                text = state.value.error,
                color = MaterialTheme.colors.error
            )
        }
    }
}