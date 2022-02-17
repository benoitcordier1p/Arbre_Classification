package com.example.arbre_classification.presentation.treeInfo.components.treeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.arbre_classification.R
import com.example.arbre_classification.presentation.ui.theme.Arbre_ClassificationTheme
import com.example.domain.models.Tree
import com.example.domain.models.mock
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun TreeScreen(tree: Tree) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.background,
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) {
        tree.let {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Column(Modifier.padding(10.dp)) {
                        TreeDescription(tree = it)
                    }
                }
            }
        }
    }
}

@Composable
fun TreeDescription(
    tree: Tree
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.tree),
            contentDescription = "Image of the tree",
            modifier = Modifier.size(150.dp)
        )
    }

    Spacer(modifier = Modifier.size(10.dp))

    Text(
        text = "Arbre n° ${tree.id}",
        style = MaterialTheme.typography.button,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(5.dp)
            .testTag("Tree_Item_${tree.id}"),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.size(10.dp))

    Text(
        text = "Espèce : ${tree.espece}",
        style = MaterialTheme.typography.h5
    )

    Spacer(modifier = Modifier.size(10.dp))

    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_height),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface
        )
        Text(
            text = "Hauteur : ${tree.hauteurenm}m",
            style = MaterialTheme.typography.h5
        )
    }

    Spacer(modifier = Modifier.size(10.dp))

    Row {
        Icon(
            painter = painterResource(id = R.drawable.circ),
            contentDescription = "circonferenceIcon",
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = "Circonférence : ${tree.circonferenceencm}cm",
            style = MaterialTheme.typography.h5
        )
    }

    Spacer(modifier = Modifier.size(10.dp))

    Text(
        text = "Adresse : ${tree.adresse}",
        style = MaterialTheme.typography.h5
    )

}

@Composable
@Preview
fun PreviewTreeDescription() {
    Arbre_ClassificationTheme {
        Column {
            TreeDescription(tree = mock())
        }
    }
}