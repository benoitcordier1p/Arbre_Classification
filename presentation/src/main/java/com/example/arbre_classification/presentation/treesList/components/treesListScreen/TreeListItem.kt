package com.example.arbre_classification.presentation.treesList.components.treesListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.models.Tree
import com.google.gson.Gson

@Composable
fun TreeListItem(
    tree : Tree,
    navController: NavController
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
        Column(modifier = Modifier
            .padding(6.dp)
            .background(
                Color.White
            )
        ){
            Text(
                text = "Arbre n° ${tree.id}",
                style = MaterialTheme.typography.button,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Espèce : ${tree.espece}",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {

                val treeJson = Gson().toJson(tree).replace("/",",")
                navController.navigate(
                    route = "TreesList/$treeJson"
                )

            }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .testTag("Tree_Button_${tree.id}")
                .background(MaterialTheme.colors.primary)
            ) {
               Text(
                   text = "Cliquez ici pour plus d'infos",
                   color = Color.White)
            }
        }

    }
}