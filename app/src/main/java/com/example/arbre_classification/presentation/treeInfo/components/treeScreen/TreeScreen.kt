package com.example.arbre_classification.presentation.treeInfo.components.treeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.arbre_classification.presentation.treeInfo.TreeViewModel
import com.example.arbre_classification.presentation.treesList.TreesListViewModel
import com.example.arbre_classification.presentation.treesList.components.treesListScreen.TreeListItem

@Composable
fun TreeScreen(
    viewModel: TreeViewModel = hiltViewModel()
){

    val state = remember { viewModel.state }

    if(state.value.isLoading) {
        CircularProgressIndicator()
    }
    if(state.value.error.isEmpty()){
        Text(
            text = state.value.error,
            color = MaterialTheme.colors.error
        )
    }
}