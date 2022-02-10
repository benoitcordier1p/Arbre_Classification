package com.example.arbre_classification.presentation.treesList.components.treesListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arbre_classification.data.models.Tree
import com.example.arbre_classification.presentation.treesList.TreesListViewModel

@Composable
fun TreeListItem(
    tree : Tree,
    navController: NavController,
    viewModel: TreesListViewModel
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(12.dp)
            ),
        elevation = 8.dp

    ){
        Column(modifier = Modifier.padding(6.dp)){
            Text(
                text = "Arbre n° ${tree.recordid}",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Espèce : ${tree.fields.espece}",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {
                navController.navigate(
                    route = "TreesList/${viewModel.getTreePosition(tree.recordid)}"
                )
            }, modifier = Modifier
                .testTag("Tree_Button_${tree.recordid}")
                .background(MaterialTheme.colors.primary)
            ) {
               Text(
                   text = "Cliquez ici pour plus d'infos")
            }
        }

    }
}