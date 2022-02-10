package com.example.arbre_classification.presentation.treeInfo.components.treeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.arbre_classification.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.arbre_classification.data.models.Fields
import com.example.arbre_classification.data.models.Tree
import com.example.arbre_classification.presentation.treeInfo.TreeViewModel
import com.example.arbre_classification.presentation.ui.theme.Arbre_ClassificationTheme

@Composable
fun TreeScreen(
    viewModel: TreeViewModel = hiltViewModel()
){

    val state = viewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.verticalGradient(
                colors = listOf(MaterialTheme.colors.background,
                    Color(0xFFFFFFFF)
                )
            )
        )
    ){
        state.tree?.let {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Column(Modifier.padding(10.dp)){
                        TreeDescription(tree = it)
                    }
                }
            }
        }
    }
    if(state.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
    if(state.error.isEmpty()){
        Text(
            text = state.error,
            color = MaterialTheme.colors.error
        )
    }
}

@Composable
fun TreeDescription(
    tree: Tree
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.tree),
            contentDescription = "Image of the tree",
            modifier = Modifier.size(150.dp)
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
    Text(
        text = "Arbre n° ${tree.recordid}",
        style = MaterialTheme.typography.button,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(5.dp)
            .testTag("Tree_Item_${tree.recordid}"),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.size(10.dp))
    Text(
        text = "Espèce : ${tree.fields.espece}",
        style = MaterialTheme.typography.h5
    )
    Spacer(modifier = Modifier.size(10.dp))
    Row{
        Icon(painter = painterResource(id = R.drawable.ic_height), contentDescription = null, tint = MaterialTheme.colors.onSurface)
        Text(
            text = "Hauteur : ${tree.fields.hauteurenm}m",
            style = MaterialTheme.typography.h5
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
    Row{
        Icon(
            painter = painterResource(id = R.drawable.circ),
            contentDescription = "circonferenceIcon",
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = "Circonférence : ${tree.fields.circonferenceencm}cm",
            style = MaterialTheme.typography.h5
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
    Text(
        text = "Adresse : ${tree.fields.adresse},${tree.fields.arrondissement}",
        style = MaterialTheme.typography.h5
    )

}

@Composable
@Preview
fun previewTreeDescription(){
    Arbre_ClassificationTheme {
        Column{
            TreeDescription(tree = Tree(
                Fields(
                    "1 Boulevard de Clichy",
                    "18ème Arrondissement",
                    123,
                    "hispanica",
                    2,
                    "Arbre"),
                "1234567890a")
            )
        }

    }
}