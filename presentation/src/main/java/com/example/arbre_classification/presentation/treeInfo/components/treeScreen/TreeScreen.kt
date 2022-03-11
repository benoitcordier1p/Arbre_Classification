package com.example.arbre_classification.presentation.treeInfo.components.treeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arbre_classification.R
import com.example.arbre_classification.presentation.ui.theme.Arbre_ClassificationTheme
import com.example.arbre_classification.util.WindowInfo
import com.example.arbre_classification.util.animations.TreesTransitions
import com.example.arbre_classification.util.event.RxEventHandler
import com.example.arbre_classification.util.event.TreeEvent
import com.example.arbre_classification.util.rememberWindowInfo
import com.example.domain.models.Tree
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Destination(route = "trees_screen/details", style = TreesTransitions::class)
@Composable
fun TreeScreen(navigator: DestinationsNavigator,tree: Tree) {

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
                        TreeDescription(navigator, tree = it)
                    }
                }
            }
        }
    }
}

@Composable
fun TreeDescription(navigator: DestinationsNavigator,tree: Tree) {
    val windowInfo = rememberWindowInfo()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TreeImageCard(
            painter = painterResource(R.drawable.tree),
            contentDescription = "Image of the tree",
            title = "Arbre n°${tree.id}",
        )
    }

    Spacer(modifier = Modifier.size(10.dp))
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        CompactScreenDescription(tree = tree)
    } else {
        LargeScreenDescription(tree = tree)
    }

    Icon(
        imageVector = Icons.Default.Delete,
        contentDescription = null,
        tint = Color.Black,
        modifier = Modifier
            .padding(top = (7.5).dp)
            .size(30.dp)
            .clickable {
                RxEventHandler.publishEvent(TreeEvent.DeleteTree(tree))
                navigator.popBackStack()
            }
    )
}

@Composable
fun TreeImageCard(
    painter: Painter,
    contentDescription: String,
    title: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black,
                        ),
                        startY = 200f
                    )
                )
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    title,
                    style = TextStyle(color = Color.White, fontSize = 14.sp)
                )
            }
        }
    }
}

@Composable
fun CompactScreenDescription(tree: Tree) {
    Text(
        text = "Espèce : ${tree.espece}",
        style = MaterialTheme.typography.h5
    )

    Spacer(modifier = Modifier.size(10.dp))

    Text(
        text = "Adresse : ${tree.adresse}",
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


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LargeScreenDescription(tree: Tree) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Espèce : ${tree.espece}",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.weight(0.5F)
            )

            Text(
                text = "Adresse : ${tree.adresse}",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.weight(0.5F)
            )

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.size(10.dp))

            Row(modifier = Modifier.weight(0.5F)) {
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
            Row(modifier = Modifier.weight(0.5F)) {
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
        }
        Spacer(modifier = Modifier.height(50.dp))
    }


}

@Composable
@Preview
fun PreviewTreeDescription() {
    Arbre_ClassificationTheme {
        Column {

        }
    }
}