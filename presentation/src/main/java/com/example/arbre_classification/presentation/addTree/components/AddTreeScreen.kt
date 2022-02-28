package com.example.arbre_classification.presentation.addTree.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.arbre_classification.presentation.addTree.AddTreeViewModel
import com.example.domain.models.Tree
import com.ramcosta.composedestinations.annotation.Destination

@Destination(route = "add_tree_screen")
@Composable
fun AddTreeScreen(
    addTreeViewModel: AddTreeViewModel = hiltViewModel()
) {

    val idText = rememberSaveable { mutableStateOf("") }
    val especeText = rememberSaveable { mutableStateOf("") }
    val hauteurText = rememberSaveable { mutableStateOf("") }
    val circonfText = rememberSaveable { mutableStateOf("") }
    val adresseText = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Text(text = "id")
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = idText.value,
            onValueChange = {
                idText.value = it
            },
            label = { Text("id") }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "espèce")
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = especeText.value,
            onValueChange = {
                especeText.value = it
            },
            label = { Text("espèce") }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "hauteur")
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = hauteurText.value,
            onValueChange = {
                hauteurText.value = it
            },
            label = { Text("hauteur") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "circonférence")
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = circonfText.value,
            onValueChange = {
                circonfText.value = it
            },
            label = { Text("circonférence") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "adresse")
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = adresseText.value,
            onValueChange = {
                adresseText.value = it
            },
            label = { Text("adresse") }
        )
        Button(onClick = {
            addTreeViewModel.addTree(
                listOf(
                    Tree(
                        id = idText.value,
                        espece = especeText.value,
                        hauteurenm = hauteurText.value.toInt(),
                        circonferenceencm = circonfText.value.toInt(),
                        adresse = adresseText.value
                    )
                )
            )
        }) {
            Text(text = "Save")
        }
    }
}